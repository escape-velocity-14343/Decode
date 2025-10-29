package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.ColorSensorSubsystem;

public class DetectColor extends CommandBase {
    ColorSensorSubsystem artifactSensor;
    public DetectColor(ColorSensorSubsystem artifactSensor){
        addRequirements(artifactSensor);
        this.artifactSensor = artifactSensor;
    }
    @Override
    public void initialize(){
        artifactSensor.detectColor();
    }
}
