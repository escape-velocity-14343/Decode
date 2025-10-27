package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class TransferWheelOnCommand extends CommandBase {
    public TransferWheelOnCommand (TransferWheelSubsystem transferWheel){
        transferWheel.on();
    }
    @Override
    public boolean isFinished() {
        return true;
    }

}
