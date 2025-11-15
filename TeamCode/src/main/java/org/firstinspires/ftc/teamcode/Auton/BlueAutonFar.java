package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

@Autonomous(name = "Blue Auton Far", group = "Blue")
public class BlueAutonFar extends AutonFar {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.blueAlliance = true;
        StaticValues.setM(1);
        run(StaticValues.getM());
    }
}
