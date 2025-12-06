package org.firstinspires.ftc.teamcode.Auton;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.command.AprilTagMotifDetectionCommand;
//import org.firstinspires.ftc.teamcode.command.CreepOnCommand;
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.LogKittenCommand;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.TimeoutCommand;
import org.firstinspires.ftc.teamcode.command.TurretAimDefaultCommand;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.turret.ConstantsTurret;


public abstract class AutonCloseV2 extends Robot {
    DefaultGoToPointCommand toPoint;


    public void run(int m) throws InterruptedException {
        initialize();
        StaticValues.resetMotif();
        StaticValues.resetArtifacts();
        turret.setTargetPosition(ConstantsTurret.obeliskPosClose * m);
        shooter.setVelocity(0);
        setTurretCamExposure();
        setIntakeCamExposure();
        Pose2d shootingPose = new Pose2d(15, 15*m, Rotation2d.fromDegrees(160*m));
        pinpointSubsystem.setPose(new Pose2d(56.4, (46.8) * m, Rotation2d.fromDegrees(135 * m)));
        Log.i("AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading());
        toPoint = new DefaultGoToPointCommand(drive, pinpointSubsystem, new Pose2d(56.4, (46.8) * m, Rotation2d.fromDegrees(135 * m)));
        Log.i("AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading());
        drive.setDefaultCommand(toPoint);
        Log.i("AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading());
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().schedule(
                new WaitCommand(0).andThen(
                        new TimeoutCommand(
                                new SequentialCommandGroup(
                                        new GoToPointWithDefaultCommand(shootingPose, toPoint).alongWith(
                                                new AprilTagMotifDetectionCommand(aprilTag).andThen(
                                                        new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosClose * m)).andThen(
                                                                new WaitUntilCommand(() -> Util.inRange(turret.getTurretPosition(), ConstantsTurret.shootingPosClose * m, 15)),
                                                                new InstantCommand(() -> turret.setDefaultCommand(new TurretAimDefaultCommand(aprilTag, turret, pinpointSubsystem)))))),// shooting
                                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem),
                                        new GoToPointWithDefaultCommand(new Pose2d(10, (20) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 20, 67).alongWith(new InstantCommand(()->toPoint.setDriveKP(-0.1))),
                                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                                new InstantCommand(()->toPoint.setDriveKP(DefaultGoToPointCommand.translationkP)),
                                                new RunCommand(() -> toPoint.setTarget(new Pose2d(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getX(), Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getY(), -54, 54), Rotation2d.fromDegrees(90 * m)))).interruptOn(() -> !spindexer.hasSpace())), 5000),
                                        new GoToPointWithDefaultCommand(shootingPose, toPoint).alongWith(new InstantCommand(()->toPoint.setDriveKP(DefaultGoToPointCommand.translationkP)), new InstantCommand(() -> intake.setPower(-1))), // shooting
                                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem).alongWith(new InstantCommand(() -> intake.setPower(0))),
                                        new GoToPointWithDefaultCommand(new Pose2d(-17, (20) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67).alongWith(new InstantCommand(()->toPoint.setDriveKP(-0.1))),
                                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                                new InstantCommand(()->toPoint.setDriveKP(DefaultGoToPointCommand.translationkP)),
                                                new RunCommand(() -> toPoint.setTarget(new Pose2d(Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getX(), -24, -8), Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getY(), -60, 60), Rotation2d.fromDegrees(90 * m)))).interruptOn(() -> !spindexer.hasSpace())), 5000),
                                        new GoToPointWithDefaultCommand(shootingPose, toPoint).alongWith(new InstantCommand(()->toPoint.setDriveKP(DefaultGoToPointCommand.translationkP)), new InstantCommand(() -> intake.setPower(-1))), // shooting
                                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem).alongWith(new InstantCommand(() -> intake.setPower(0))),

                                        new GoToPointWithDefaultCommand(new Pose2d(-38, (20) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67).alongWith(new InstantCommand(()->toPoint.setDriveKP(-0.1))),
                                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                                new InstantCommand(()->toPoint.setDriveKP(DefaultGoToPointCommand.translationkP)),
                                                new RunCommand(() -> toPoint.setTarget(new Pose2d(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getX(), Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getY(), -60, 60), Rotation2d.fromDegrees(90 * m)))).interruptOn(() -> !spindexer.hasSpace())), 5000),
                                        new GoToPointWithDefaultCommand(shootingPose, toPoint).alongWith(new InstantCommand(()->toPoint.setDriveKP(DefaultGoToPointCommand.translationkP)), new InstantCommand(() -> intake.setPower(-1))), // shooting
                                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem).alongWith(new InstantCommand(() -> intake.setPower(0))),
                                        new GoToPointWithDefaultCommand(new Pose2d(-20, (24) * m, Rotation2d.fromDegrees(135 * m)), toPoint)), 29000).andThen(
                                new GoToPointWithDefaultCommand(new Pose2d(0, 30, Rotation2d.fromDegrees(0)), toPoint))
                )
        );
        waitForStart();
        aprilTag.setEnableLiveView(false);
        intakeCam.setEnableLiveView(false);
        while (opModeIsActive()) {
            telemetry.addData("shooter", spindexer.getDegrees());
            update();
        }
    }
}
