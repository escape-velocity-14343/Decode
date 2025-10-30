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
    public String detectColor(){
        if (artifactSensor.greenDetected()){
            return "green";
        } else {
            return "purple";
        }
    }

    public boolean proximityDetected(){
        return artifactSensor.proximityDetected();
        }
    }
