package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class SpindexGoToCommand extends CommandBase {
    SpindexerSubsystem spindexer;
    double pos;
    public SpindexGoToCommand(SpindexerSubsystem spindexer, double pos){
        this.spindexer = spindexer;
        this.pos = pos;
        addRequirements(spindexer);
    }

    @Override
    public void initialize(){
        spindexer.setTargetPosition(pos);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
