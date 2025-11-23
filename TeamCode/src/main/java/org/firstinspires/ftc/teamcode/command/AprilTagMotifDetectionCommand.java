package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

public class AprilTagMotifDetectionCommand extends CommandBase {
    double ID;
    int obelisk;

    AprilTagSubsystem apriltag;
    ElapsedTime timer = new ElapsedTime();
    public AprilTagMotifDetectionCommand(AprilTagSubsystem apriltag){
        this.apriltag = apriltag;
    }

    @Override
    public void initialize(){
        timer.reset();
    }
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
    public boolean isFinished() {
        if (timer.milliseconds()>1670) {
            StaticValues.resetMotif();
            StaticValues.setMotif(0, 2);
            return true;
        }
        return apriltag.detect() != -1;
    }

    @Override
    public void end(boolean interrupted){
        StaticValues.resetMotif();
        if (apriltag.detect() != -1)
            StaticValues.setMotif(apriltag.detect(), 2);
        else
            StaticValues.setMotif(0, 2);
    }
}
