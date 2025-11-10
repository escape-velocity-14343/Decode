package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class ShootCommandGroup extends SequentialCommandGroup {

    public ShootCommandGroup(ShooterSubsystem shooter, SpindexerSubsystem spindexer, TransferArmSubsystem transferArm, TransferWheelSubsystem transferWheel, int ballColor, AprilTagSubsystem atag, DefaultGoToPointCommand toPoint) {
//        telemetry.addData("hello", 1);
        addCommands(
                new GoToPointWithDefaultCommand(new Pose2d(23, (36)* StaticValues.getM(), new Rotation2d((-2.3)*StaticValues.getM())), toPoint), // shooting
                new ShootWithDistCommand(shooter, atag),
                new SpindexOutCommand(spindexer, ballColor),
                new LogKittenCommand(Log.ASSERT, "SHOOT COMMAND", "spindexing done"),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new TransferArmDownCommand(transferArm),
                new TransferWheelOffCommand(transferWheel),
                new ShooterOffCommand(shooter)
            );
        addRequirements(shooter, spindexer, transferArm, transferWheel);
    }
}
