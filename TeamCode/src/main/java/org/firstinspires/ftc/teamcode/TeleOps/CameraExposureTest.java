package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.ExposureCycle;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;


@Autonomous(name = "Camera Exposure Test", group = "Test")
public class CameraExposureTest extends Robot {
    AprilTagSubsystem cam;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        aprilTag.waitForSetExposure(67,67);

        waitForStart();

        CommandScheduler.getInstance().schedule(
                new ExposureCycle(aprilTag)
        );


        while (!isStopRequested()) {
            update();
        }
        CommandScheduler.getInstance().reset();
    }
}