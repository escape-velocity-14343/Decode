package org.firstinspires.ftc.teamcode.subsystems.turret;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

public class TurretSubsystem extends SubsystemBase {
    private DcMotor turretMotor;
    private sensOrangeEncoder turretEncoder;
    private SquIDController powerManage;
    private double targetpos = ConstantsTurret.targetposition;
    private Telemetry telemetry;

    public TurretSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        powerManage = new SquIDController();
        turretMotor = hwMap.get(DcMotor.class, "turretMotor");
        turretEncoder = new sensOrangeEncoder("turretEncoder", hwMap);
        turretEncoder.setPositionOffset(ConstantsTurret.offset);
        this.telemetry = telemetry;

    }

    public void manual(Gamepad gamepad) {
        if (gamepad.right_trigger > 0){
            if (turretEncoder.getDegrees() + gamepad.right_trigger < ConstantsTurret.max){
                turretMotor.setPower(powerManage.calculate(turretEncoder.getDegrees() + gamepad.right_trigger, turretEncoder.getDegrees()));
            } else {
                turretMotor.setPower(powerManage.calculate(ConstantsTurret.max, turretEncoder.getDegrees()));
            }
        }

        if (gamepad.left_trigger > 0){
            if (turretEncoder.getDegrees() + gamepad.left_trigger > ConstantsTurret.min){
                turretMotor.setPower(powerManage.calculate(turretEncoder.getDegrees() - gamepad.left_trigger, turretEncoder.getDegrees()));
            } else {
                turretMotor.setPower(powerManage.calculate(ConstantsTurret.min, turretEncoder.getDegrees()));
            }
        }
    }


    public void setposition(double position) {
        this.targetpos = position;
    }

    public double getTurretPosition() {
        return turretEncoder.getDegrees();
    }
    public void auto(double rotate) {
        if (turretEncoder.getDegrees() + rotate > 0){
            if (turretEncoder.getDegrees() + rotate < ConstantsTurret.max && turretEncoder.getDegrees() + rotate > ConstantsTurret.min) {
                turretMotor.setPower(powerManage.calculate(turretEncoder.getDegrees() + rotate, turretEncoder.getDegrees()));
            } else if (turretEncoder.getDegrees() + rotate > ConstantsTurret.min) {
                turretMotor.setPower(powerManage.calculate(ConstantsTurret.max, turretEncoder.getDegrees()));
            } else {
                turretMotor.setPower(powerManage.calculate(ConstantsTurret.min, turretEncoder.getDegrees()));
            }
        }
    }
    public void setPower(double power) {
        if (!(turretEncoder.getDegrees() > ConstantsTurret.max && power > 0) && !(turretEncoder.getDegrees() < ConstantsTurret.min && power < 0))
            turretMotor.setPower(power);
        else
            turretMotor.setPower(0);
    }

    @Override
    public void periodic() {
        powerManage.setPID(ConstantsTurret.kp);
        telemetry.addData("Kp is", ConstantsTurret.kp);
        telemetry.addData("setting power to", powerManage.calculateAngleWrapping(targetpos, turretEncoder.getDegrees()));
        telemetry.addData("turret position", turretEncoder.getDegrees());
        setPower(powerManage.calculate(targetpos, turretEncoder.getDegrees())); //NO ANGLE WRAP BECAUSE THE WIRING CANT WRAP
    }
}
