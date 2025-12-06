package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@Autonomous(name = "Red Auton Close V2", group = "Red")
public class RedAutonClose extends AutonCloseV2 {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.blueAlliance = false;
        StaticValues.setM(-1);
        run(StaticValues.getM());
    }
}
