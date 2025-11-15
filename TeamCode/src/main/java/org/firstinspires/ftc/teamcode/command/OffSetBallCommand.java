package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

public class OffSetBallCommand extends CommandBase {
    int ballNumber;
    public OffSetBallCommand(int ballNumber) {
        this.ballNumber = ballNumber;
    }

    @Override
    public void initialize() {
        Log.i("OffSetBallCommand", "Setting ball offset to ball number: " + ballNumber);
        StaticValues.setBallOffset(ballNumber);
        Log.i("OffSetBallCommand", "Ball offset set to: " + StaticValues.getBallOffset());
    }

    public boolean isFinished() {
        return true;
    }
}
