package org.firstinspires.ftc.teamcode.lib;



import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.ftclib.util.math.Rotation2d;

public class sensOrangeEncoder {

    private AnalogInput sensor;
    private double offset;
    private boolean inverted;

    { 
        // default to 360 degrees
        maxAngle = 360;

        // default to 0
        offset = 0;

        // default to false
        inverted = false;
    }

    public AnalogEncoder(String key, HardwareMap hMap){
        this.sensor = hMap.analogInput.get(key);
    }

    public void setInverted(boolean inverted){
        this.inverted = inverted;
    }

    /**
     * Sets offset of the encoder
     */
    public void setPositionOffset(double offset){
        this.offset = offset;
    } 
    
    /**
     * @return Degrees
     */
    public double getDegrees(){
        return AngleUnit.normalizeDegrees((inverted ? -1 : 1) * (sensor.getVoltage()-0.043)/3.1*360 + offset);
    }
    /**
    * @return who would know
    */
    public double getRadians() {
        return Math.toRadians(getAngle());
    }
    public Rotation2d getRotation2d() {
        return new Rotation2d(getRadians());
    }


}