package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

@Autonomous
public class RedAuton extends AutonV1 {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.blueAlliance = false;
        run(-1, 12, -4);
    }
}
