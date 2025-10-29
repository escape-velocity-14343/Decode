package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class SpindexInCommand extends CommandBase {
    SpindexerSubsystem spindexer;
    int ballNum;
    public SpindexInCommand(SpindexerSubsystem spindexer){
        this.spindexer = spindexer;
        addRequirements(spindexer);
    }

    @Override
    public void initialize() {
        spindexer.intakeAuto();
    }
    @Override
    public boolean isFinished() {
        return spindexer.isClose();
    }
}
