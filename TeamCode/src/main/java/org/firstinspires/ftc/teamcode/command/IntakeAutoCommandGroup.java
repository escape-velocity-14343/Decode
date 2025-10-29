package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.ColorSensorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class IntakeAutoCommandGroup extends SequentialCommandGroup {
    public IntakeAutoCommandGroup(SpindexerSubsystem spindexer, ColorSensorSubsystem artifactSensor){
        addCommands(
                new SpindexInCommand(spindexer),
                new ArtifactSensorProximity(artifactSensor),
                new DetectColor(artifactSensor)
        );
    }
}
