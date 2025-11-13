package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
@Configurable
public class CreepCommand extends CommandBase {
    DefaultGoToPointCommand toPoint;
    ElapsedTime timer = new ElapsedTime();
    int m;

    public static double creepRate = 5;
    SpindexerSubsystem spindexer;
    public CreepCommand(DefaultGoToPointCommand toPoint, int m, SpindexerSubsystem spindexer) {
        this.toPoint = toPoint;
        this.m = m;
        this.spindexer = spindexer;
    }

    @Override
    public void initialize() {
        toPoint.setTarget(new Pose2d(toPoint.getCurrentPose().getX(), toPoint.getCurrentPose().getY() + (creepRate * timer.seconds() * m), new Rotation2d(toPoint.getCurrentPose().getHeading())));
        timer.reset();
    }
    @Override
    public void execute() {
        toPoint.setTarget(new Pose2d(toPoint.getCurrentPose().getX(), toPoint.getCurrentPose().getY() + (creepRate * timer.seconds() * m), new Rotation2d(toPoint.getCurrentPose().getHeading())));
        timer.reset();
    }

    @Override
    public boolean isFinished() {
        return spindexer.getRemainingSpace()==0;
    }
}
