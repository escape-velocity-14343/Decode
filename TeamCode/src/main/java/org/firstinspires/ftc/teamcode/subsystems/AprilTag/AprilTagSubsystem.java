package org.firstinspires.ftc.teamcode.subsystems.AprilTag;

import android.util.Size;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.HashMap;
import java.util.Map;

public class AprilTagSubsystem extends SubsystemBase {
    private WebcamName shooterCam;
    private VisionPortal visionPortal;
    private AprilTagProcessor tagProcessor;
    private Map<String, Integer> goalTags = new HashMap<>();
    private double distance;
    private double height;
    private double rotation;
    double[] rtn = new double[4];
    Telemetry telemetry;

    public AprilTagSubsystem (HardwareMap hwMap, Telemetry telemetry) {
        shooterCam = null;
        shooterCam = hwMap.get(WebcamName.class, "shooterCam");
        tagProcessor = new AprilTagProcessor.Builder().setDrawAxes(true).setDrawCubeProjection(true).setDrawTagID(true).setDrawTagOutline(true).build();
        visionPortal = new VisionPortal.Builder().addProcessor(tagProcessor).setCamera(shooterCam).setCameraResolution(new Size(640, 480)).setStreamFormat(VisionPortal.StreamFormat.MJPEG).build();
        goalTags.put("Blue", 20);
        goalTags.put("Red", 24);
        this.telemetry = telemetry;
    }

    public double[] returnarray(){
        return rtn;
    }

    public double[] detect() {
        if (!tagProcessor.getDetections().isEmpty()) {
            AprilTagDetection tag = tagProcessor.getDetections().get(0);
            rtn[0] = distance;
            rtn[1] = height;
            rtn[2] = rotation;
            rtn[3] = tag.id;
            telemetry.addData("rotation", rotation);
            return rtn;
        }
        return rtn;
    }
}

