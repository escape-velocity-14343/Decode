package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveFieldCentric;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.hood.HoodSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

@TeleOp (name = "TeleOp V1", group = "LinearOpMode")
public class TeleOpV1 extends LinearOpMode {
    private MecanumDriveFieldCentric drive;
    private ShooterSubsystem shooter;
    private HoodSubsystem hood;
    private TransferArmSubsystem transferArm;
    private TransferWheelSubsystem transferWheel;
    private IntakeSubsystem intakeWheel;
    private SpindexerSubsystem spindexer;



    public void runOpMode(){
        drive = new MecanumDriveFieldCentric();
        shooter = new ShooterSubsystem(hardwareMap);
        hood = new HoodSubsystem(hardwareMap);
        transferArm = new TransferArmSubsystem(hardwareMap);
        transferWheel = new TransferWheelSubsystem(hardwareMap);
        intakeWheel = new IntakeSubsystem(hardwareMap);
        spindexer = new SpindexerSubsystem(hardwareMap, telemetry);

        drive.init(hardwareMap);

        waitForStart();
        intakeWheel.takein();
        transferWheel.on();
        shooter.on();
        drive.execute(gamepad1);
        while(opModeIsActive()){


        }
    }

}
