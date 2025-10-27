package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;

public class ShooterOnCommand extends CommandBase {
    public ShooterOnCommand(ShooterSubsystem shooter){
        addRequirements(shooter);
        shooter.on();
    }
}
