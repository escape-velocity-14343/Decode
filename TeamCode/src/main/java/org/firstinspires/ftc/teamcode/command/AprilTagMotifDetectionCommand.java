package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.onbotjava.handlers.admin.ResetOnBotJava;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class AprilTagMotifDetectionCommand extends CommandBase {
    double ID;

    AprilTagSubsystem apriltag;
    public AprilTagMotifDetectionCommand(AprilTagSubsystem apriltag){
        this.apriltag = apriltag;
    }

    @Override
    public void initialize() {
        int num = (int) apriltag.detect();
        if (num == 0) {
            Robot.motif1 = 2;
        } else if (num == 1) {
            Robot.motif2 = 2;
        } else if (num == 3) {
            Robot.motif3 = 2;
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
