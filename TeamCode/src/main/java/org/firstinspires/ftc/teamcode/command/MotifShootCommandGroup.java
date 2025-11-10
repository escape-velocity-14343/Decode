package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.LogCatCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class MotifShootCommandGroup extends SequentialCommandGroup {
    public MotifShootCommandGroup(SpindexerSubsystem spindexer, ShooterSubsystem shooter, TransferWheelSubsystem transferWheel, TransferArmSubsystem transferArm, AprilTagSubsystem aprilTag){
        addRequirements(spindexer, shooter, transferWheel, transferArm);
        addCommands(
                new ShootWithDistCommand(shooter, aprilTag),
                new LogKittenCommand(Log.ASSERT,"auto", "shooter on"),
                new ShooterOnCommand(shooter),

                new LogKittenCommand(Log.ASSERT,"auto1", "shooting ball one"),
                new LogKittenCommand(Log.ASSERT,"auto1", "shooting"),
                new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, StaticValues.getMotif(0), aprilTag),
//                new SpindexOutCommand(spindexer, StaticValues.getMotif(0)),
//                new LogKittenCommand(Log.ASSERT,"auto1", "transferwheel on"),
//                new TransferWheelOnCommand(transferWheel),
//                new LogKittenCommand(Log.ASSERT,"auto1", "arm up"),
//                new TransferArmUpCommand(transferArm),
//                new LogKittenCommand(Log.ASSERT,"auto1", "arm down"),
//                new TransferArmDownCommand(transferArm),
//                new WaitCommand(5000),

                new ShootWithDistCommand(shooter, aprilTag),
                new LogKittenCommand(Log.ASSERT,"auto2", "shooting ball two"),
                new LogKittenCommand(Log.ASSERT,"auto1", "shooting"),
                new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, StaticValues.getMotif(1), aprilTag),
//                new LogKittenCommand(Log.ASSERT,"auto2", "spindex"),
//                new SpindexOutCommand(spindexer, StaticValues.getMotif(1)),
//                new LogKittenCommand(Log.ASSERT,"auto2", "transfer arm up"),
//                new TransferArmUpCommand(transferArm),
//                new LogKittenCommand(Log.ASSERT,"auto2", "transfer arm down"),
//                new TransferArmDownCommand(transferArm),
//                new WaitCommand(5000),

                new ShootWithDistCommand(shooter, aprilTag),
                new LogKittenCommand(Log.ASSERT,"auto3", "shooting ball three"),
                new LogKittenCommand(Log.ASSERT,"auto1", "shooting"),
                new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, StaticValues.getMotif(2), aprilTag)
//                new LogKittenCommand(Log.ASSERT,"auto3", "spindex"),
//                new SpindexOutCommand(spindexer, StaticValues.getMotif(2)),
//                new LogKittenCommand(Log.ASSERT,"auto3", "transfer arm up"),
//                new TransferArmUpCommand(transferArm),
//                new LogKittenCommand(Log.ASSERT,"auto3", "transfer arm down"),
//                new TransferArmDownCommand(transferArm),
//                new WaitCommand(5000)
        );
    }
}
