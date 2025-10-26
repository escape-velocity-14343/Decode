package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class TransferWheelOffCommand extends CommandBase {
    public TransferWheelOffCommand (TransferWheelSubsystem transferWheel){
        transferWheel.off();
    }
}
