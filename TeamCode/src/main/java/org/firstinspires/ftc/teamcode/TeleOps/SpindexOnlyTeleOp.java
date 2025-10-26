package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.RobotSpindexOnly;

@TeleOp (name = "SpindexOnlyTeleOp", group = "Testing")
public class SpindexOnlyTeleOp extends RobotSpindexOnly {
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            spindexer.periodic();
            telemetry.update();
        }
    }
}
