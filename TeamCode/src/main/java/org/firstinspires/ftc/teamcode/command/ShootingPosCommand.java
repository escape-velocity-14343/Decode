package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

public class ShootingPosCommand extends CommandBase {
    DefaultGoToPointCommand toPoint;
    public ShootingPosCommand(DefaultGoToPointCommand toPoint){
        this.toPoint = toPoint;
    }

    public void initialize(){
        toPoint.setTarget(new Pose2d(23, (12)*StaticValues.getM(), Rotation2d.fromDegrees(135)));
    }

    public boolean isFinished(){
        return true;
    }
}
