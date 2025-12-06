package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

@Autonomous(name = "Blue Auton TW Auton", group = "Blue")
@Disabled
public class BlueTwAuton extends TwAuton{
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.blueAlliance = true;
        StaticValues.setM(1);
        run(StaticValues.getM());
    }
}
