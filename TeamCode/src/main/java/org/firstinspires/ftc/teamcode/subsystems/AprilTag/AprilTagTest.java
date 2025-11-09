package org.firstinspires.ftc.teamcode.subsystems.AprilTag;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(group = "Test")
public class AprilTagTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AprilTagSubsystem aprilTagSubsystem = new AprilTagSubsystem(hardwareMap, telemetry);
        while(!aprilTagSubsystem.isStreaming());
        aprilTagSubsystem.waitForSetExposure(1000,1000);
        waitForStart();
        while (opModeIsActive()) {
            aprilTagSubsystem.periodic();
            telemetry.update();
        }
    }
}
