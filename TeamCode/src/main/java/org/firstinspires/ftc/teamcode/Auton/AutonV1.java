package org.firstinspires.ftc.teamcode.Auton;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.AprilTagMotifDetectionCommand;
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.MotifShootCommand;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

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
//                        new InstantCommand(()->turret.setposition(45)),
//                        new AprilTagMotifDetectionCommand(aprilTag),
//                        new InstantCommand(()->turret.setposition(0)),
                        new MotifShootCommand(spindexer, shooter, transferWheel, transferArm),
                        new GoToPointWithDefaultCommand(new Pose2d(60-15-12, 72-15+26, Rotation2d.fromDegrees(90)), toPoint),
                        new GoToPointWithDefaultCommand(new Pose2d(60-15-12, 72-15+26+24, Rotation2d.fromDegrees(90)), toPoint)
                )
        );
        waitForStart();
        while (opModeIsActive()){
            update();
        }
    }
}
