package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;

public class ShooterOnCommand extends CommandBase {
    ShooterSubsystem shooterSubsystem;
    public ShooterOnCommand(ShooterSubsystem shooter){
        this.shooterSubsystem = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooterSubsystem.on();
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
