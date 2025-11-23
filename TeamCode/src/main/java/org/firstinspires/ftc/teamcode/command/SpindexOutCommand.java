package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class SpindexOutCommand extends CommandBase {
    SpindexerSubsystem spindexer;
    int ballColor;
    int ballNum;

    public SpindexOutCommand(SpindexerSubsystem spindexer, int ballColor, int ballNum) {
        this.spindexer = spindexer;
        this.ballColor = ballColor;
        this.ballNum = ballNum;
        addRequirements(spindexer);
    }


    @Override
    public void initialize() {
         if (ballColor == -1){
            Log.i("SPINDEX OUT COMMAND", "motif is: " + StaticValues.getMotif(0) +  StaticValues.getMotif(1) + StaticValues.getMotif(2) + " with color " + StaticValues.getMotif(ballNum));
            spindexer.outakeAuto(StaticValues.getMotif(ballNum));
        } else {
            spindexer.outakeAuto(ballColor);
        }
    }
    @Override
    public boolean isFinished() {
        if (spindexer.isClose()){
            spindexer.setPower(0);
            return true;
        }
        return false;
    }
}
