package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;

public class TransferArmUpCommand extends CommandBase {
    public TransferArmUpCommand(TransferArmSubsystem transferArm){
        transferArm.up();
    }
}
