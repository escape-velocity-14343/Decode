package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.bylazar.panels.Panels;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.IntakeOnCommand;
import org.firstinspires.ftc.teamcode.Commands.ShooterOnCommand;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.hood.HoodSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretSubsystem;

import java.util.List;

public abstract class Robot extends LinearOpMode {
//    public AprilTagSubsystem apriltag;
    public HoodSubsystem hood;
    public IntakeSubsystem intake;
    public ShooterSubsystem shooter;
    public SpindexerSubsystem spindexer;
    public TransferArmSubsystem transferArm;
    public TransferWheelSubsystem transferWheel;
    public TurretSubsystem turret;
    public MecanumDriveSubsystem drive;
//    public List<LynxModule> hubs;

    public void initialize(){
//        apriltag = new AprilTagSubsystem(hardwareMap, telemetry);
        CommandScheduler.getInstance().reset();
        CommandScheduler.getInstance().cancelAll();
        telemetry = new JoinedTelemetry(telemetry, PanelsTelemetry.INSTANCE.getFtcTelemetry());

        hood = new HoodSubsystem(hardwareMap);
        drive = new MecanumDriveSubsystem(hardwareMap, ()->0);
        turret = new TurretSubsystem(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap);
        shooter = new ShooterSubsystem(hardwareMap);
        spindexer = new SpindexerSubsystem(hardwareMap, telemetry);
        transferArm = new TransferArmSubsystem(hardwareMap);
        transferWheel = new TransferWheelSubsystem(hardwareMap);
        shooter.setDefaultCommand(new ShooterOnCommand(shooter));
        intake.setDefaultCommand(new IntakeOnCommand(intake));
//
//        hubs = hardwareMap.getAll(LynxModule.class);
//        for (LynxModule hub : hubs) {
//            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
//        }
    }

    public void update() {
//        for (LynxModule hub : hubs) {
//            hub.clearBulkCache();
//        }
        CommandScheduler.getInstance().run();
        telemetry.update();
    }
}
