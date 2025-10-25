package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.hood.HoodSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;


@TeleOp(name = "TestShooter", group = "Linear OpMode")
public class shooterTest extends LinearOpMode {
    private ShooterSubsystem shooter;
    private HoodSubsystem hood;
    private TransferArmSubsystem transferArm;
    private TransferWheelSubsystem transferWheel;
    private IntakeSubsystem intake;

    @Override
    public void runOpMode(){
        shooter = new ShooterSubsystem(hardwareMap);
        hood = new HoodSubsystem(hardwareMap);
        transferArm = new TransferArmSubsystem(hardwareMap);
        transferWheel = new TransferWheelSubsystem(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap);

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


