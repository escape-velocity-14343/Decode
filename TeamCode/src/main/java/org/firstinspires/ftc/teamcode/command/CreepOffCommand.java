package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;

import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

public class CreepOffCommand extends CommandBase {
    DefaultGoToPointCommand toPoint;
    public CreepOffCommand(DefaultGoToPointCommand toPoint){
        this.toPoint = toPoint;
    }

    @Override
    public void initialize() {
        StaticValues.setMaxSpeed(1);
        toPoint.setTarget(new Pose2d(new Translation2d(toPoint.getCurrentPose().getX(), toPoint.getCurrentPose().getY()), Rotation2d.fromDegrees((90)*StaticValues.getM())));
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
