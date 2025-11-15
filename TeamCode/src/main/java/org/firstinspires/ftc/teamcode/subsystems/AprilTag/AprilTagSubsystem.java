package org.firstinspires.ftc.teamcode.subsystems.AprilTag;

import android.util.Log;
import android.util.Size;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AprilTagSubsystem extends SubsystemBase {
    private WebcamName shooterCam;
    private VisionPortal visionPortal;
    private AprilTagProcessor tagProcessor;
    private Map<String, Integer> goalTags = new HashMap<>();
    private double distance = 0;
    private double height;
    private double rotation;
    private double bearing = 0;

    double[] rtn = new double[4];
    double turretOffsetX = -81 / 25.4;
    double cameraZ = 207 / 25.4;
    Rotation2d cameraPitch = Rotation2d.fromDegrees(19.484);
    double cameraOffsetX = 78.3 / 25.4;
    Telemetry telemetry;

    public AprilTagSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        shooterCam = hwMap.get(WebcamName.class, "shooterCam");

        tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setLensIntrinsics(
                        341.44068944204895,
                        310.9014398907107,
                        360, //342.01300901026104
                        240) //228.30715483016178
                .build();
        tagProcessor.setDecimation(1f);
        visionPortal = new VisionPortal.Builder().addProcessor(tagProcessor)
                .setCamera(shooterCam)
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .enableLiveView(true)
                .build();
        goalTags.put("Blue", 20);
        goalTags.put("Red", 24);
        //PanelsCameraStream.INSTANCE.startStream(visionPortal, 30);

        this.telemetry = telemetry;

    }

    public double[] returnarray() {
        return rtn;
    }

    public int detect() {
        bearing = 0;
        //tagProcessor.setDecimation(VisionConstants.decimation);
        if (!tagProcessor.getDetections().isEmpty()) {
            for (int i = 0; i < tagProcessor.getDetections().size(); i++) {
                AprilTagDetection tag = tagProcessor.getDetections().get(i);
                if (tag.id == 20 && StaticValues.getM() == 1) {
                    bearing = tag.ftcPose.bearing;
                    distance = tag.ftcPose.range;
                } else if (tag.id == 24 && StaticValues.getM() == -1) {
                    bearing = tag.ftcPose.bearing;
                    distance = tag.ftcPose.range;
                } else if (tag.id >= 21 && tag.id <= 23) {
                    Log.i("apriltag detecting", "detected" + (tag.id - 21));
                    return tag.id - 21;
                }
            }
        }
        return -1;
    }

    //
    //
    //    Log.i("apriltag detecting", "detecting");
    //    tagProcessor.setDecimation(VisionConstants.decimation);
    //    if (!tagProcessor.getDetections().isEmpty()) {
    //        AprilTagDetection tag = tagProcessor.getDetections().get(0);
    //        if (tag.id == 20 || tag.id == 24) {
    //
    //            if (distance > 67)
    //                tagProcessor.setDecimation(1f);
    //            else
    //                tagProcessor.setDecimation(3.0f);
    //
    //            telemetry.addData("Translation X", tag.ftcPose.x);
    //            telemetry.addData("Translation Y", tag.ftcPose.y);
    //            telemetry.addData("Translation Z", tag.ftcPose.z);
    //            telemetry.addData("Distance", tag.ftcPose.range);
    //            telemetry.addData("Bearing", tag.ftcPose.bearing);
    //
    //        } else
    //            bearing = 0;
    //        Log.i("apriltag detecting", "detected" + (tag.id - 21));
    //        for (int i = 0; i<3; i++){
    //
    //        }
    //
    //    } else
    //        Log.i("apriltag detecting", "not detected");
    //    bearing = 0;
    //    return 0;
    //}

    @Override
    public void periodic() {
        detect();
    }

    public double getBearing() {
        return bearing;
    }

    public double getDistance() {
        return distance;
    }

    //public Pose2d getLocalization(double turretAngle) {
    //    Rotation2d turretRotation = Rotation2d.fromDegrees(turretAngle).plus(new Rotation2d(Math.PI));
    //    Pose2d robotRelativeCameraPose = new Pose2d(turretOffsetX + cameraOffsetX * turretRotation.getCos(), cameraOffsetX * turretRotation.getSin(), turretRotation);
    //
    //}
    public void end() {
        visionPortal.close();
    }

    public boolean isStreaming() {
        return visionPortal.getCameraState() == VisionPortal.CameraState.STREAMING;
    }

    public boolean setExposure(int exposure) {
        if (!isStreaming()) {
            return false;
        }
        ExposureControl control = visionPortal.getCameraControl(ExposureControl.class);
        control.setMode(ExposureControl.Mode.Manual);
        Log.i("camera", "exposure: " + control.getExposure(TimeUnit.MICROSECONDS));
        return control.setExposure(exposure, TimeUnit.MICROSECONDS);
    }

    public boolean setExposure() {
        return setExposure(VisionConstants.exposureMicros);
    }

    public boolean waitForSetExposure(long timeoutMs, int maxAttempts) {
        return waitForSetExposure(timeoutMs, maxAttempts, VisionConstants.exposureMicros);
    }

    public boolean waitForSetExposure(long timeoutMs, int maxAttempts, int exposure) {
        long startMs = System.currentTimeMillis();
        int attempts = 0;
        long msAfterStart = 0;
        while (msAfterStart < timeoutMs && attempts++ < maxAttempts) {
            Log.i("camera", String.format("Attempting to set camera exposure, attempt %d, %d ms after start", attempts, msAfterStart));
            if (setExposure(exposure)) {
                Log.i("camera", "Set exposure succeeded");
                return true;
            }
            msAfterStart = System.currentTimeMillis() - startMs;
        }

        Log.e("camera", "Set exposure failed");
        return false;
    }
public double getExposure() {
        if (!isStreaming()) {
            return -1;
        }
        ExposureControl control = visionPortal.getCameraControl(ExposureControl.class);
        return control.getExposure(TimeUnit.MICROSECONDS);
    }

    public void saveFrame(String name) {
        visionPortal.saveNextFrameRaw(name);
    }
}

