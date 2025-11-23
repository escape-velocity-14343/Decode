package org.firstinspires.ftc.teamcode.Auton;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;

import org.firstinspires.ftc.teamcode.command.AprilTagMotifDetectionCommand;
//import org.firstinspires.ftc.teamcode.command.CreepOnCommand;
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.LogKittenCommand;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.TimeoutCommand;
import org.firstinspires.ftc.teamcode.command.TurretAimCommand;
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
        setExposure();
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
                        new GoToPointWithDefaultCommand(new Pose2d(27,(16)*m, Rotation2d.fromDegrees(135*m)), toPoint).alongWith(
                                new AprilTagMotifDetectionCommand(aprilTag).andThen(
                                    new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosClose*m)).andThen(
                                            new WaitUntilCommand(()-> Util.inRange(turret.getTurretPosition(), ConstantsTurret.shootingPosClose*m, 15)),
                                            new InstantCommand(() -> turret.setDefaultCommand(new TurretAimDefaultCommand(aprilTag, turret, pinpointSubsystem)))))),// shooting
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "is at pos"),

                        //new LogKittenCommand(Log.ASSERT, "AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading()),


                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "april tag detection completed"),
                        //new WaitCommand(5000),

                        //new GoToPointWithDefaultCommand(new Pose2d(23, (36)*m, new Rotation2d((-2.3)*m)), toPoint), // shooting
                        //new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosClose * m)),
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "turret is ready"),
                        //new WaitCommand(5000),
                        //new LogKittenCommand(Log.ASSERT, "AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading()),
                        new InstantCommand(()->Log.println(Log.ASSERT, "AUTO TURRET", "Probably better turret position: " + (pinpointSubsystem.getHeading().getDegrees() - pinpointSubsystem.getRotationToPoint(new Pose2d(67, 51, new Rotation2d(0))).getDegrees()))),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint),
                        new LogKittenCommand(Log.ASSERT, "AUTO V1", "done shooting"),
                        //new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint).raceWith(new TurretAimDefaultCommand(aprilTag, turret)),
                        //new RaceAimShootAuto(new TurretAimDefaultCommand(aprilTag, turret), new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint)),
                        //new GoToPointWithDefaultCommand(new Pose2d(12, 28, Rotation2d.fromDegrees(90)), toPoint),
                        new GoToPointWithDefaultCommand(new Pose2d(12, (28) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 20, 67),

                        new InstantCommand(() -> StaticValues.setMaxSpeed(0.1867)),
                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(12, (54.67)*StaticValues.getM(), Rotation2d.fromDegrees((90)*StaticValues.getM())), toPoint)).interruptOn(() -> spindexer.getRemainingSpace()==0),4000),//.interruptOn(()->spindexer.getRemainingSpace()<1),
                        new InstantCommand(() -> StaticValues.setMaxSpeed(1)),
                        //new GoToPointWithDefaultCommand(new Pose2d(5, (42)*m, Rotation2d.fromDegrees((90)*m)), toPoint).alongWith(new InstantCommand(() -> intake.setPower(-1))),
                        //new GoToPointWithDefaultCommand(new Pose2d(5, (56)*m, Rotation2d.fromDegrees((90)*m)), toPoint).alongWith(new InstantCommand(() -> intake.setPower(1))),
                        //new WaitCommand(750),


                        new GoToPointWithDefaultCommand(new Pose2d(27,(16)*m, Rotation2d.fromDegrees(135 * m)), toPoint).alongWith(new InstantCommand(() -> intake.setPower(-1))), // shooting
                        new LogKittenCommand(Log.ASSERT, "AUTO PINPOINT", "x is" + pinpointSubsystem.getPose().getX() + "Y is" + pinpointSubsystem.getPose().getY() + "Heading is" + pinpointSubsystem.getPose().getHeading()),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint).alongWith(new InstantCommand(() -> intake.setPower(0))),

                        new GoToPointWithDefaultCommand(new Pose2d(-15, (28) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67),
                        new InstantCommand(() -> StaticValues.setMaxSpeed(0.1867)),
                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-15, (67)*StaticValues.getM(), Rotation2d.fromDegrees((90)*StaticValues.getM())), toPoint)).interruptOn(() -> spindexer.getRemainingSpace()==0),4000),//.interruptOn(()->spindexer.getRemainingSpace()<1),
                        new InstantCommand(() -> StaticValues.setMaxSpeed(1)),
                        //.interruptOn(()->spindexer.getRemainingSpace()<1),
                        new GoToPointWithDefaultCommand(new Pose2d(27,(16)*m, Rotation2d.fromDegrees(135 * m)), toPoint).alongWith(new InstantCommand(() -> intake.setPower(-1))), // shooting
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint).alongWith(new InstantCommand(() -> intake.setPower(0))),

                        new GoToPointWithDefaultCommand(new Pose2d(-40, (28) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67),
                        new InstantCommand(() -> StaticValues.setMaxSpeed(0.1867)),
                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-40, (67)*StaticValues.getM(), Rotation2d.fromDegrees((90)*StaticValues.getM())), toPoint)).interruptOn(() -> spindexer.getRemainingSpace()==0),4000),//.interruptOn(()->spindexer.getRemainingSpace()<1),
                        new InstantCommand(() -> StaticValues.setMaxSpeed(1)),
                        new GoToPointWithDefaultCommand(new Pose2d(27,(16)*m, Rotation2d.fromDegrees(135 * m)), toPoint).alongWith(new InstantCommand(() -> intake.setPower(-1))), // shooting
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint).alongWith(new InstantCommand(() -> intake.setPower(0))),
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
