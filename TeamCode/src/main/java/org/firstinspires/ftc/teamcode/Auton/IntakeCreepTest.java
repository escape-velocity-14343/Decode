package org.firstinspires.ftc.teamcode.Auton;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.CreepOffCommand;
import org.firstinspires.ftc.teamcode.command.CreepOnCommand;
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.TimeoutCommand;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

@Autonomous
public class IntakeCreepTest extends Robot {
    DefaultGoToPointCommand toPoint;
    @Override
    public void runOpMode() {
        initialize();
        drive.setDefaultCommand(toPoint);
        toPoint = new DefaultGoToPointCommand(drive, pinpointSubsystem, new Pose2d(60,(48), Rotation2d.fromDegrees(135)));
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new InstantCommand(() -> StaticValues.setMaxSpeed(0.25)),
                        new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor).alongWith(new GoToPointWithDefaultCommand(new Pose2d(new Translation2d(toPoint.getCurrentPose().getX(), (67)*StaticValues.getM()), Rotation2d.fromDegrees((90)*StaticValues.getM())), toPoint)),5000),//.interruptOn(()->spindexer.getRemainingSpace()<1),
                        new InstantCommand(() -> StaticValues.setMaxSpeed(1))
                )
        );
        waitForStart();
        while (opModeIsActive()) {
            update();
        }
    }
}
