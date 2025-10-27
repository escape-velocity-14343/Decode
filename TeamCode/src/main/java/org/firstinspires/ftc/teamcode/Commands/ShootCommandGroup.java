package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.LogCatCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class ShootCommandGroup extends SequentialCommandGroup {

    public ShootCommandGroup(SpindexerSubsystem spindexer, TransferArmSubsystem transferArm, TransferWheelSubsystem transferWheel, int ballNum, Telemetry telemetry) {
        telemetry.addData("hello", 1);
        addCommands(
                new LogCatCommand("Shoot Command Start"),
                new TransferArmDownCommand(transferArm),
                new LogCatCommand("Transfer Arm Down"),
                new SpindexOutCommand(spindexer, ballNum),
                new LogCatCommand("Spindex Out Complete"),
                new TransferWheelOnCommand(transferWheel),
                new LogCatCommand("Transfer Wheel On"),
                new TransferArmUpCommand(transferArm),
                new LogCatCommand("Transfer Arm Up"),
                new TransferArmDownCommand(transferArm),
                new LogCatCommand("Transfer Arm Down Again"),
                new TransferWheelOffCommand(transferWheel),
                new LogCatCommand("Transfer Wheel Off")
        );
        addRequirements(spindexer, transferArm, transferWheel);
    }
}
