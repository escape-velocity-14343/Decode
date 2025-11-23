package org.firstinspires.ftc.teamcode.subsystems.turret;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

public class TurretSubsystem extends SubsystemBase {
    private DcMotor turretMotor;
    private sensOrangeEncoder turretEncoder;
    private SquIDController powerManage;
    private double targetpos = ConstantsTurret.targetposition;
    private Telemetry telemetry;
    boolean manualControl = false;
    double lastPower = 0;

    public TurretSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        powerManage = new SquIDController();
        turretMotor = hwMap.get(DcMotor.class, "turretMotor");
        turretEncoder = new sensOrangeEncoder("turretEncoder", hwMap);
        turretEncoder.setPositionOffset(ConstantsTurret.offset);
        this.telemetry = telemetry;

    }

    public void setTargetPosition(double position) {
        manualControl = false;
        this.targetpos = position;
    }

    public double getTurretPosition() {
        return turretEncoder.getDegrees();
    }
    private void setPower(double power) {
        if (Util.inRange(lastPower, power, 0.01))
            return;
        if (!(turretEncoder.getDegrees() > ConstantsTurret.max && power > 0) && !(turretEncoder.getDegrees() < ConstantsTurret.min && power < 0))
            turretMotor.setPower(power);
        else
            turretMotor.setPower(0);
        lastPower = power;
    }
    public void setPowerManual(double power) {
        manualControl = true;
        setPower(power);
    }

    @Override
    public void periodic() {
        powerManage.setPID(ConstantsTurret.kp);
        telemetry.addData("Kp is", ConstantsTurret.kp);
        telemetry.addData("setting power to", powerManage.calculateAngleWrapping(targetpos, turretEncoder.getDegrees()));
        telemetry.addData("turret position", turretEncoder.getDegrees());
        if (!manualControl)
            setPower(powerManage.calculate(targetpos, turretEncoder.getDegrees())); //NO ANGLE WRAP BECAUSE THE WIRING CANT WRAP
    }
}
