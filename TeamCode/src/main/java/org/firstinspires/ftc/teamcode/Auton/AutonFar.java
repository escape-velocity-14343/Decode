package org.firstinspires.ftc.teamcode.Auton;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.AprilTagMotifDetectionCommand;
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.turret.ConstantsTurret;

@Autonomous(name = "AutonFar", group = "Auton")
public abstract class AutonFar extends Robot {
    DefaultGoToPointCommand toPoint;

    public void run(int m) throws InterruptedException{
        initialize();
        StaticValues.resetMotif();
        StaticValues.resetArtifacts();
        turret.setTargetPosition(ConstantsTurret.obeliskPosFar);
        timer.reset();
        while(!aprilTag.isStreaming()||timer.milliseconds()>1000);
        aprilTag.waitForSetExposure(1000,1000);

        pinpointSubsystem.setPose(new Pose2d(-57.3, 18.8, Rotation2d.fromDegrees(180)));
        toPoint = new DefaultGoToPointCommand(drive, pinpointSubsystem, new Pose2d(-57.3, 18.8, Rotation2d.fromDegrees(180)));
        drive.setDefaultCommand(toPoint);
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new AprilTagMotifDetectionCommand(aprilTag),
                        new GoToPointWithDefaultCommand(new Pose2d(-38, 11, Rotation2d.fromDegrees(180)), toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint),

                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                new GoToPointWithDefaultCommand(new Pose2d(-33, (28) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67)
                                        .andThen(new GoToPointWithDefaultCommand(new Pose2d(-33, (36) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67)
                                                .andThen(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<3))
                                                .andThen(new GoToPointWithDefaultCommand(new Pose2d(-33, (42) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67))
                                                .andThen(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<2))
                                                .andThen(new GoToPointWithDefaultCommand(new Pose2d(-33, (52) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67)))),
                        new GoToPointWithDefaultCommand(new Pose2d(-38, 11, Rotation2d.fromDegrees(180)), toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint),

                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                new GoToPointWithDefaultCommand(new Pose2d(-43, (67) * m, Rotation2d.fromDegrees((180) * m)), toPoint, 15, 67)
                                        .andThen(new GoToPointWithDefaultCommand(new Pose2d(-45, (67) * m, Rotation2d.fromDegrees((180) * m)), toPoint, 15, 67)
                                                .andThen(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<3))
                                                .andThen(new GoToPointWithDefaultCommand(new Pose2d(-51, (67) * m, Rotation2d.fromDegrees((180) * m)), toPoint, 15, 67))
                                                .andThen(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<2))
                                                .andThen(new GoToPointWithDefaultCommand(new Pose2d(-57, (67) * m, Rotation2d.fromDegrees((180) * m)), toPoint, 15, 67)))),
                        new GoToPointWithDefaultCommand(new Pose2d(-38, 11, Rotation2d.fromDegrees(180)), toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint)
                )
        );
    }
}
