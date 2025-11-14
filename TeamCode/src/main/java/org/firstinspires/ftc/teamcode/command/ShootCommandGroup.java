package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

import java.util.function.IntSupplier;

public class ShootCommandGroup extends SequentialCommandGroup {

    public ShootCommandGroup(ShooterSubsystem shooter, SpindexerSubsystem spindexer, TransferArmSubsystem transferArm, TransferWheelSubsystem transferWheel, int ballColor, AprilTagSubsystem atag, IntSupplier ballNum) {
//        telemetry.addData("hello", 1);
        addCommands(
                //new GoToPointWithDefaultCommand(new Pose2d(23, (36)* StaticValues.getM(), Rotation2d.fromDegrees((-215)*StaticValues.getM())), toPoint), // shooting
                new ShootWithDistCommand(shooter, atag).alongWith(new SpindexOutCommand(spindexer, ballColor, ballNum.getAsInt())),
                new LogKittenCommand(Log.ASSERT, "SHOOT COMMAND", "spindexing done"),
                new TransferWheelOnCommand(transferWheel),
                new TransferArmUpCommand(transferArm),
                new TransferArmDownCommand(transferArm),
                new TransferWheelOffCommand(transferWheel)
            );
        addRequirements(shooter, spindexer, transferArm, transferWheel);
    }
}
