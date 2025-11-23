package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.robocol.Command;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class SpindexPosSetCommand extends CommandBase {
    SpindexerSubsystem spindexer;
    int position;

    public SpindexPosSetCommand(SpindexerSubsystem spindexer, int position) {
        this.spindexer = spindexer;
        this.position = position;
        addRequirements(spindexer);
    }

    @Override
    public void initialize() {
        spindexer.outake(position);
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
