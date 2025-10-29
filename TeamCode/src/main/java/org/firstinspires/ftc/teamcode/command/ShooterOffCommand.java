package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;

public class ShooterOffCommand extends CommandBase {
    ShooterSubsystem shooterSubsystem;
    public ShooterOffCommand(ShooterSubsystem shooter){
        this.shooterSubsystem = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooterSubsystem.setPower(0);
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
