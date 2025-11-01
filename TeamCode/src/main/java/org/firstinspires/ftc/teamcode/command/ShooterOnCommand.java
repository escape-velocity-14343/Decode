package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;

public class ShooterOnCommand extends CommandBase {
    ShooterSubsystem shooterSubsystem;
    double targetVelocity = 750;
    public ShooterOnCommand(ShooterSubsystem shooter){
        this.shooterSubsystem = shooter;
        addRequirements(shooter);
    }
    public ShooterOnCommand(ShooterSubsystem shooter, double velocity){
        this.shooterSubsystem = shooter;
        this.targetVelocity = velocity;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooterSubsystem.setVelocity(targetVelocity);
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
