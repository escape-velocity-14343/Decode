package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;

public class TransferArmUpCommand extends CommandBase {
    TransferArmSubsystem transferArm;
    ElapsedTime timer = new ElapsedTime();
    public TransferArmUpCommand(TransferArmSubsystem transferArm) {
        this.transferArm = transferArm;
        addRequirements(transferArm);
    }
    @Override
    public void initialize() {
        transferArm.up();
        timer.reset();
    }


    @Override
    public boolean isFinished() {
        return timer.milliseconds() > StaticValues.TRANSFER_ARM_MILLIS*2;
    }
}
