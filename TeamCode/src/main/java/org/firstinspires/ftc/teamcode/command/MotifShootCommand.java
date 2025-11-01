package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class MotifShootCommand extends SequentialCommandGroup {
    public MotifShootCommand(SpindexerSubsystem spindexer, ShooterSubsystem shooter, TransferWheelSubsystem transferWheel, TransferArmSubsystem transferArm) {
        addRequirements(spindexer, shooter);
        addCommands(
                new ShooterOnCommand(shooter),
                new InstantCommand(() -> spindexer.outakeAuto(Robot.motif[0])),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new WaitCommand(500),
                new TransferArmDownCommand(transferArm),

                new InstantCommand(() -> spindexer.outakeAuto(Robot.motif[1])),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new WaitCommand(500),
                new TransferArmDownCommand(transferArm),

                new InstantCommand(() -> spindexer.outakeAuto(Robot.motif[2])),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new WaitCommand(500),
                new TransferArmDownCommand(transferArm),


                new TransferWheelOffCommand(transferWheel),
                new ShooterOffCommand(shooter)
        );
    }
}
