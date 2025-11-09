package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.StaticValues;

public class AprilTagMotifDetectionCommand extends CommandBase {
    public static double ID;

    AprilTagSubsystem apriltag;
    public AprilTagMotifDetectionCommand(AprilTagSubsystem apriltag){
        this.apriltag = apriltag;
    }

    @Override
    public void initialize(){
        StaticValues.setMotif((int)apriltag.detect(), 2);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
