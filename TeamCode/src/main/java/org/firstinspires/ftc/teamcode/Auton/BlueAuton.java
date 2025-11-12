package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@Autonomous(name = "Blue Auton", group = "Blue")
public class BlueAuton extends AutonV1{
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.blueAlliance = true;
        StaticValues.setM(1);
        run(StaticValues.getM());
    }
}
