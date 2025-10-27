package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class ShootSequenceInstantCommand extends SequentialCommandGroup {
    public ShootSequenceInstantCommand(SpindexerSubsystem spindexer, TransferArmSubsystem transferArm, TransferWheelSubsystem transferWheel, int ballNum, Telemetry telemetry){
        addCommands(
                new InstantCommand(()->transferArm.down()),
                new WaitCommand(500),
                new InstantCommand(()->spindexer.outake(ballNum)),
                new WaitCommand(500),
                new InstantCommand(()->transferWheel.on()),
                new InstantCommand(()->transferArm.up()),
                new WaitCommand(500),
                new InstantCommand(()-> transferArm.down()),
                new InstantCommand(()->transferWheel.off())
        );
    }
}
