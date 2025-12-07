package org.firstinspires.ftc.teamcode.subsystems.shooter;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.InterpLUT;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

public class ShooterSubsystem extends SubsystemBase {
    private DcMotorEx shooterMotorRight;
    private DcMotor shooterMotorLeft;
    private DcMotorEx encoderMotor;
    SquIDController velocityController = new SquIDController();

    double targetVelocity = 0;
    InterpLUT velocityLUT = new InterpLUT();
    InterpLUT AtagVelocityLUT = new InterpLUT();
    InterpLUT usingLUT;
    double lastPower = 0;

    public ShooterSubsystem (HardwareMap hwMap) {
        shooterMotorRight = (DcMotorEx) hwMap.get(DcMotor.class, "shooterMotorRight");
        shooterMotorLeft = hwMap.get(DcMotor.class, "shooterMotorLeft");
        encoderMotor = (DcMotorEx) hwMap.get(DcMotor.class, "driveFrontLeft");
        //reverse right motor
        shooterMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //pinpoint
        velocityLUT.add(0,-660);
        velocityLUT.add(35, -1160);
        velocityLUT.add(50,-1180);
        velocityLUT.add(75, -1360);
        velocityLUT.add(120,-1820);
        velocityLUT.add(130, -1920);
        velocityLUT.createLUT();

        AtagVelocityLUT.add(0,-660);
        AtagVelocityLUT.add(35, -1180);
        AtagVelocityLUT.add(50,-1200);
        AtagVelocityLUT.add(75, -1240);
        AtagVelocityLUT.add(120,-1840);
        AtagVelocityLUT.add(130, -1940);
        AtagVelocityLUT.createLUT();

        usingLUT = velocityLUT;
    }

    public void useAtag(boolean yes){
        if (yes){
            usingLUT = AtagVelocityLUT;
        } else{
            usingLUT = velocityLUT;
        }
    }

    public void on() {
        shooterMotorRight.setPower(1);
        shooterMotorLeft.setPower(1);
    }

    public void intake(){
        targetVelocity = 20000;
    }

    public void setPower(double power){
        if (Util.inRange(lastPower, power, 0.01))
            return;
        shooterMotorRight.setPower(power * StaticValues.getVoltageScalar());
        shooterMotorLeft.setPower(power * StaticValues.getVoltageScalar());
        lastPower = power;
    }
    public void shootFromDistance(double distance) {
        double velocity = ShooterConstants.closeVelocity;
        if (distance > 0 && distance < 200)
            velocity = usingLUT.get(Range.clip(distance, 0, 129));
        Log.i("Shooter", "Shooting from distance: " + distance + " with velocity: " + velocity);
        setVelocity(velocity);
    }

    public void setVelocity(double velocity) {
        targetVelocity = velocity;
    }

    public double getVelocity(){
        return encoderMotor.getVelocity();
    }
    public boolean atVelocity() {
        return Util.inRange(targetVelocity, encoderMotor.getVelocity(),  ShooterConstants.tolerance);

    }

//
//    public void velocityControlTest(){
//        setVelocity(constants.targetVelocity);
//    }

    @Override
    public void periodic() {
        Robot.getTelemetry().addData("shooter velocity", encoderMotor.getVelocity());
        velocityController.setPID(ShooterConstants.kvp);
        setPower(velocityController.calculate(targetVelocity, encoderMotor.getVelocity()) + ShooterConstants.kv * targetVelocity);
    }

}
