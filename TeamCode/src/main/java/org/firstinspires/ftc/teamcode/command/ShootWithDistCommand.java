package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConstants;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;

public class ShootWithDistCommand extends CommandBase {
    ShooterSubsystem shooterSubsystem;
    AprilTagSubsystem atag;
    double distance = 0;
    public ShootWithDistCommand(ShooterSubsystem shooter, AprilTagSubsystem atag){
        this.shooterSubsystem = shooter;
        this.atag = atag;
        addRequirements(shooter);
    }
    public ShootWithDistCommand(ShooterSubsystem shooter, double distance){
        this.shooterSubsystem = shooter;
        this.distance = distance;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        if (atag != null) {
            distance = atag.getDistance();
        }
        shooterSubsystem.shootFromDistance(distance);
    }
    @Override
    public boolean isFinished() {
        return shooterSubsystem.atVelocity();
    }
}
