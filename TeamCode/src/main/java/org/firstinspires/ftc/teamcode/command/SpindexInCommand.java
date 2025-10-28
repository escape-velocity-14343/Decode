package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class SpindexInCommand extends CommandBase {
    SpindexerSubsystem spindexer;
    int ballNum;
    public SpindexInCommand(SpindexerSubsystem spindexer, int ballNum){
        this.spindexer = spindexer;
        this.ballNum = ballNum;
        addRequirements(spindexer);
    }

    @Override
    public void initialize() {
        spindexer.intake(ballNum);
    }
    @Override
    public boolean isFinished() {
        return spindexer.isClose();
    }
}
