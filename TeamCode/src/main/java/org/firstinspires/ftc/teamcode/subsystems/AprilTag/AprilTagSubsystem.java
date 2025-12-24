package org.firstinspires.ftc.teamcode.subsystems.AprilTag;

import android.util.Log;
import android.util.Size;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
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
    AprilTagDetection relocTag = null;
    boolean stale = false;

    double camToTurretDist = 3.5;
    double turretToRobotDist = 4;

    public AprilTagSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        shooterCam = hwMap.get(WebcamName.class, "shooterCam");

        tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setNumThreads(2)
                .build();
        tagProcessor.setDecimation(1f);
        visionPortal = new VisionPortal.Builder().addProcessor(tagProcessor)
                .setCamera(shooterCam)
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .enableLiveView(true)
                .setShowStatsOverlay(false)
                .setLiveViewContainerId(StaticValues.getPortalIDShooter())
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
        stale = true;
        if (!tagProcessor.getDetections().isEmpty()) {
            for (int i = 0; i < tagProcessor.getDetections().size(); i++) {
                AprilTagDetection tag = tagProcessor.getDetections().get(i);
                if (tag.id == 20 && StaticValues.getM() == 1) {
                    bearing = tag.ftcPose.bearing;
                    distance = tag.ftcPose.range * Math.cos(Math.toRadians(tag.ftcPose.elevation) + cameraPitch.getRadians());
                    double tagDist = tag.ftcPose.range * Math.cos(Math.toRadians(tag.ftcPose.elevation) + cameraPitch.getRadians());
                    telemetry.addData("Translation X", tag.ftcPose.x);
                    telemetry.addData("Translation Y", tag.ftcPose.y);
                    telemetry.addData("Translation Z", tag.ftcPose.z);
                    telemetry.addData("Pitch", tag.ftcPose.pitch);
                    telemetry.addData("Distance", tag.ftcPose.range);
                    telemetry.addData("Bearing", tag.ftcPose.bearing);
                    telemetry.addData("Tag Distance", tagDist);
                    StaticValues.tagDist = tagDist;
                    relocTag = tag;
                    stale = false;
                } else if (tag.id == 24 && StaticValues.getM() == -1) {
                    bearing = tag.ftcPose.bearing;
                    distance = tag.ftcPose.range * Math.cos(Math.toRadians(tag.ftcPose.elevation) + cameraPitch.getRadians());
                    double tagDist = tag.ftcPose.range * Math.cos(Math.toRadians(tag.ftcPose.elevation) + cameraPitch.getRadians());
                    telemetry.addData("Tag Distance", tagDist);
                    telemetry.addData("Translation X", tag.ftcPose.x);
                    telemetry.addData("Translation Y", tag.ftcPose.y);
                    telemetry.addData("Translation Z", tag.ftcPose.z);
                    telemetry.addData("Pitch", tag.ftcPose.pitch);
                    telemetry.addData("Distance", tag.ftcPose.range);
                    telemetry.addData("Bearing", tag.ftcPose.bearing);
                    relocTag = tag;
                    StaticValues.tagDist = tagDist;
                    stale = false;
                } else if (tag.id >= 21 && tag.id <= 23) {
                    Log.i("apriltag detecting", "detected" + (tag.id - 21));
                    return tag.id - 21;
                }
            }
        }
        return -1;
    }


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

    public boolean tagSeen(){
        if (relocTag == null || stale){
            return false;
        }
        else{
            return true;
        }
    }

    public Translation2d getLocalization(double turretAngle, Rotation2d robotAngle) {
        if (relocTag == null || stale) {
            return null;
        }
        double bearing = Math.atan(Math.tan(Math.toRadians(relocTag.ftcPose.bearing)) / Math.cos(cameraPitch.getRadians()));
        telemetry.addData("RELOC TAG: robot angle in radians", robotAngle.getRadians());
        telemetry.addData("RELOC TAG: turret angle in radians", turretAngle);
        telemetry.addData("RELOC TAG: ground bearing in radians", Math.toRadians(bearing));
        telemetry.addData("RELOC TAG: tag bearing in radians", Math.toRadians(relocTag.ftcPose.bearing));
        telemetry.addData("RELOC TAG: distance", getDistance());
        double theta = Math.PI - (Math.abs(robotAngle.getRadians()) - (Math.PI/2)) +turretAngle-Math.toRadians(relocTag.ftcPose.bearing);
        telemetry.addData("RELOC TAG: total angle in radians", (theta/* + bearing*/));

        //relative to camera
        double x = 66 - Math.cos(theta)*getDistance();
        double y = 66 - Math.sin(theta)*getDistance();

        telemetry.addData("RELOC TAG: pos before offset", "x: " + x + ", y: " + y);

        //camera pose from turret
        double camX = Math.cos(/*robotAngle.getRadians() - Math.PI */- turretAngle) * camToTurretDist;
        double camY = Math.sin(/*robotAngle.getRadians() - Math.PI*/ - turretAngle) * camToTurretDist;

        //turret pose from robot
        double turretX = Math.sin(robotAngle.getRadians()) * turretToRobotDist;
        double turretY = -Math.cos(robotAngle.getRadians()) * turretToRobotDist;

        telemetry.addData("RELOC TAG: offset X", camX + turretX);
        telemetry.addData("RELOC TAG: offset Y", camY + turretY);
        x += camX + turretToRobotDist + turretX;
        y += camY + turretY;
        Translation2d newPose = new Translation2d(x, y);
        //AprilTagPoseFtc tag = relocTag.ftcPose;
        //Rotation2d turretRotation = Rotation2d.fromDegrees(AngleUnit.normalizeDegrees(180 - turretAngle));
        //telemetry.addData("Turret Angle", turretRotation.getDegrees());
        //Pose2d robotRelativeCameraPose = new Pose2d(turretOffsetX + cameraOffsetX * turretRotation.getCos(), cameraOffsetX * turretRotation.getSin(), turretRotation);
        //telemetry.addData("Robot Relative Camera X", robotRelativeCameraPose.getX());
        //telemetry.addData("Robot Relative Camera Y", robotRelativeCameraPose.getY());
        //double tagDist = tag.range * Math.cos(Math.toRadians(tag.elevation) + cameraPitch.getRadians());
        //double tagSideways = tag.x;
        //telemetry.addData("Tag Distance", tagDist);
        //Translation2d turretRelativeTranslation = new Translation2d(tagDist * turretRotation.getCos() - tagSideways * turretRotation.getSin(), tagDist * turretRotation.getSin() + tagSideways * turretRotation.getCos());
        //telemetry.addData("Turret Relative X", turretRelativeTranslation.getX());
        //telemetry.addData("Turret Relative Y", turretRelativeTranslation.getY());
        //Translation2d robotPose = new Translation2d(tagDist * turretRotation.getCos() - tagSideways * turretRotation.getSin() - robotRelativeCameraPose.getX(), tagDist * turretRotation.getSin() + tagSideways * turretRotation.getCos() - robotRelativeCameraPose.getY());
        telemetry.addData("Robot Pose X", newPose.getX());
        telemetry.addData("Robot Pose Y", newPose.getY());
        //new Translation2d()
        //newPose = new Pose2d(newPose.getTranslation().rotateBy(robotAngle.times(-1)), robotAngle);
        return newPose;
    }
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
        Log.i("shoter camera", "exposure before: " + control.getExposure(TimeUnit.MICROSECONDS));
        boolean worked = control.setExposure(exposure, TimeUnit.MICROSECONDS);
        Log.i("shooter camera", "exposure after: " + control.getExposure(TimeUnit.MICROSECONDS));
        return worked;
    }

    public boolean setExposure() {
        return setExposure(VisionConstants.shooterExposureMicros);
    }

    public boolean waitForSetExposure(long timeoutMs, int maxAttempts) {
        return waitForSetExposure(timeoutMs, maxAttempts, VisionConstants.shooterExposureMicros);
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
    public void setEnableLiveView(boolean enable) {
        if (enable) {
            visionPortal.resumeLiveView();
            return;
        }
        visionPortal.stopLiveView();
    }
    public double getFPS() {
        return visionPortal.getFps();
    }
}

