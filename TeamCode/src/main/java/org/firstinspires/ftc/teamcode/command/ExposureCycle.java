package org.firstinspires.ftc.teamcode.command;


import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;


public class ExposureCycle extends CommandBase {
    AprilTagSubsystem cam;
    int exposureMillis = 100;
    boolean lastExposureSuccess = false;
    public ExposureCycle(AprilTagSubsystem camera) {
        cam = camera;
        addRequirements(cam);
    }
    @Override
    public void initialize() {
        cam.setExposure(exposureMillis);
    }
    @Override
    public void execute() {
        if (lastExposureSuccess) {
            cam.saveFrame("Exposure: " + exposureMillis + "ms, " + "last exposure: " + cam.getExposure());
            exposureMillis+=50;
        }
        lastExposureSuccess = cam.setExposure(exposureMillis);
    }
    @Override
    public void end(boolean wasInterrupted) {
        //reset to original exposure (ftcdash)
        cam.setExposure();
    }
    @Override
    public boolean isFinished() {
        return exposureMillis >= 20000;
    }
}