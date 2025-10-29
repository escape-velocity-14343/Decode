package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.ColorSensorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class IntakeAutoCommandGroup extends SequentialCommandGroup {
    public IntakeAutoCommandGroup(SpindexerSubsystem spindexer, IntakeSubsystem intake, ColorSensorSubsystem artifactSensor){
        addCommands(
                new IntakeOnCommand(intake),
                new SpindexInCommand(spindexer),
                new LogKittenCommand(Log.ASSERT, "INTAKE AUTO", "spindexing done"),
                new ArtifactSensorProximity(artifactSensor),
                new LogKittenCommand(Log.ASSERT, "INTAKE AUTO", "artifact proximity reached"),
                new DetectColor(artifactSensor, spindexer),
                new LogKittenCommand(Log.ASSERT, "INTAKE AUTO", "color inputed into array"),
                new IntakeOffCommand(intake)
        );
        addRequirements(spindexer, intake, artifactSensor);
    }
}
