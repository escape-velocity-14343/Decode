package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class TransferArmDownCommand extends CommandBase {
    ElapsedTime timer = new ElapsedTime();
    public TransferArmDownCommand (TransferArmSubsystem transferArm){
        transferArm.down();
        timer.reset();
    }
    @Override
    public boolean isFinished() {
        return timer.milliseconds() > 250;
    }
}
