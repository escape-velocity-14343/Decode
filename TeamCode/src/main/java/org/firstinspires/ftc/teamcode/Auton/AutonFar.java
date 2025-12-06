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
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.LogKittenCommand;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.TimeoutCommand;
import org.firstinspires.ftc.teamcode.command.TurretAimDefaultCommand;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.turret.ConstantsTurret;


public abstract class AutonFar extends Robot {
    DefaultGoToPointCommand toPoint;
    Pose2d shootingPose = new Pose2d(-50, 12, Rotation2d.fromDegrees(180));

    public void run(int m) throws InterruptedException{
        shootingPose = new Pose2d(-50, 12*m, Rotation2d.fromDegrees(180));
        initialize();
        StaticValues.resetMotif();
        StaticValues.resetArtifacts();
        turret.setTargetPosition(ConstantsTurret.obeliskPosFar * m);
        setTurretCamExposure();

        pinpointSubsystem.setPose(new Pose2d(-62, 18 * m, Rotation2d.fromDegrees(180)));
        toPoint = new DefaultGoToPointCommand(drive, pinpointSubsystem, new Pose2d(-62, 18 * m, Rotation2d.fromDegrees(180)));
        drive.setDefaultCommand(toPoint);
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().schedule(
                new WaitCommand(0).alongWith(
                        new TimeoutCommand(
                                new SequentialCommandGroup(
                                        new AprilTagMotifDetectionCommand(aprilTag),
                                        new InstantCommand(() -> turret.setDefaultCommand(new TurretAimDefaultCommand(aprilTag, turret, pinpointSubsystem))),
                                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem).alongWith(new InstantCommand (()->Log.println(Log.ASSERT, "shooter aligned", "turret err: " + turret.getError()))),

                                        new GoToPointWithDefaultCommand(new Pose2d(-30, (23) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 10, 67),
                                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                                new RunCommand(() -> toPoint.setTarget(new Pose2d(Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getX(), -65, 65), Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getY(), -65, 65), Rotation2d.fromDegrees(90 * m)))).interruptOn(() -> !spindexer.hasSpace())), 5000),
                                        new GoToPointWithDefaultCommand(shootingPose, toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem).alongWith(new InstantCommand (()->Log.println(Log.ASSERT, "shooter aligned", "turret err: " + turret.getError()))),

                                        new GoToPointWithDefaultCommand(new Pose2d(-40, (54) * m, Rotation2d.fromDegrees((175) * m)), toPoint, 10, 67),
                                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                                new RunCommand(() -> toPoint.setTarget(new Pose2d(Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getX(), -65, 65), Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getY(), -65, 65), Rotation2d.fromDegrees(175)))).interruptOn(() -> !spindexer.hasSpace())), 5000),
                                        new GoToPointWithDefaultCommand(shootingPose, toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem).alongWith(new InstantCommand (()->Log.println(Log.ASSERT, "shooter aligned", "turret err: " + turret.getError()))),

                                        new GoToPointWithDefaultCommand(new Pose2d(-40, (54) * m, Rotation2d.fromDegrees((175) * m)), toPoint, 10, 67),
                                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                                new RunCommand(() -> toPoint.setTarget(new Pose2d(Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getX(), -65, 65), Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getY(), -65, 65), Rotation2d.fromDegrees(175)))).interruptOn(() -> !spindexer.hasSpace())), 5000),
                                        new GoToPointWithDefaultCommand(shootingPose, toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem).alongWith(new InstantCommand (()->Log.println(Log.ASSERT, "shooter aligned", "turret err: " + turret.getError())))
                                ), 29000).andThen(
                                new GoToPointWithDefaultCommand(new Pose2d(-36.7, (46) * m, Rotation2d.fromDegrees(0)), toPoint)
                        )
                )
        );
        waitForStart();
        while (opModeIsActive()){
            //for (int i = 0; i < 3; i++){
            //    Log.i("Motif", "ball" + StaticValues.getMotif(i));
            //}
            //telemetry.addData("motif!", Robot.motif.toString());
            telemetry.addData("shooter", spindexer.getDegrees());
            update();
        }
    }
}
