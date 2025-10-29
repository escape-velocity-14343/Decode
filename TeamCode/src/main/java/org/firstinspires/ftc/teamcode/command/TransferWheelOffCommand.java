package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class TransferWheelOffCommand extends CommandBase {
    TransferWheelSubsystem transferWheel;
    public TransferWheelOffCommand (TransferWheelSubsystem transferWheel){
        this.transferWheel = transferWheel;
        addRequirements(transferWheel);
    }
    @Override
    public void initialize() {
        transferWheel.off();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
