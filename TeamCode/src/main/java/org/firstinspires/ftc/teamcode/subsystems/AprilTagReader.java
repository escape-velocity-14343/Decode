package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

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
    public void init (HardwareMap hwMap) {
        WebcamName shooterCam = null;
        shooterCam = hwMap.get(WebcamName.class, "shooterCam");
        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder().setDrawAxes(true).setDrawCubeProjection(true).setDrawTagID(true).setDrawTagOutline(true).build();
        VisionPortal visionPortal = new VisionPortal.Builder().addProcessor(tagProcessor).setCamera(shooterCam).setCameraResolution(new Size(640, 480)).build();
        goalTags.put("Blue", 20);
        goalTags.put("Red", 24);
    }
    public void AprilTagsTracking(String color){
        while (true){
            if (tagProcessor.getDetections().size() > 0){
                AprilTagDetection tag = tagProcessor.getDetections().get(0);
                if(tag.id == goalTags.get(color)) {
                    double distance = tag.ftcPose.range;
                    double height = tag.ftcPose.elevation;
                    int rotation = (int) tag.ftcPose.bearing;

                }
            }
        }
    }

}
