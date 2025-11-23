package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class MotifShootCommandGroup extends SequentialCommandGroup {
    public MotifShootCommandGroup(SpindexerSubsystem spindexer, ShooterSubsystem shooter, TransferWheelSubsystem transferWheel, TransferArmSubsystem transferArm, AprilTagSubsystem aprilTag, DefaultGoToPointCommand toPoint){
        addRequirements(spindexer, shooter, transferWheel, transferArm);
        //Log.i("motif shoot", "motif is: " + StaticValues.getMotif(0) +  StaticValues.getMotif(1) + StaticValues.getMotif(2));
        //Log.i("motif shoot" , "artifacts are: " + StaticValues.getArtifacts(0) +  StaticValues.getArtifacts(1) + StaticValues.getArtifacts(2));
        addCommands(
//                new LogKittenCommand(Log.ASSERT, "motif shoot", "motif is: " + (() -> StaticValues.getMotif(0)).get() +  StaticValues.getMotif(1) + StaticValues.getMotif(2));
                //new ShootWithDistCommand(shooter, aprilTag),
                //new ShootingPosCommand(toPoint),
                new LogKittenCommand(Log.ASSERT,"motif shoot", "shooter on"),
                new LogKittenCommand(Log.ASSERT, "motif shoot", "motif is: " + StaticValues.getMotif(0) +  StaticValues.getMotif(1) + StaticValues.getMotif(2)),
                new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting ball one"),
                new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting"),
                new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, () -> -1, aprilTag, 0, ()-> StaticValues.getBallOffset()),
//                new SpindexOutCommand(spindexer, StaticValues.getMotif(0)),
//                new LogKittenCommand(Log.ASSERT,"auto1", "transferwheel on"),
//                new TransferWheelOnCommand(transferWheel),
//                new LogKittenCommand(Log.ASSERT,"auto1", "arm up"),
//                new TransferArmUpCommand(transferArm),
//                new LogKittenCommand(Log.ASSERT,"auto1", "arm down"),
//                new TransferArmDownCommand(transferArm),
//                new WaitCommand(5000),

                //new ShootWithDistCommand(shooter, aprilTag),
                new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting ball two"),
                new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting"),
                new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, () -> -1, aprilTag, 1, ()-> StaticValues.getBallOffset()),
//                new LogKittenCommand(Log.ASSERT,"auto2", "spindex"),
//                new SpindexOutCommand(spindexer, StaticValues.getMotif(1)),
//                new LogKittenCommand(Log.ASSERT,"auto2", "transfer arm up"),
//                new TransferArmUpCommand(transferArm),
//                new LogKittenCommand(Log.ASSERT,"auto2", "transfer arm down"),
//                new TransferArmDownCommand(transferArm),
//                new WaitCommand(5000),

                //new ShootWithDistCommand(shooter, aprilTag),
                new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting ball three"),
                new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting " + StaticValues.getMotif(2)),
                new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, () -> -1, aprilTag, 2, ()-> StaticValues.getBallOffset())
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
