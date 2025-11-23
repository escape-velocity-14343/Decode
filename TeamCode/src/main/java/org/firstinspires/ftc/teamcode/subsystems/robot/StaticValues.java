package org.firstinspires.ftc.teamcode.subsystems.robot;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.lights.PanelsLights;
import com.bylazar.lights.RGBIndicator;

@Configurable
public class StaticValues {
    private static int[] motif = {1, 1, 2};

    public static void setMotif(int place, int value) {
        motif[place] = value;
    }

    public static int getMotif(int place) {
        return motif[place];
    }

    public static void resetMotif() {
        motif[0] = 1;
        motif[1] = 1;
        motif[2] = 1;
    }

    private static int[] artifacts = {2, 1, 1};

    public static void setArtifacts(int place, int value) {
        artifacts[place] = value;
    }

    public static int getArtifacts(int place) {
        return artifacts[place];
    }

    public static void resetArtifacts() {
        artifacts[0] = 2;
        artifacts[1] = 1;
        artifacts[2] = 1;
    }

    private static int m = 1;

    public static void setM(int value) {
        m = value;
    }

    public static int getM() {
        return m;
    }

    private static double voltageScalar = 1.0;

    public static void setVoltageScalar(double value) {
        voltageScalar = value;
    }

    public static double getVoltageScalar() {
        return voltageScalar;
    }

    private static int ballOffset = 0;
    public static void setBallOffset(int value) {
        ballOffset = value;
    }
    public static int getBallOffset() {
        return ballOffset;
    }

    public static double maxSpeed = 1;
    public static void setMaxSpeed(double value){
        maxSpeed = value;
    }
    public static double getMaxSpeed(){
        return maxSpeed;
    }

    public static double minSpeed = -1;
    public static void setMinSpeed(double value){
        minSpeed = value;
    }
    public static double getMinSpeed(){
        return minSpeed;
    }
}
