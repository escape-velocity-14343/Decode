package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

import kotlin.time.Instant;

public class ShooterIntakeCommandGroup extends SequentialCommandGroup {
    public ShooterIntakeCommandGroup(ShooterSubsystem shooter, SpindexerSubsystem spindexer) {
        addCommands(
                new LogKittenCommand(Log.ASSERT, "Shooter Intake Command", "starting shooter intake command"),
                new SpindexGoToCommand(spindexer, 0),
                new LogKittenCommand(Log.ASSERT, "Shooter Intake Command", "spindexing done"),
                new InstantCommand(() -> shooter.intake()),
                new LogKittenCommand(Log.ASSERT, "Shooter Intake Command", "shooter intake started"),
                new WaitCommand(1000),
                new LogKittenCommand(Log.ASSERT, "Shooter Intake Command", "waiting done"),
                new SpindexGoToCommand(spindexer, 120),
                new LogKittenCommand(Log.ASSERT, "Shooter Intake Command", "spindexing to 120 done"),
                new WaitCommand(1000),
                new LogKittenCommand(Log.ASSERT, "Shooter Intake Command", "waiting done"),
                new SpindexGoToCommand(spindexer, 240),
                new LogKittenCommand(Log.ASSERT, "Shooter Intake Command", "spindexing to 240 done")
        );
    }
}
