package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.ColorSensorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class DetectColor extends CommandBase {
    ColorSensorSubsystem artifactSensor;
    SpindexerSubsystem spindexer;
    String color;
    public DetectColor(ColorSensorSubsystem artifactSensor, SpindexerSubsystem spindexer){
        addRequirements(artifactSensor);
        this.artifactSensor = artifactSensor;
    }
    @Override
    public void initialize(){
        color = artifactSensor.detectColor();
        spindexer.addColor(color);
    }
}
