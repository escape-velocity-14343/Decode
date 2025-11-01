package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class ShootCommandGroup extends SequentialCommandGroup {

    public ShootCommandGroup(ShooterSubsystem shooter, SpindexerSubsystem spindexer, TransferArmSubsystem transferArm, TransferWheelSubsystem transferWheel, int ballColor, Telemetry telemetry) {
        telemetry.addData("hello", 1);
        addCommands(
                new ShooterOnCommand(shooter),
                new WaitCommand(500),
                new SpindexOutCommand(spindexer, ballColor),
                new LogKittenCommand(Log.ASSERT, "SHOOT COMMAND", "spindexing done"),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new WaitCommand(500),
                new TransferArmDownCommand(transferArm),
                new TransferWheelOffCommand(transferWheel),
                new ShooterOffCommand(shooter)
            );
        addRequirements(shooter, spindexer, transferArm, transferWheel);
    }
}
