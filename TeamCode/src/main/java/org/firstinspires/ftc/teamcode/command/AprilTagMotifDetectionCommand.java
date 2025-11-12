package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

public class AprilTagMotifDetectionCommand extends CommandBase {
    double ID;

    AprilTagSubsystem apriltag;
    public AprilTagMotifDetectionCommand(AprilTagSubsystem apriltag){
        this.apriltag = apriltag;
    }

    @Override
    public void initialize(){
        Log.i("AprilTagMotifDetectionCommand", "Starting motif detection: " + StaticValues.getMotif(0) + ", " + StaticValues.getMotif(1) + ", " + StaticValues.getMotif(2));
        StaticValues.setMotif((int)apriltag.detect(), 2);
        Log.i("AprilTagMotifDetectionCommand", "Detected motif after: " + StaticValues.getMotif(0) + ", " + StaticValues.getMotif(1) + ", " + StaticValues.getMotif(2));
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
