package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

@Autonomous
public class BlueAutonomousFromThePuyallup extends AutonV1 {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.blueAlliance=true;
        run(1, 0,0);
    }
}
