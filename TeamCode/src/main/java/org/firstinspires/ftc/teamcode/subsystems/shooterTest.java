package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.hood.HoodSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.transferArmServo;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.transferWheelServo;

@TeleOp(name = "TestShooter", group = "Linear OpMode")
public class shooterTest extends LinearOpMode {
    private ShooterSubsystem shooter;
    private HoodSubsystem hood;
    private transferArmServo transferArm;
    private transferWheelServo transferWheel;
    private IntakeSubsystem intake;

    @Override
    public void runOpMode(){
        shooter = new ShooterSubsystem(hardwareMap);
        hood = new HoodSubsystem(hardwareMap);
        transferArm = new transferArmServo();
        transferWheel = new transferWheelServo();
        intake = new IntakeSubsystem();

        transferArm.init(hardwareMap);
        transferWheel.init(hardwareMap);
        intake.init(hardwareMap);

        waitForStart();
        intake.takein();
        transferWheel.on();
        shooter.on();
        while(opModeIsActive()) {
            if (gamepad1.dpad_down){
                intake.manual(gamepad1);
                transferArm.manual(gamepad1);
                shooter.setPower(1.0);
            }
            transferArm.manual(gamepad1);
            hood.manual(gamepad1, telemetry);
            telemetry.update();
        }
    }
}


