package org.firstinspires.ftc.teamcode.Auton;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.AprilTagMotifDetectionCommand;
import org.firstinspires.ftc.teamcode.command.CreepCommand;
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.LogKittenCommand;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.turret.ConstantsTurret;

@Autonomous(name = "AutonV1", group = "Auton")
public abstract class AutonV1 extends Robot {
    DefaultGoToPointCommand toPoint;

    public void run(int m) throws InterruptedException {
        initialize();
        StaticValues.resetMotif();
        StaticValues.resetArtifacts();
        turret.setTargetPosition(ConstantsTurret.obeliskPos);
        shooter.setVelocity(0);
        timer.reset();
        while(!aprilTag.isStreaming()||timer.milliseconds()>1000);
        aprilTag.waitForSetExposure(1000,1000);
        pinpointSubsystem.setPose(new Pose2d(60,(72)*m, Rotation2d.fromDegrees(135)));
        Log.i("AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading());
        toPoint = new DefaultGoToPointCommand(drive, pinpointSubsystem, new Pose2d(60,(72)*m, Rotation2d.fromDegrees(135)));
        Log.i("AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading());
        drive.setDefaultCommand(toPoint);
        Log.i("AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading());
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "starting"),
                        new GoToPointWithDefaultCommand(new Pose2d(23,(36)*m, Rotation2d.fromDegrees(135)), toPoint), // shooting
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "is at pos"),
                        //new LogKittenCommand(Log.ASSERT, "AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading()),
                        new AprilTagMotifDetectionCommand(aprilTag),
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "april tag detection completed"),
                        //new WaitCommand(5000),

                        //new GoToPointWithDefaultCommand(new Pose2d(23, (36)*m, new Rotation2d((-2.3)*m)), toPoint), // shooting
                        new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPos)),
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "turret is ready"),
                        //new WaitCommand(5000),
                        //new LogKittenCommand(Log.ASSERT, "AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading()),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint),
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "done shooting"),
                        //new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint).raceWith(new TurretAimDefaultCommand(aprilTag, turret)),
                        //new RaceAimShootAuto(new TurretAimDefaultCommand(aprilTag, turret), new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint)),
                        //new GoToPointWithDefaultCommand(new Pose2d(12, 28, Rotation2d.fromDegrees(90)), toPoint),
                        new GoToPointWithDefaultCommand(new Pose2d(11, (58)*m, Rotation2d.fromDegrees((90)*m)), toPoint),
                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor)
                                .alongWith(new GoToPointWithDefaultCommand(new Pose2d(11, (61)*m, Rotation2d.fromDegrees((90)*m)), toPoint)
                                        .andThen(new GoToPointWithDefaultCommand(new Pose2d(11, (64)*m, Rotation2d.fromDegrees((90)*m)), toPoint))
                                        .andThen(new GoToPointWithDefaultCommand(new Pose2d(11, (70)*m, Rotation2d.fromDegrees((90)*m)), toPoint))),

                        new GoToPointWithDefaultCommand(new Pose2d(23,(36)*m, Rotation2d.fromDegrees(135)), toPoint), // shooting
                        new LogKittenCommand(Log.ASSERT, "AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading()),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint),

                        //new GoToPointWithDefaultCommand(new Pose2d(-5-24, 38+22, Rotation2d.fromDegrees(90)), toPoint),
                        new GoToPointWithDefaultCommand(new Pose2d(-12, (58)*m, Rotation2d.fromDegrees((90)*m)), toPoint)
                                .andThen(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor)
                                        .alongWith(new CreepCommand(toPoint, m, spindexer))),
                        new GoToPointWithDefaultCommand(new Pose2d(23,(36)*m, Rotation2d.fromDegrees(135)), toPoint), // shooting
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint),

                        new GoToPointWithDefaultCommand(new Pose2d(-34, (58)*m, Rotation2d.fromDegrees((90)*m)), toPoint)
                                .andThen(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor)
                                        .alongWith(new CreepCommand(toPoint, m, spindexer))),
                        //new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-34, (60)*m, new Rotation2d((1.5)*m)), toPoint)),
                        //new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-34, (71)*m, new Rotation2d((1.5)*m)), toPoint)),
                        //new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-34, (80)*m, new Rotation2d((1.5)*m)), toPoint)),
                        new GoToPointWithDefaultCommand(new Pose2d(23,(36)*m, Rotation2d.fromDegrees(135)), toPoint), // shooting
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint)
                        //
                        //new GoToPointWithDefaultCommand(new Pose2d(-5-24, 58+22, Rotation2d.fromDegrees(90)), toPoint),
                        //new WaitCommand(500),
                        //new GoToPointWithDefaultCommand(new Pose2d(60-15-36-24, 72-15+26+24, Rotation2d.fromDegrees(90)), toPoint),
                        //new WaitCommand(500),
                        //new GoToPointWithDefaultCommand(new Pose2d(60-15-36-24, 72-15+26, Rotation2d.fromDegrees(90)), toPoint),
                        //new WaitCommand(500),
                        //new GoToPointWithDefaultCommand(new Pose2d(60-15-36-24-24, 72-15+26+24, Rotation2d.fromDegrees(90)), toPoint)
                )
                );
        waitForStart();
        while (opModeIsActive()){
            for (int i = 0; i < 3; i++){
                Log.i("Motif", "ball" + StaticValues.getMotif(i));
            }
            //telemetry.addData("motif!", Robot.motif.toString());
            telemetry.addData("shooter", spindexer.getDegrees());
            update();
        }
    }
}
