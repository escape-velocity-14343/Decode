package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.AprilTag.VisionConstants;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@TeleOp(name = "Exposure Tuner", group = "Test")
public class ExposureTuner extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        timer.reset();
        double lastExposure = 0;
        while(!aprilTag.isStreaming()||timer.milliseconds()>1000);

        while (opModeIsActive()) {
            if (VisionConstants.exposureMillis != lastExposure) {
                telemetry.addData("Camera has been initialized correctly: ",aprilTag.setExposure(VisionConstants.exposureMillis));
                telemetry.update();
            }
            lastExposure = VisionConstants.exposureMillis;
        }

    }
}
