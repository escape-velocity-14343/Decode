package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;

public class TransferArmDownCommand extends CommandBase {
    ElapsedTime timer = new ElapsedTime();
    TransferArmSubsystem transferArmSubsystem;
    public TransferArmDownCommand (TransferArmSubsystem transferArm){
        this.transferArmSubsystem = transferArm;
        addRequirements(transferArm);
    }
    @Override
    public void initialize() {
        timer.reset();
        transferArmSubsystem.down();
    }
    @Override
    public boolean isFinished() {
        return timer.milliseconds() > 250;
    }
}
