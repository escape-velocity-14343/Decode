package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.ColorSensorSubsystem;

public class ArtifactSensorProximity extends CommandBase {
    ColorSensorSubsystem artifactSensor;
    public ArtifactSensorProximity(ColorSensorSubsystem artifactSensor){
        addRequirements(artifactSensor);
        this.artifactSensor = artifactSensor;
    }
    @Override
    public void initialize(){}

    public boolean isFinished(){
        if (artifactSensor.proximityDetected()){
            return true;
        } else{
            return false;
        }
    }
}
