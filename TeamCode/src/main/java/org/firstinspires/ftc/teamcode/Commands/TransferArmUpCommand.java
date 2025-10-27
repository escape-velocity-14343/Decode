package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;

public class TransferArmUpCommand extends CommandBase {
    ElapsedTime timer = new ElapsedTime();
    public TransferArmUpCommand(TransferArmSubsystem transferArm) {
        timer.reset();
        transferArm.up();
    }

    @Override
    public boolean isFinished() {
        return timer.milliseconds() > 250;
    }
}
