package org.firstinspires.ftc.teamcode.subsystems.shooter;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.InterpLUT;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

public class ShooterSubsystem extends SubsystemBase {
    private DcMotorEx shooterMotorRight;
    private DcMotor shooterMotorLeft;
    SquIDController velocityController = new SquIDController();

    double targetVelocity = 0;
    InterpLUT velocityLUT = new InterpLUT();

    public ShooterSubsystem (HardwareMap hwMap) {
        shooterMotorRight = (DcMotorEx) hwMap.get(DcMotor.class, "shooterMotorRight");
        shooterMotorLeft = hwMap.get(DcMotor.class, "shooterMotorLeft");
        //reverse right motor
        shooterMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
        velocityLUT.add(0,0);
        velocityLUT.add(40,-1500);
        velocityLUT.add(60,-1600);
        velocityLUT.add(70,-1800);
        velocityLUT.createLUT();

    }

    public void on(){
        shooterMotorRight.setPower(1);
        shooterMotorLeft.setPower(1);
    }
    public void setPower(double power){
        shooterMotorRight.setPower(power);
        shooterMotorLeft.setPower(power);
    }
    public void shootFromDistance(double distance){
        double velocity = velocityLUT.get(distance);
        Log.i("Shooter", "Shooting from distance: " + distance + " with velocity: " + velocity);
        setVelocity(velocity);
    }

    public void setVelocity(double velocity) {
        targetVelocity = velocity;
    }

    public double getVelocity(){
        return shooterMotorRight.getVelocity();
    }
    public boolean atVelocity() {
        return Util.inRange(targetVelocity, shooterMotorRight.getVelocity(), 67);

    }

//
//    public void velocityControlTest(){
//        setVelocity(constants.targetVelocity);
//    }

    @Override
    public void periodic() {
        Robot.getTelemetry().addData("shooter velocity", shooterMotorRight.getVelocity());
        velocityController.setPID(ShooterConstants.kvp);
        setPower(velocityController.calculate(targetVelocity, shooterMotorRight.getVelocity()) + ShooterConstants.kv * targetVelocity);
    }

}
