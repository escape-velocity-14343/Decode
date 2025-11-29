package org.firstinspires.ftc.teamcode.subsystems.IntakeCam;

import android.graphics.Color;
import android.util.Log;
import android.util.Size;


import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Vector2d;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.SortOrder;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;

import org.firstinspires.ftc.teamcode.lib.VisionPortal.ColorBlobLocatorProcessorMulti;
import org.firstinspires.ftc.teamcode.lib.VisionPortal.ColorRange;
import org.firstinspires.ftc.teamcode.lib.VisionPortal.GlowUpPipeline;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.VisionConstants;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.Circle;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorSpace;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class IntakeCamSubsystem extends SubsystemBase {
    ColorBlobLocatorProcessorMulti colorLocator;
    private double pixelPos = 0;

    private Vector2d artifactPose = new Vector2d();

    Telemetry telemetry;
    VisionPortal visionPortal;
    private final WebcamName intakeCam;
    private double angle = 0;


    public IntakeCamSubsystem(HardwareMap hMap, Telemetry telemetry) {
        colorLocator = new ColorBlobLocatorProcessorMulti(
                new org.firstinspires.ftc.teamcode.lib.VisionPortal.ColorRange(ColorSpace.HSV, new Scalar(126, 30, 30), new Scalar(161, 255, 255)),
                org.firstinspires.ftc.teamcode.lib.VisionPortal.ImageRegion.asImageCoordinates(0, 0, IntakeCamConstants.width, IntakeCamConstants.height),
                ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY,
                Color.rgb(255, 120, 31),
                Color.rgb(255, 255, 255),
                Color.rgb(3, 227, 252)
        );
        //colorLocator.addColors(new ColorRange());

        intakeCam = hMap.get(WebcamName.class, "intakeCam");
        visionPortal = new VisionPortal.Builder()
                .addProcessors(colorLocator)
                .setCameraResolution(new Size(320, 240))
                .setCamera(intakeCam)
                .setStreamFormat(VisionPortal.StreamFormat.YUY2)
                .setLiveViewContainerId(StaticValues.getPortalIDIntake())
                .build();
        setEnabled(true);
        visionPortal.stopLiveView();
        this.telemetry = telemetry;
    }


    @Override
    public void periodic() {
        pixelPos = 0;

        if (visionPortal.getProcessorEnabled(colorLocator)) {
            artifactPose = new Vector2d(colorLocator.getCircle().getCenter().x, colorLocator.getCircle().getCenter().y);
        }
    }

    public Vector2d getArtifactPos() {
        return artifactPose;
    }
    public Pose2d getArtifactPose() {
        if (Objects.isNull(artifactPose)) {
            return null;
        }
        return new Pose2d(artifactPose.getX(), artifactPose.getY(), Rotation2d.fromDegrees(angle));
    }


    public void setEnabled(boolean enable) {
        visionPortal.setProcessorEnabled(colorLocator, enable);
    }

    public double getPixelPos() {
        return pixelPos;
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
        Log.i("intake camera", "exposure before: " + control.getExposure(TimeUnit.MILLISECONDS));
        boolean worked = control.setExposure(exposure, TimeUnit.MILLISECONDS);
        Log.i("intake camera", "exposure after: " + control.getExposure(TimeUnit.MILLISECONDS));
        return worked;
    }

    public boolean setExposure() {
        return setExposure(VisionConstants.intakeExposureMillis);
    }

    public boolean waitForSetExposure(long timeoutMs, int maxAttempts) {
        return waitForSetExposure(timeoutMs, maxAttempts, VisionConstants.intakeExposureMillis);
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
        return control.getExposure(TimeUnit.MILLISECONDS);
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
