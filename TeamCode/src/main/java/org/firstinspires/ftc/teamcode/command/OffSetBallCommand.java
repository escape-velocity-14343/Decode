package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

public class OffSetBallCommand extends CommandBase {
    int ballNumber;
    public OffSetBallCommand(int ballNumber) {
        this.ballNumber = ballNumber;
    }

    @Override
    public void initialize() {
        StaticValues.setBallOffset(ballNumber);
    }

    public boolean isFinished() {
        return true;
    }
}
