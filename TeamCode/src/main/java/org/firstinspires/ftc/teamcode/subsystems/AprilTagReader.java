package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.HashMap;
import java.util.Map;

public class AprilTagReader {
    private WebcamName shooterCam;
    private VisionPortal visionPortal;
    private AprilTagProcessor tagProcessor;
    private Map<String, Integer> goalTags = new HashMap<>();
    private double distance;
    private double height;
    private double rotation;
    double[] rtn = new double[3];
    Telemetry telemetry;

    public void init(HardwareMap hwMap, Telemetry telemetry) {
        shooterCam = null;
        shooterCam = hwMap.get(WebcamName.class, "shooterCam");
        tagProcessor = new AprilTagProcessor.Builder().setDrawAxes(true).setDrawCubeProjection(true).setDrawTagID(true).setDrawTagOutline(true).build();
        visionPortal = new VisionPortal.Builder().addProcessor(tagProcessor).setCamera(shooterCam).setCameraResolution(new Size(640, 480)).setStreamFormat(VisionPortal.StreamFormat.MJPEG).build();
        goalTags.put("Blue", 20);
        goalTags.put("Red", 24);
        distance = -0.1;
        height = -0.1;
        rotation = -0.1;
        rtn[0] = distance;
        rtn[1] = height;
        rtn[2] = rotation;
        this.telemetry = telemetry;
    }

    public double[] periodic(String color) {
        if (!tagProcessor.getDetections().isEmpty()) {
            AprilTagDetection tag = tagProcessor.getDetections().get(0);
            if (tag.id == goalTags.get(color)) {
                distance = tag.ftcPose.range;
                height = tag.ftcPose.elevation;
                rotation = tag.ftcPose.bearing;
            }
        }
        rtn[0] = distance;
        rtn[1] = height;
        rtn[2] = rotation;
        telemetry.addData("rotation", rotation);

        return rtn;
    }
}

