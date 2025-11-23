package org.firstinspires.ftc.teamcode.TeleOps;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.DefaultDriveCommand;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
@Configurable
@TeleOp(name = "VelocityCompTest", group = "Test")
public class VelocityCompTest extends Robot {
    static double targetVelocity = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        pinpointSubsystem.reset();
        waitForStart();
        CommandScheduler.getInstance().schedule(new DefaultDriveCommand(drive, ()->-1, ()->0, ()->0));
        while (pinpointSubsystem.getVelocity().getTranslation().getNorm()<targetVelocity && opModeIsActive()) {
            update();
        }
        Log.i("VelocityCompTest", "Reached target velocity: " + pinpointSubsystem.getVelocity().getTranslation().getNorm());
        CommandScheduler.getInstance().schedule(new DefaultDriveCommand(drive, ()->0, ()->0, ()->0));
        double initialX = pinpointSubsystem.getPose().getX();
        while (pinpointSubsystem.getVelocity().getTranslation().getNorm()>0.1 && opModeIsActive()) {
            update();
        }
        Log.i("VelocityCompTest", "Stopped. Distance traveled: " + (pinpointSubsystem.getPose().getX() - initialX));
    }
}
