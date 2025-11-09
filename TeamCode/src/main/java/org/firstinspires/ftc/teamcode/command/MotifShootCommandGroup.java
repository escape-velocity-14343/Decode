package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.LogCatCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class MotifShootCommandGroup extends SequentialCommandGroup {
    public MotifShootCommandGroup(SpindexerSubsystem spindexer, ShooterSubsystem shooter, TransferWheelSubsystem transferWheel, TransferArmSubsystem transferArm){
        addRequirements(spindexer, shooter, transferWheel, transferArm);
        addCommands(
                new LogCatCommand("shooting ball one"),
                new ShooterOnCommand(shooter),
                new SpindexOutCommand(spindexer, StaticValues.getMotif(0)),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new TransferArmDownCommand(transferArm),
                new WaitCommand(5000),

                new LogCatCommand("shooting ball two"),
                new SpindexOutCommand(spindexer, StaticValues.getMotif(1)),
                new TransferArmUpCommand(transferArm),
                new TransferArmDownCommand(transferArm),
                new WaitCommand(5000),

                new LogCatCommand("shooting ball three"),
                new SpindexOutCommand(spindexer, StaticValues.getMotif(2)),
                new TransferArmUpCommand(transferArm),
                new TransferArmDownCommand(transferArm),
                new WaitCommand(5000)
        );
    }
}
