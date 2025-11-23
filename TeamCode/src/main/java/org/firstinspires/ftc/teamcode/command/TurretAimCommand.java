package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PinpointSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretSubsystem;

public class TurretAimCommand extends CommandBase {
    TurretSubsystem turret;
    PinpointSubsystem pinpointSubsystem;
    public TurretAimCommand(TurretSubsystem turret, PinpointSubsystem pinpointSubsystem){
        this.turret = turret;
        this.pinpointSubsystem = pinpointSubsystem;
        addRequirements(turret);
    }
    @Override
    public void initialize() {

    }
}
