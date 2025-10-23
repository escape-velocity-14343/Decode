package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.hood.hoodServo;
import org.firstinspires.ftc.teamcode.subsystems.intake.intakeServo;
import org.firstinspires.ftc.teamcode.subsystems.shooter.shooterMotor;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.transferArmServo;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.transferWheelServo;

@TeleOp(name = "TestShooter", group = "Linear OpMode")
public class shooterTest extends LinearOpMode {
    private shooterMotor shooter;
    private hoodServo hood;
    private transferArmServo transferArm;
    private transferWheelServo transferWheel;
    private intakeServo intake;

    @Override
    public void runOpMode(){
        shooter = new shooterMotor();
        hood = new hoodServo();
        transferArm = new transferArmServo();
        transferWheel = new transferWheelServo();
        intake = new intakeServo();

        shooter.init(hardwareMap);
        hood.init(hardwareMap);
        transferArm.init(hardwareMap);
        transferWheel.init(hardwareMap);
        intake.init(hardwareMap);

        waitForStart();
        intake.on();
        transferWheel.on();
        shooter.on();
        while(opModeIsActive()) {
            if (gamepad1.dpad_down){
                intake.manual(gamepad1);
                transferArm.manual(gamepad1);
                shooter.manual(gamepad1);
            }
            transferArm.manual(gamepad1);
            hood.manual(gamepad1);
        }
    }
}


