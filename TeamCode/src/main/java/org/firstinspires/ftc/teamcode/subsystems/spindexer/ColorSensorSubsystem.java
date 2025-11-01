package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.ArtifactSensor;
import org.firstinspires.ftc.teamcode.lib.RevColorSensorDetector;

public class ColorSensorSubsystem extends SubsystemBase {
    ArtifactSensor artifactSensor;
    public ColorSensorSubsystem(HardwareMap hwMap){
        this.artifactSensor = new RevColorSensorDetector(hwMap);
    }
    public int detectColor(){
        if (artifactSensor.greenDetected()){
            return 2;
        } else {
            return 1;
        }
    }

    public boolean proximityDetected(){
        return artifactSensor.proximityDetected();
        }
    }
