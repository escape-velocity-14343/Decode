package org.firstinspires.ftc.teamcode.subsystems.shooter;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.SquIDController;

public class ShooterSubsystem extends SubsystemBase {
    private DcMotorEx shooterMotorRight;
    private DcMotor shooterMotorLeft;
    SquIDController velocityController = new SquIDController();

    double targetVelocity = 0;

    public ShooterSubsystem (HardwareMap hwMap) {
        shooterMotorRight = (DcMotorEx) hwMap.get(DcMotor.class, "shooterMotorRight");
        shooterMotorLeft = hwMap.get(DcMotor.class, "shooterMotorLeft");
        //reverse right motor
        shooterMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    public void on(){
        shooterMotorRight.setPower(1);
        shooterMotorLeft.setPower(1);
    }
    public void setPower(double power){
        shooterMotorRight.setPower(power);
        shooterMotorLeft.setPower(power);
    }
    public void setVelocity(double velocity) {
        targetVelocity = velocity;
    }

    @Override
    public void periodic() {
//        setPower(velocityController.calculate(targetVelocity, shooterMotorRight.getVelocity()));
    }

}
