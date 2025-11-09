package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class MotifShoot21Command extends SequentialCommandGroup {
    public MotifShoot21Command(SpindexerSubsystem spindexer, ShooterSubsystem shooter, TransferWheelSubsystem transferWheel, TransferArmSubsystem transferArm) {
        addRequirements(spindexer, shooter);
        addCommands(
                new ShooterOnCommand(shooter),
                new SpindexGoToCommand(spindexer, 0),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new WaitCommand(500),
                new TransferArmDownCommand(transferArm),

                new SpindexGoToCommand(spindexer, 120),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new WaitCommand(500),
                new TransferArmDownCommand(transferArm),

                new SpindexGoToCommand(spindexer, 240),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new WaitCommand(500),
                new TransferArmDownCommand(transferArm),


                new TransferWheelOffCommand(transferWheel),
                new ShooterOffCommand(shooter)
        );
    }
}
