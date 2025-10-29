package org.firstinspires.ftc.teamcode.command;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.ColorSensorSubsystem;

public class ArtifactSensorProximity extends CommandBase {
    ColorSensorSubsystem artifactSensor;
    Telemetry telemetry;
    public ArtifactSensorProximity(ColorSensorSubsystem artifactSensor){
        addRequirements(artifactSensor);
        this.artifactSensor = artifactSensor;
        this.telemetry = telemetry;
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
