package org.firstinspires.ftc.teamcode.subsystems.robot;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PinpointSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.hood.HoodSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.ColorSensorSubsystem;
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
    public ColorSensorSubsystem artifactSensor;
    public PinpointSubsystem pinpointSubsystem;
    public AprilTagSubsystem aprilTag;
    public static Telemetry publicTelemetry;
    public static int[] motif = {1, 2, 1};
    public static Pose2d pose;
    public static int atagBearing = 0;
    public static boolean blueAlliance;
    public ElapsedTime timer = new ElapsedTime();
    int loopTimerCounter = 0;
    double loopTime = 0;

    public static boolean useTestTelemetry = true;

    public List<LynxModule> hubs;

    public void initialize(){

        CommandScheduler.getInstance().reset();
        CommandScheduler.getInstance().cancelAll();
        telemetry = new JoinedTelemetry(telemetry, PanelsTelemetry.INSTANCE.getFtcTelemetry());
        publicTelemetry = useTestTelemetry? telemetry : PanelsTelemetry.INSTANCE.getFtcTelemetry();
        artifactSensor = new ColorSensorSubsystem(hardwareMap);
        pinpointSubsystem = new PinpointSubsystem(hardwareMap);
        hood = new HoodSubsystem(hardwareMap);
        drive = new MecanumDriveSubsystem(hardwareMap, () -> pinpointSubsystem.getHeading().getRadians());
        turret = new TurretSubsystem(hardwareMap, telemetry);
        intake = new IntakeSubsystem(hardwareMap);
        shooter = new ShooterSubsystem(hardwareMap);
        spindexer = new SpindexerSubsystem(hardwareMap);
        transferArm = new TransferArmSubsystem(hardwareMap);
        transferWheel = new TransferWheelSubsystem(hardwareMap);
        aprilTag = new AprilTagSubsystem(hardwareMap, telemetry);

        hubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : hubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
    }

    public void update() {
        for (LynxModule hub : hubs) {
            hub.clearBulkCache();
        }
        pose = pinpointSubsystem.getPose();
        CommandScheduler.getInstance().run();
        publicTelemetry.addData("Loop Time", loopTime);
        loopTimerCounter++;
        if (loopTimerCounter >= 10) {
            loopTime = timer.milliseconds()/10;
            timer.reset();
            loopTimerCounter = 0;
        }

        telemetry.update();

    }

    public void end() {
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().reset();
        aprilTag.end();
        Log.d("RADIAN DEGREE MISMATCH", "Mecanum + " );

    }

    public static Telemetry getTelemetry() {
        return publicTelemetry;
    }
    public static Pose2d getPose() {
        return pose;
    }
}
