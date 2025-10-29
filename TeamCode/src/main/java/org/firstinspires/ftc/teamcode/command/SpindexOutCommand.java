package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class SpindexOutCommand extends CommandBase {
    SpindexerSubsystem spindexer;
    String ballColor;
    public SpindexOutCommand(SpindexerSubsystem spindexer, String ballColor){
        this.spindexer = spindexer;
        this.ballColor = ballColor;
        addRequirements(spindexer);
    }

    @Override
    public void initialize() {
        spindexer.outakeAuto(ballColor);
    }
    @Override
    public boolean isFinished() {
        return spindexer.isClose();
    }
}
