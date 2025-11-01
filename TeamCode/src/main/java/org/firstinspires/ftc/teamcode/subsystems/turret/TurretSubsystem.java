package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

public class TurretSubsystem extends SubsystemBase {
    private DcMotor turretMotor;
    private sensOrangeEncoder turretEncoder;
    private SquIDController powerManage;
    private double targetpos = ConstantsTurret.targetposition;

    public TurretSubsystem(HardwareMap hwMap){
        powerManage = new SquIDController();
        turretMotor = hwMap.get(DcMotor.class, "turretMotor");
        turretEncoder = new sensOrangeEncoder("turretEncoder", hwMap);
        turretEncoder.setPositionOffset(ConstantsTurret.offset);

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


    public void setposition(double position){
        this.targetpos = position;
    }

    public double getTurretPosition(){
        return turretEncoder.getDegrees();
    }
    public void auto(double rotate){
        if (turretEncoder.getDegrees() + rotate > 0){
            if (turretEncoder.getDegrees() + rotate < ConstantsTurret.max && turretEncoder.getDegrees() + rotate > ConstantsTurret.min){
                turretMotor.setPower(powerManage.calculate(turretEncoder.getDegrees() + rotate, turretEncoder.getDegrees()));
            } else if (turretEncoder.getDegrees() + rotate > ConstantsTurret.min) {
                turretMotor.setPower(powerManage.calculate(ConstantsTurret.max, turretEncoder.getDegrees()));
            } else {
                turretMotor.setPower(powerManage.calculate(ConstantsTurret.min, turretEncoder.getDegrees()));
            }
        }
    }
    @Override
    public void periodic(){
        powerManage.setPID(ConstantsTurret.kp);
        turretMotor.setPower(powerManage.calculateAngleWrapping(targetpos, turretEncoder.getDegrees()));
    }


}
