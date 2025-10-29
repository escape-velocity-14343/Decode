package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.lib.Util.Pose2DConverter;
import static org.firstinspires.ftc.teamcode.lib.Util.Pose2dConverter;

import android.util.Log;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

import org.firstinspires.ftc.teamcode.lib.Localizer;
import org.firstinspires.ftc.teamcode.lib.GoBildaPinpoint;

@Configurable
public class PinpointSubsystem extends SubsystemBase implements Localizer {
    GoBildaPinpoint pinpoint;
    Pose2D pose = new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0);
    GoBildaPinpoint.DeviceStatus deviceStatus = GoBildaPinpoint.DeviceStatus.NOT_READY;

    @Deprecated public static double yawScalar = 1;
    public static boolean flipX = true;
    public static boolean flipY = false;
    public static double xEncOffset = 65;
    public static double yEncOffset = 85;

    private Pose2D lastGoodPose = new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0);

    public PinpointSubsystem(HardwareMap hMap) {
        pinpoint = hMap.get(GoBildaPinpoint.class, "pinpoint");

        pinpoint.initialize();

        // pinpoint.setYawScalar(yawScalar);
        pinpoint.recalibrateIMU();

        pinpoint.setEncoderResolution(GoBildaPinpoint.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(
                flipX
                        ? GoBildaPinpoint.EncoderDirection.REVERSED
                        : GoBildaPinpoint.EncoderDirection.FORWARD,
                flipY
                        ? GoBildaPinpoint.EncoderDirection.REVERSED
                        : GoBildaPinpoint.EncoderDirection.FORWARD);
        pinpoint.setOffsets(xEncOffset, yEncOffset);
        // reset();
        deviceStatus = pinpoint.getDeviceStatus();
    }

    @Override
    public void periodic() {
        pinpoint.update();
        if (Double.isNaN(pinpoint.getPosX())
                || Double.isNaN(pinpoint.getPosY())
                || (pinpoint.getPosX() == 0.0
                && pinpoint.getPosY() == 0.0
                && pinpoint.getHeading() == 0.0
                && pinpoint.getVelX() == 0.0)) {
            pose = lastGoodPose;
            Log.i("%11", "pinpoint NaN value");
        } else {
            pose = pinpoint.getPosition();
            lastGoodPose = pose;
        }

        if (pinpoint.getDeviceStatus() != deviceStatus) {
            Log.i("%Pinpoint Status Change", pinpoint.getDeviceStatus().toString());
            deviceStatus = pinpoint.getDeviceStatus();
        }
    }

    private Pose2D getSDKPose() {
        return pose;
    }

    public int[] getEncoderCounts() {
        return new int[] {pinpoint.getEncoderX(), pinpoint.getEncoderY()};
    }

    public Pose2d getPose() {
        return new Pose2d(
                getSDKPose().getX(DistanceUnit.INCH),
                getSDKPose().getY(DistanceUnit.INCH),
                Rotation2d.fromDegrees(getSDKPose().getHeading(AngleUnit.DEGREES)));
    }

    @Override
    public void setPose(Pose2d pose) {
        pinpoint.setPosition(Pose2DConverter(pose));
    }

    @Override
    public void update() {
        pinpoint.update();
    }

    @Override
    public Rotation2d getHeading() {
        return getPose().getRotation();
    }

    @Override
    public Translation2d getTranslation() {
        return getPose().getTranslation();
    }

    public Pose2d getVelocity() {
        Pose2D velocity = pinpoint.getVelocity();
        return new Pose2d(
                velocity.getX(DistanceUnit.INCH),
                velocity.getY(DistanceUnit.INCH),
                Rotation2d.fromDegrees(velocity.getHeading(AngleUnit.DEGREES)));
    }

    @Override
    public Rotation2d getAngularVelocity() {
        return getVelocity().getRotation();
    }

    @Override
    public Translation2d getTranslationalVelocity() {
        return getVelocity().getTranslation();
    }

    public void reset() {
        pinpoint.resetPosAndIMU();
        lastGoodPose = new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0);
    }

    /**
     * @param x In inches.
     * @param y In inches.
     */
    public void setPosition(double x, double y) {
        pinpoint.setPosition(
                new Pose2D(
                        DistanceUnit.INCH,
                        x,
                        y,
                        AngleUnit.DEGREES,
                        pose.getHeading(AngleUnit.DEGREES)));
        lastGoodPose =
                new Pose2D(
                        DistanceUnit.INCH,
                        x,
                        y,
                        AngleUnit.DEGREES,
                        pose.getHeading(AngleUnit.DEGREES));
    }

    public boolean isDoneCalibration() {

        return pinpoint.getDeviceStatus() == GoBildaPinpoint.DeviceStatus.READY;
    }

    /** Warning - will completely break position!! */
    public void resetYaw() {
        pinpoint.setPosition(
                new Pose2D(
                        DistanceUnit.MM,
                        pinpoint.getPosX(),
                        pinpoint.getPosY(),
                        AngleUnit.RADIANS,
                        0));
    }
}