package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class TransferWheelOnCommand extends CommandBase {
    TransferWheelSubsystem transferWheel;
    public TransferWheelOnCommand (TransferWheelSubsystem transferWheel){
        this.transferWheel = transferWheel;
        addRequirements(transferWheel);
    }
    @Override
    public void initialize() {
        transferWheel.on();
    }
    @Override
    public boolean isFinished() {
        return true;
    }

}
