package org.firstinspires.ftc.teamcode.lib;

import android.util.Log;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class RobotPnP {
    double rx = -2;
    double ry = 0;
    double rz = 12;
    double rp = Math.toRadians(-45); //robot xyz, robot pitch
    double cx = 338.083 / 2;
    double cy = 218.771 / 2;
    double fl = 491.437 / 2;
    Pose2d fieldPos;

    public RobotPnP() {
    }
    public Translation2d getRobotCentricTranslation(int px, int py) {
        if (px < 0 || py < 0) {
            return new Translation2d(0,0);
        }
        double gt = Math.atan((py-cy)/fl);
        Log.v("robotpnp","camera centric angle: " + Math.toDegrees(gt));
        double theta = rp-gt;
        Log.v("robotpnp", "ground centric angle: " + Math.toDegrees(theta));
        double gd = rz / Math.tan(theta);
        Log.v("robotpnp","ground distance: " + gd);
        double dist = Math.hypot(gd, rz);
        Log.v("robotpnp","sample distance: " + dist);
        double pd = Math.cos(gt)*dist;
        Log.v("robotpnp", "plane distance" + pd);
        double gx = (px-cx)*pd/fl;
        Log.v("robotpnp","ground horizontal distance" + gx);
        // gd is backwards for some reason
        return new Translation2d(-gd + rx, ry-gx);
    }

    public Translation2d getFieldCoordinates(int px, int py, Pose2d robotPose) {

        Translation2d rctrans = getRobotCentricTranslation(px, py);

        return robotPose.getTranslation().plus(rctrans.rotateBy(robotPose.getRotation()));

    }


}