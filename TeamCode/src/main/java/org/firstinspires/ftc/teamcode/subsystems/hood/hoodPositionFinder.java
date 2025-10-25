package org.firstinspires.ftc.teamcode.subsystems.hood;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "hoodPositionFinder", group = "LinearOpMode")
public class hoodPositionFinder extends LinearOpMode {
    private HoodSubsystem hood;
    public void runOpMode(){
        hood = new HoodSubsystem(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            hood.manual(gamepad1, telemetry);
            telemetry.update();
            sleep(500);
        }
    }

}
