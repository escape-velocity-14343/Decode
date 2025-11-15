package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

@Autonomous(name = "Red Auton Far", group = "Red")
public class RedAutonFar extends AutonFar {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.blueAlliance = false;
        StaticValues.setM(-1);
        run(StaticValues.getM());
    }
}
