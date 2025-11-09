package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@Autonomous(name = "Red Auton", group = "Red")
public class RedAuton extends AutonV1{
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.blueAlliance = false;
        run(-1);
    }
}
