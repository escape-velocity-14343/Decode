package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class SpindexOutCommand extends CommandBase {
    SpindexerSubsystem spindexer;
    int ballNum;
    public SpindexOutCommand(SpindexerSubsystem spindexer, int ballNum){
        this.spindexer = spindexer;
        this.ballNum = ballNum;
        addRequirements(spindexer);
    }

    @Override
    public void initialize() {
        spindexer.outake(ballNum);
    }
    @Override
    public boolean isFinished() {
        return spindexer.isClose();
    }
}
