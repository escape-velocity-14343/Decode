package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
@TeleOp(name = "TurretPosFinder", group = "Test")
public class TurretPosFinder extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            turret.periodic();
            telemetry.update();
        }
        end();
    }
}
