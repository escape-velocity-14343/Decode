package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

@TeleOp(group="Test")
@Configurable
public class GTPointTest extends Robot {
    public static double x = 0;
    public static double y = 0;
    public static double rot = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        pinpointSubsystem.setPose(new Pose2d( 0, 0, new Rotation2d(0)));
        waitForStart();

        DefaultGoToPointCommand gtpc = new DefaultGoToPointCommand(drive, pinpointSubsystem, new Pose2d(x, y, Rotation2d.fromDegrees(rot)));
        CommandScheduler.getInstance().schedule(gtpc);

        while (!isStopRequested()){
            update();

            gtpc.setTarget(new Pose2d(x, y, Rotation2d.fromDegrees(rot)));

            telemetry.addData("x", pinpointSubsystem.getPose().getX());
            telemetry.addData("y", pinpointSubsystem.getPose().getY());
            telemetry.addData("heading", pinpointSubsystem.getPose().getHeading());

        }
        end();
    }
}