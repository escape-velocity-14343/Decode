package org.firstinspires.ftc.teamcode.Auton;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.AprilTagMotifDetectionCommand;
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.MotifShoot21Command;
import org.firstinspires.ftc.teamcode.command.MotifShoot22Command;
import org.firstinspires.ftc.teamcode.command.MotifShoot23Command;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.StaticValues;

@Autonomous(name = "AutonV1", group = "Auton")
public class AutonV1 extends Robot {
    DefaultGoToPointCommand toPoint;
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        pinpointSubsystem.setPose(new Pose2d(60,72, Rotation2d.fromDegrees(-135)));
        toPoint = new DefaultGoToPointCommand(drive, pinpointSubsystem, new Pose2d(0, 0, new Rotation2d(0)));
        drive.setDefaultCommand(toPoint);
        
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new GoToPointWithDefaultCommand(new Pose2d(60-15, 72-15, Rotation2d.fromDegrees(-135)), toPoint),
                        new GoToPointWithDefaultCommand(new Pose2d(60-25, 72-25, Rotation2d.fromDegrees(-225)), toPoint),
                        //new WaitCommand(5000),
                        new AprilTagMotifDetectionCommand(aprilTag),
                        new GoToPointWithDefaultCommand(new Pose2d(60-25, 72-25, Rotation2d.fromDegrees(-125)), toPoint),
                        new ConditionalCommand(new MotifShoot21Command(spindexer, shooter, transferWheel, transferArm), new ConditionalCommand(new MotifShoot22Command(spindexer, shooter, transferWheel, transferArm), new ConditionalCommand(new MotifShoot23Command(spindexer, shooter, transferWheel, transferArm), new MotifShoot21Command(spindexer, shooter, transferWheel, transferArm), () -> aprilTag.ID == 23), () -> aprilTag.ID == 22), () -> aprilTag.ID == 21),

                        //new GoToPointWithDefaultCommand(new Pose2d(12, 28, Rotation2d.fromDegrees(90)), toPoint),
                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(0, 35+18, Rotation2d.fromDegrees(90)), toPoint)),
                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(0, 40+18, Rotation2d.fromDegrees(90)), toPoint)),
                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(0, 45+18, Rotation2d.fromDegrees(90)), toPoint)),
                        new GoToPointWithDefaultCommand(new Pose2d(60-30, 72-25, Rotation2d.fromDegrees(-125)), toPoint),
                        new ConditionalCommand(new MotifShoot21Command(spindexer, shooter, transferWheel, transferArm), new ConditionalCommand(new MotifShoot22Command(spindexer, shooter, transferWheel, transferArm), new ConditionalCommand(new MotifShoot23Command(spindexer, shooter, transferWheel, transferArm), new MotifShoot21Command(spindexer, shooter, transferWheel, transferArm), () -> aprilTag.ID == 23), () -> aprilTag.ID == 22), () -> aprilTag.ID == 21),

                        //new GoToPointWithDefaultCommand(new Pose2d(-5-24, 38+22, Rotation2d.fromDegrees(90)), toPoint),
                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-24-2, 35+17, Rotation2d.fromDegrees(90)), toPoint)),
                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-24 - 2, 40+17, Rotation2d.fromDegrees(90)), toPoint)),
                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-24 - 2, 45+17, Rotation2d.fromDegrees(90)), toPoint)),
                        new GoToPointWithDefaultCommand(new Pose2d(60-30, 72-25, Rotation2d.fromDegrees(-125)), toPoint),
                        new ConditionalCommand(new MotifShoot21Command(spindexer, shooter, transferWheel, transferArm), new ConditionalCommand(new MotifShoot22Command(spindexer, shooter, transferWheel, transferArm), new ConditionalCommand(new MotifShoot23Command(spindexer, shooter, transferWheel, transferArm), new MotifShoot21Command(spindexer, shooter, transferWheel, transferArm), () -> aprilTag.ID == 23), () -> aprilTag.ID == 22), () -> aprilTag.ID == 21),

                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-24-24 - 2, 35+18, Rotation2d.fromDegrees(90)), toPoint)),
                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-24-24 - 2, 40+18, Rotation2d.fromDegrees(90)), toPoint)),
                        new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(-24-24 - 2, 45+18, Rotation2d.fromDegrees(90)), toPoint)),
                        new GoToPointWithDefaultCommand(new Pose2d(60-30, 72-25, Rotation2d.fromDegrees(-125)), toPoint),
                        new ConditionalCommand(new MotifShoot21Command(spindexer, shooter, transferWheel, transferArm), new ConditionalCommand(new MotifShoot22Command(spindexer, shooter, transferWheel, transferArm), new ConditionalCommand(new MotifShoot23Command(spindexer, shooter, transferWheel, transferArm), new MotifShoot21Command(spindexer, shooter, transferWheel, transferArm), () -> aprilTag.ID == 23), () -> aprilTag.ID == 22), () -> aprilTag.ID == 21)
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
