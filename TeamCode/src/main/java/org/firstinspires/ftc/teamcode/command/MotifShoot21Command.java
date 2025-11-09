package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class MotifShootCommand extends SequentialCommandGroup {
    public MotifShootCommand(SpindexerSubsystem spindexer, ShooterSubsystem shooter, TransferWheelSubsystem transferWheel, TransferArmSubsystem transferArm, AprilTagSubsystem aprilTagSubsystem) {
        addRequirements(spindexer, shooter);
        addCommands(
                new ShootWithDistCommand(shooter, aprilTagSubsystem),
                new SpindexOutCommand(spindexer, Robot.motif[0]),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new TransferArmDownCommand(transferArm),

                new ShootWithDistCommand(shooter, aprilTagSubsystem),
                new SpindexOutCommand(spindexer, Robot.motif[1]),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new TransferArmDownCommand(transferArm),

                new ShootWithDistCommand(shooter, aprilTagSubsystem),
                new SpindexOutCommand(spindexer, Robot.motif[2]),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new TransferArmDownCommand(transferArm),

                new TransferWheelOffCommand(transferWheel),
                new ShooterOffCommand(shooter)
        );
    }
}
