package org.firstinspires.ftc.teamcode.Auton;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@Autonomous
public class IntakeAlignTest extends Robot{
    DefaultGoToPointCommand toPoint;
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        drive.setDefaultCommand(toPoint);
        toPoint = new DefaultGoToPointCommand(drive, pinpointSubsystem, intakeCam);
        CommandScheduler.getInstance().schedule(

        );
        waitForStart();
        while (opModeIsActive()) {
            update();
        }
    }
}
