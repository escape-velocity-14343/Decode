package org.firstinspires.ftc.teamcode.lib;

import android.graphics.Color;

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
        return greenDetected;
    }

    @Override
    public boolean proximityDetected() {
        return proximityDetected;
    }
    public void update() {
        float[] hsvValues = new float[3];
        Color.colorToHSV(colorSensor.getNormalizedColors().toColor(), hsvValues);
        float hue = hsvValues[0];
        greenDetected = Math.abs(hue- ConstantsSpindexer.greenHue) > Math.abs(hue - ConstantsSpindexer.purpleHue);
        proximityDetected = colorSensor.getDistance(DistanceUnit.CM) < ConstantsSpindexer.proximityThreshold;
    }
}
