package org.firstinspires.ftc.teamcode.subsystems.shooter;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@TeleOp (name = "Shooter PID Test", group = "Test")
public class ShooterPIDTest extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            update();
            shooter.setVelocity(ShooterConstants.testVelocity);
            shooter.periodic();
        }
    }
}
