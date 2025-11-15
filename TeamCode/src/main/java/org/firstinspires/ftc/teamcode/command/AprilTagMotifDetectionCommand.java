package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

public class AprilTagMotifDetectionCommand extends CommandBase {
    double ID;
    int obelisk;

    AprilTagSubsystem apriltag;
    public AprilTagMotifDetectionCommand(AprilTagSubsystem apriltag){
        this.apriltag = apriltag;
    }

    //@Override
    //public void initialize(){
    //    StaticValues.resetMotif();
    //    Log.i("AprilTagMotifDetectionCommand", "Starting motif detection: " + StaticValues.getMotif(0) + ", " + StaticValues.getMotif(1) + ", " + StaticValues.getMotif(2));
    //    this.obelisk = apriltag.detect();
    //    Log.i("AprilTagMotifDetectionCommand", "Detected: " + obelisk);
    //
    //    Log.i("AprilTagMotifDetectionCommand", "Detected motif after: " + StaticValues.getMotif(0) + ", " + StaticValues.getMotif(1) + ", " + StaticValues.getMotif(2));
    //}
    //
    //@Override
    //public void execute(){
    //    StaticValues.resetMotif();
    //    Log.i("AprilTagMotifDetectionCommand", "Starting motif detection: " + StaticValues.getMotif(0) + ", " + StaticValues.getMotif(1) + ", " + StaticValues.getMotif(2));
    //    this.obelisk = apriltag.detect();
    //    Log.i("AprilTagMotifDetectionCommand", "Detected: " + obelisk);
    //    //StaticValues.setMotif(obelisk, 2);
    //    Log.i("AprilTagMotifDetectionCommand", "Detected motif after: " + StaticValues.getMotif(0) + ", " + StaticValues.getMotif(1) + ", " + StaticValues.getMotif(2));
    //}

    @Override
    public boolean isFinished(){
        return apriltag.detect() != -1;
    }

    @Override
    public void end(boolean interrupted){
        StaticValues.resetMotif();
        StaticValues.setMotif(apriltag.detect(), 2);
    }
}
