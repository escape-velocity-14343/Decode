package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class TransferArmDownCommand extends CommandBase {
    public TransferArmDownCommand (TransferArmSubsystem transferArm){
        transferArm.down();
    }
}
