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
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.turret.ConstantsTurret;


public abstract class AutonFar extends Robot {
    DefaultGoToPointCommand toPoint;

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
                new SequentialCommandGroup(
                        new AprilTagMotifDetectionCommand(aprilTag),
                        new GoToPointWithDefaultCommand(new Pose2d(-2, 12 * m, Rotation2d.fromDegrees(180)), toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint),

                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                new GoToPointWithDefaultCommand(new Pose2d(-33, (28) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67)
                                        .andThen(new GoToPointWithDefaultCommand(new Pose2d(-33, (36) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67)
                                                .andThen(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<3))
                                                .andThen(new GoToPointWithDefaultCommand(new Pose2d(-33, (42) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67))
                                                .andThen(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<2))
                                                .andThen(new GoToPointWithDefaultCommand(new Pose2d(-33, (64) * m, Rotation2d.fromDegrees((90) * m)), toPoint, 15, 67)))),
                        new GoToPointWithDefaultCommand(new Pose2d(-2, 12, Rotation2d.fromDegrees(180)), toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint),

                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(
                                new GoToPointWithDefaultCommand(new Pose2d(-43, (67) * m, Rotation2d.fromDegrees((180) * m)), toPoint, 15, 67)
                                        .andThen(new GoToPointWithDefaultCommand(new Pose2d(-45, (67) * m, Rotation2d.fromDegrees((180) * m)), toPoint, 15, 67)
                                                .andThen(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<3))
                                                .andThen(new GoToPointWithDefaultCommand(new Pose2d(-51, (67) * m, Rotation2d.fromDegrees((180) * m)), toPoint, 15, 67))
                                                .andThen(new WaitUntilCommand(()-> spindexer.getRemainingSpace()<2))
                                                .andThen(new GoToPointWithDefaultCommand(new Pose2d(-57, (67) * m, Rotation2d.fromDegrees((180) * m)), toPoint, 15, 67)))),
                        new GoToPointWithDefaultCommand(new Pose2d(-2, 12, Rotation2d.fromDegrees(180)), toPoint).alongWith(new InstantCommand(() -> turret.setTargetPosition(ConstantsTurret.shootingPosFar))),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint)
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
