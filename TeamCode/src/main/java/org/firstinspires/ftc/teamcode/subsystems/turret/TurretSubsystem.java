package org.firstinspires.ftc.teamcode.subsystems.turret;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.ThaiVPController;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;
import org.firstinspires.ftc.teamcode.subsystems.PinpointSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

public class TurretSubsystem extends SubsystemBase {
    private DcMotor turretMotor;
    private sensOrangeEncoder turretEncoder;
    private ThaiVPController powerManage;
    private double targetpos = ConstantsTurret.targetposition;
    private Telemetry telemetry;
    boolean manualControl = false;
    double lastPower = 0;

    boolean useMoveShoot = false;

    public TurretSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        powerManage = new ThaiVPController();
        powerManage.setExponent(ConstantsTurret.exponent);
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

    public double getTurretPositionRadians(){
        return turretEncoder.getRadians();
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
    public double getError() {
        return AngleUnit.normalizeDegrees(targetpos - turretEncoder.getDegrees());
    }

    public Pose2d movingShoot(PinpointSubsystem robotPose){
        double yTranslation = robotPose.getVelocity().getY()*ConstantsTurret.ballTravelTime;
        double xTranslation = robotPose.getVelocity().getX()*ConstantsTurret.ballTravelTime;
        return new Pose2d(StaticValues.goalPos.getY() + yTranslation, StaticValues.goalPos.getX() + xTranslation, StaticValues.goalPos.getRotation());
    }

    @Override
    public void periodic() {
        powerManage.setPID(ConstantsTurret.kp);
        //telemetry.addData("Kp is", ConstantsTurret.kp);
        //telemetry.addData("setting power to", powerManage.calculateAngleWrapping(targetpos, turretEncoder.getDegrees()));
        telemetry.addData("turret position", turretEncoder.getDegrees());
        if (!manualControl)
            setPower(powerManage.calculate(targetpos, turretEncoder.getDegrees())); //NO ANGLE WRAP BECAUSE THE WIRING CANT WRAP
    }
}
