package org.firstinspires.ftc.teamcode.Auton;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.command.AprilTagMotifDetectionCommand;
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.TimeoutCommand;
import org.firstinspires.ftc.teamcode.command.TurretAimDefaultCommand;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.turret.ConstantsTurret;


public abstract class TwAuton extends Robot {
    DefaultGoToPointCommand toPoint;
    Pose2d shootingPose = new Pose2d(-33, 8, Rotation2d.fromDegrees(180));

    public void run(int m) throws InterruptedException{
        initialize();
        StaticValues.resetMotif();
        StaticValues.resetArtifacts();
        turret.setTargetPosition(ConstantsTurret.obeliskPosFar * m);
        setTurretCamExposure();

        pinpointSubsystem.setPose(new Pose2d(-57.3, 18.8 * m, Rotation2d.fromDegrees(180)));
        toPoint = new DefaultGoToPointCommand(drive, pinpointSubsystem, new Pose2d(-58, 21 * m, Rotation2d.fromDegrees(180)));
        drive.setDefaultCommand(toPoint);
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().schedule(
                new TimeoutCommand(
                        new SequentialCommandGroup(
                                new AprilTagMotifDetectionCommand(aprilTag),
                                new InstantCommand(() -> turret.setDefaultCommand(new TurretAimDefaultCommand(aprilTag, turret, pinpointSubsystem))),
                                new GoToPointWithDefaultCommand(shootingPose, toPoint),
                                new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem),

                                new GoToPointWithDefaultCommand(new Pose2d(-36.7, (28) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67),
                                new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                        new RunCommand(()->toPoint.setTarget(new Pose2d(Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getX(), -65, 65), Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getY(), -67, 67), Rotation2d.fromDegrees(90*m)))).interruptOn(()->!spindexer.hasSpace())), 5000),
                                new GoToPointWithDefaultCommand(shootingPose, toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                                new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem),

                                new GoToPointWithDefaultCommand(new Pose2d(-43, (67) * m, Rotation2d.fromDegrees((180) * m)), toPoint, 15, 67),
                                new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                        new RunCommand(()->toPoint.setTarget(new Pose2d(Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getX(), -65, 65), Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getY(), -67, 67), Rotation2d.fromDegrees(180)))).interruptOn(()->!spindexer.hasSpace())), 5000),
                                new GoToPointWithDefaultCommand(shootingPose, toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                                new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem),

                                new GoToPointWithDefaultCommand(new Pose2d(-43, (67) * m, Rotation2d.fromDegrees((180) * m)), toPoint, 15, 67),
                                new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                        new RunCommand(()->toPoint.setTarget(new Pose2d(Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getX(), -65, 65), Range.clip(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()).getY(), -67, 67), Rotation2d.fromDegrees(180)))).interruptOn(()->!spindexer.hasSpace())), 5000),
                                new GoToPointWithDefaultCommand(shootingPose, toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                                new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem)
                        ), 29000).andThen(
                        new GoToPointWithDefaultCommand(new Pose2d(-36.7, (46) * m, Rotation2d.fromDegrees(0)), toPoint)
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
