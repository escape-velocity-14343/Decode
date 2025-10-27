package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.hood.HoodSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

@TeleOp (name = "TeleOp V1", group = "LinearOpMode")
public class TeleOpV1 extends LinearOpMode {
    private MecanumDriveSubsystem drive;
    private ShooterSubsystem shooter;
    private HoodSubsystem hood;
    private TransferArmSubsystem transferArm;
    private TransferWheelSubsystem transferWheel;
    private IntakeSubsystem intakeWheel;
    private SpindexerSubsystem spindexer;



    public void runOpMode(){
        drive = new MecanumDriveSubsystem(hardwareMap, ()->0);
        shooter = new ShooterSubsystem(hardwareMap);
        hood = new HoodSubsystem(hardwareMap);
        transferArm = new TransferArmSubsystem(hardwareMap);
        transferWheel = new TransferWheelSubsystem(hardwareMap);
        intakeWheel = new IntakeSubsystem(hardwareMap);
        spindexer = new SpindexerSubsystem(hardwareMap, telemetry);

        waitForStart();
        intakeWheel.takein();
        transferWheel.on();
        shooter.on();
        drive.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        while(opModeIsActive()){


        }
    }

}
