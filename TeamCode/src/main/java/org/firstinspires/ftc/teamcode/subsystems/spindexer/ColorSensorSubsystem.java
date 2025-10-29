package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.lib.ArtifactSensor;

public class ColorSensorSubsystem extends SubsystemBase {
    ArtifactSensor artifactSensor;
    SpindexerSubsystem spindexer;
    public ColorSensorSubsystem(ArtifactSensor artifactSensor, SpindexerSubsystem spindexer){
        this.artifactSensor = artifactSensor;
        this.spindexer = spindexer;
    }
    public void detectColor(){
        if (artifactSensor.proximityDetected()) {
            if (artifactSensor.greenDetected()){
                spindexer.addColor("green");
            } else {
                spindexer.addColor("purple");
            }
        }
    }

    public boolean proximityDetected(){
        return proximityDetected();
        }
    }
