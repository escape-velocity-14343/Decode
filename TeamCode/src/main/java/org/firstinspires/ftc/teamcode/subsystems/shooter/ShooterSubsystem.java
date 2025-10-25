package org.firstinspires.ftc.teamcode.subsystems.shooter;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterSubsystem extends SubsystemBase {
    private DcMotor shooterMotorRight;
    private DcMotor shooterMotorLeft;
    public ShooterSubsystem(HardwareMap hwMap) {
        shooterMotorRight = hwMap.get(DcMotor.class, "shooterMotorRight");
        shooterMotorLeft = hwMap.get(DcMotor.class, "shooterMotorLeft");
        //reverse right motor
        shooterMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void on(){
        shooterMotorRight.setPower(1);
        shooterMotorLeft.setPower(1);
    }
    public void manual(Gamepad gamepad){
        double power = gamepad.right_stick_y;
        shooterMotorRight.setPower(power);
        shooterMotorLeft.setPower(power);
    }
}
