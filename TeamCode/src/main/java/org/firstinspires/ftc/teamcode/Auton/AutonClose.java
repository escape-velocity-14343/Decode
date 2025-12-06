package org.firstinspires.ftc.teamcode.Auton;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.command.AprilTagMotifDetectionCommand;
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.LogKittenCommand;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.TimeoutCommand;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.turret.ConstantsTurret;


public abstract class AutonClose extends Robot {
    DefaultGoToPointCommand toPoint;

    public void run(int m) throws InterruptedException {
        initialize();
        StaticValues.resetMotif();
        StaticValues.resetArtifacts();
        turret.setTargetPosition(ConstantsTurret.obeliskPosClose * m);
        shooter.setVelocity(0);
        setTurretCamExposure();
        pinpointSubsystem.setPose(new Pose2d(60,(48)*m, Rotation2d.fromDegrees(135*m)));
        Log.i("AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading());
        toPoint = new DefaultGoToPointCommand(drive, pinpointSubsystem, new Pose2d(60,(48)*m, Rotation2d.fromDegrees(135*m)));
        Log.i("AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading());
        drive.setDefaultCommand(toPoint);
        Log.i("AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading());
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "starting"),
                        new GoToPointWithDefaultCommand(new Pose2d(27,(16)*m, Rotation2d.fromDegrees(135*m)), toPoint).alongWith(new AprilTagMotifDetectionCommand(aprilTag)),// shooting
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "is at pos"),
                        new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosClose*m)),
                        //new LogKittenCommand(Log.ASSERT, "AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading()),


                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "april tag detection completed"),
                        //new WaitCommand(5000),

                        //new GoToPointWithDefaultCommand(new Pose2d(23, (36)*m, new Rotation2d((-2.3)*m)), toPoint), // shooting
                        //new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosClose * m)),
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "turret is ready"),
                        //new WaitCommand(5000),
                        //new LogKittenCommand(Log.ASSERT, "AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading()),
                        new InstantCommand(()->Log.println(Log.ASSERT, "AUTO TURRET", "Probably better turret position: " + (pinpointSubsystem.getHeading().getDegrees() - pinpointSubsystem.getRotationToPoint(new Pose2d(67, 51, new Rotation2d(0))).getDegrees()))),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem),
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "done shooting"),
                        //new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint).raceWith(new TurretAimDefaultCommand(aprilTag, turret)),
                        //new RaceAimShootAuto(new TurretAimDefaultCommand(aprilTag, turret), new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint)),
                        //new GoToPointWithDefaultCommand(new Pose2d(12, 28, Rotation2d.fromDegrees(90)), toPoint),
                        new GoToPointWithDefaultCommand(new Pose2d(12, (28) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 20, 67),
                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                (new TimeoutCommand(new GoToPointWithDefaultCommand(new Pose2d(12, (36) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67),1000)
                                                .andThen(new TimeoutCommand(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<3), 1000))
                                                .andThen(new TimeoutCommand(new GoToPointWithDefaultCommand(new Pose2d(12, (42) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67),1000))
                                                .andThen(new TimeoutCommand(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<2),1000))
                                                .andThen(new TimeoutCommand(new GoToPointWithDefaultCommand(new Pose2d(12, (52) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67),1000))
                                                .andThen(new TimeoutCommand(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<1),1000)))), 5000),//.interruptOn(()->spindexer.getRemainingSpace()<1),

                        //new GoToPointWithDefaultCommand(new Pose2d(5, (42)*m, Rotation2d.fromDegrees((90)*m)), toPoint).alongWith(new InstantCommand(() -> intake.setPower(-1))),
                        //new GoToPointWithDefaultCommand(new Pose2d(5, (56)*m, Rotation2d.fromDegrees((90)*m)), toPoint).alongWith(new InstantCommand(() -> intake.setPower(1))),
                        //new WaitCommand(750),


                        new GoToPointWithDefaultCommand(new Pose2d(27,(16)*m, Rotation2d.fromDegrees(135 * m)), toPoint).alongWith(new InstantCommand(() -> intake.setPower(-1))), // shooting
                        new LogKittenCommand(Log.ASSERT, "AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading()),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem).alongWith(new InstantCommand(() -> intake.setPower(0))),

                        new GoToPointWithDefaultCommand(new Pose2d(-15, (28) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67),
                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                (new TimeoutCommand(new GoToPointWithDefaultCommand(new Pose2d(-15, (36) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67),1000)
                                        .andThen(new TimeoutCommand(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<3), 1000))
                                        .andThen(new TimeoutCommand(new GoToPointWithDefaultCommand(new Pose2d(-15, (42) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67),1000))
                                        .andThen(new TimeoutCommand(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<2),1000))
                                        .andThen(new TimeoutCommand(new GoToPointWithDefaultCommand(new Pose2d(-15, (67) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67),1000))
                                        .andThen(new TimeoutCommand(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<1),1000)))), 5000),
                        //.interruptOn(()->spindexer.getRemainingSpace()<1),
                        new GoToPointWithDefaultCommand(new Pose2d(27,(16)*m, Rotation2d.fromDegrees(135 * m)), toPoint).alongWith(new InstantCommand(() -> intake.setPower(-1))), // shooting
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem).alongWith(new InstantCommand(() -> intake.setPower(0))),

                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                new GoToPointWithDefaultCommand(new Pose2d(-33, (24)*m, Rotation2d.fromDegrees((90)*m)), toPoint, 30, 67).interruptOn(()->pinpointSubsystem.getPose().getX()<=-12)
                                        .andThen(new GoToPointWithDefaultCommand(new Pose2d(-33, (36)*m, Rotation2d.fromDegrees((90)*m)), toPoint, 15, 67)
                                                .andThen(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<3))
                                                .andThen(new GoToPointWithDefaultCommand(new Pose2d(-33, (42)*m, Rotation2d.fromDegrees((90)*m)), toPoint, 15, 67))
                                                .andThen(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<2))
                                                .andThen(new GoToPointWithDefaultCommand(new Pose2d(-33, (67)*m, Rotation2d.fromDegrees((90)*m)), toPoint, 15, 67))
                                                .andThen(new TimeoutCommand(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<1),1000)))),//.interruptOn(()->spindexer.getRemainingSpace()<1),
                        new GoToPointWithDefaultCommand(new Pose2d(27,(16)*m, Rotation2d.fromDegrees(135 * m)), toPoint).alongWith(new InstantCommand(() -> intake.setPower(-1))), // shooting
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem).alongWith(new InstantCommand(() -> intake.setPower(0))),
                        new GoToPointWithDefaultCommand(new Pose2d(-60,(24)*m, Rotation2d.fromDegrees(135 * m)), toPoint)
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
