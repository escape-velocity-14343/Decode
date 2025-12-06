package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PinpointSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class PoopCommand extends SequentialCommandGroup {
    public PoopCommand(SpindexerSubsystem spindexer, ShooterSubsystem shooter, TransferWheelSubsystem transferWheel, TransferArmSubsystem transferArm, PinpointSubsystem localizer){
        addRequirements(spindexer, shooter, transferWheel, transferArm);
        //Log.i("motif shoot", "motif is: " + StaticValues.getMotif(0) +  StaticValues.getMotif(1) + StaticValues.getMotif(2));
        //Log.i("motif shoot" , "artifacts are: " + StaticValues.getArtifacts(0) +  StaticValues.getArtifacts(1) + StaticValues.getArtifacts(2));
        addCommands(
//                new LogKittenCommand(Log.ASSERT, "motif shoot", "motif is: " + (() -> StaticValues.getMotif(0)).get() +  StaticValues.getMotif(1) + StaticValues.getMotif(2));
                //new ShootWithDistCommand(shooter, aprilTag),
                new ShootWithDistCommand(shooter, localizer).alongWith(new SpindexPosSetCommand(spindexer, 0),
                        new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting ball one"),
                        new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting"),
                        new InstantCommand(() -> StaticValues.setArtifacts(0,0))),


                new TransferWheelOnCommand(transferWheel).alongWith(
                        new LogKittenCommand(Log.ASSERT, "SHOOT COMMAND", "spindexing done"),
                        new TransferArmUpCommand(transferArm)
                ),

                new TransferArmDownCommand(transferArm),
                //new TransferWheelOffCommand(transferWheel),
//                new SpindexOutCommand(spindexer, StaticValues.getMotif(0)),
//                new LogKittenCommand(Log.ASSERT,"auto1", "transferwheel on"),
//                new TransferWheelOnCommand(transferWheel),
//                new LogKittenCommand(Log.ASSERT,"auto1", "arm up"),
//                new TransferArmUpCommand(transferArm),
//                new LogKittenCommand(Log.ASSERT,"auto1", "arm down"),
//                new TransferArmDownCommand(transferArm),
//                new WaitCommand(5000),

                //new ShootWithDistCommand(shooter, aprilTag),

                new ShootWithDistCommand(shooter, localizer).alongWith(new SpindexPosSetCommand(spindexer, 1),
                        new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting ball two"),
                        new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting"),
                        new InstantCommand(() -> StaticValues.setArtifacts(1,0))),


                new TransferWheelOnCommand(transferWheel).alongWith(
                        new LogKittenCommand(Log.ASSERT, "SHOOT COMMAND", "spindexing done"),
                        new TransferArmUpCommand(transferArm)
                ),

                new TransferArmDownCommand(transferArm),
                //new TransferWheelOffCommand(transferWheel),
//                new LogKittenCommand(Log.ASSERT,"auto2", "spindex"),
//                new SpindexOutCommand(spindexer, StaticValues.getMotif(1)),
//                new LogKittenCommand(Log.ASSERT,"auto2", "transfer arm up"),
//                new TransferArmUpCommand(transferArm),
//                new LogKittenCommand(Log.ASSERT,"auto2", "transfer arm down"),
//                new TransferArmDownCommand(transferArm),
//                new WaitCommand(5000),

                //new ShootWithDistCommand(shooter, aprilTag),
                new ShootWithDistCommand(shooter, localizer).alongWith(
                        new SpindexPosSetCommand(spindexer, 2),
                        new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting ball three"),
                        new LogKittenCommand(Log.ASSERT,"motif shoot", "shooting " + StaticValues.getMotif(2)),
                        new InstantCommand(() -> StaticValues.setArtifacts(2,0))
                ),

                new TransferWheelOnCommand(transferWheel).alongWith(
                        new LogKittenCommand(Log.ASSERT, "SHOOT COMMAND", "spindexing done"),
                        new TransferArmUpCommand(transferArm)
                ),

                new TransferArmDownCommand(transferArm)
                //new TransferWheelOffCommand(transferWheel)
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
