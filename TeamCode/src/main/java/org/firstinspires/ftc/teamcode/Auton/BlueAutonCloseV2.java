package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

@Autonomous(name = "Blue Auton Close V2", group = "Blue")
public class BlueAutonCloseV2 extends AutonCloseV2 {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.blueAlliance = true;
        StaticValues.setM(1);
        run(StaticValues.getM());
    }
}
