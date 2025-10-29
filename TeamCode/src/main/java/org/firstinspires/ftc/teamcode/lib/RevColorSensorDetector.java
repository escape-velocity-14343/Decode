package org.firstinspires.ftc.teamcode.lib;

import android.graphics.Color;
import android.util.Log;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.ConstantsSpindexer;

public class RevColorSensorDetector implements ArtifactSensor {
    RevColorSensorV3 colorSensor;
    boolean greenDetected = false;
    boolean proximityDetected = false;

    public RevColorSensorDetector(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(RevColorSensorV3.class, "colorSensor");
    }
    @Override
    public boolean greenDetected() {
        float[] hsvValues = new float[3];
        Color.colorToHSV(colorSensor.getNormalizedColors().toColor(), hsvValues);
        float hue = hsvValues[0];
        greenDetected = Math.abs(hue- ConstantsSpindexer.greenHue) > Math.abs(hue - ConstantsSpindexer.purpleHue);
        return greenDetected;
    }

    @Override
    public boolean proximityDetected() {
        double distance = colorSensor.getDistance(DistanceUnit.CM);
        Log.println(Log.ASSERT, "Artifact Sensor,", "Distance: " + distance);
        proximityDetected = distance < ConstantsSpindexer.proximityThreshold;
        return proximityDetected;
    }
}
