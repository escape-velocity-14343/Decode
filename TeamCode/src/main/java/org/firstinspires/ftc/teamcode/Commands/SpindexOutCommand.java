package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class SpindexOutCommand extends CommandBase {
    SpindexerSubsystem spindexer;
    public SpindexOutCommand(SpindexerSubsystem spindexer, int ballNum){
        this.spindexer = spindexer;
        spindexer.outake(ballNum);
    }
    @Override
    public boolean isFinished() {
        //return spindexer.isClose(targetPosition, spindexerEncoder.getDegrees(), 5);
        return true;
    }
}
