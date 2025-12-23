package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;

import org.firstinspires.ftc.teamcode.lib.Localizer;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PinpointSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConstants;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;

public class ShootWithDistCommand extends CommandBase {
    ShooterSubsystem shooterSubsystem;
    AprilTagSubsystem atag;
    Localizer pinpointSubsystem;
    double distance = 0;
    public ShootWithDistCommand(ShooterSubsystem shooter, AprilTagSubsystem atag){
        this.shooterSubsystem = shooter;
        this.atag = atag;
        addRequirements(shooter);
    }
    public ShootWithDistCommand(ShooterSubsystem shooter, Localizer pinpointSubsystem) {
        this.shooterSubsystem = shooter;
        this.pinpointSubsystem = pinpointSubsystem;
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
        if (pinpointSubsystem != null) {
            distance = pinpointSubsystem.getPose().getTranslation().getDistance(new Translation2d(StaticValues.goalPos.getX(), StaticValues.getM() * StaticValues.goalPos.getY()));
        }
        shooterSubsystem.shootFromDistance(StaticValues.tagDist);
    }
    @Override
    public void execute() {
    }
    @Override
    public boolean isFinished() {
        return shooterSubsystem.atVelocity();
    }
}
