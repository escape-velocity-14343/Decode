package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
@TeleOp
public class PinpointTuner extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
//        pinpointSubsystem.reset();
        waitForStart();
        while(opModeIsActive()) {
            update();
            telemetry.addData("X: ", pinpointSubsystem.getPose().getX());
            telemetry.addData("Y: ", pinpointSubsystem.getPose().getY());
            telemetry.addData("Heading: ", pinpointSubsystem.getPose().getHeading());
            telemetry.addData("Forward Encoder Counts: ", pinpointSubsystem.getEncoderCounts()[0]);
            telemetry.addData("Left Encoder Counts: ", pinpointSubsystem.getEncoderCounts()[1]);

        }
        end();
    }
}
