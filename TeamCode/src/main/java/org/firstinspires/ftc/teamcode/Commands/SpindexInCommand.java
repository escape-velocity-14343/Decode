package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class SpindexInCommand extends CommandBase {
    SpindexerSubsystem spindexerSubsystem;
    public SpindexInCommand(SpindexerSubsystem spindexer, int ballNum){
        spindexerSubsystem = spindexer;
        spindexer.intake(ballNum);
    }
    @Override
    public boolean isFinished() {
        return spindexerSubsystem.isClose();
    }
}
