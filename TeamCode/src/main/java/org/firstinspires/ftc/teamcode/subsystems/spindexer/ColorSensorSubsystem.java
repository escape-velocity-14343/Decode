package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.lib.ArtifactSensor;

public class ColorSensorSubsystem extends SubsystemBase {
    ArtifactSensor artifactSensor;
    public ColorSensorSubsystem(){
    }
    public String detectColor(){
        if (artifactSensor.greenDetected()){
            return "green";
        } else {
            return "purple";
        }
    }

    public boolean proximityDetected(){
        return proximityDetected();
        }
    }
