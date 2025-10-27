package org.firstinspires.ftc.teamcode.Commands;

import android.util.Log;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.LogCatCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;

public class ShootCommandGroup extends SequentialCommandGroup {

    public ShootCommandGroup(SpindexerSubsystem spindexer, TransferArmSubsystem transferArm, TransferWheelSubsystem transferWheel, int ballNum, Telemetry telemetry) {
        telemetry.addData("hello", 1);
        addCommands(
                new InstantCommand(()->{
                    Log.println(Log.INFO, "Shoot Command Star", "Shoot Command Star");}),
                new TransferArmDownCommand(transferArm),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "TRANSFER ARM DOWN", "TRANSFER ARM DOWN");}),
                new SpindexOutCommand(spindexer, ballNum),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "Spindex Out Complete", "Spindex Out Complete");}),
                new TransferWheelOnCommand(transferWheel),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "Transfer Wheels On", "Transfer Wheels ON");}),
                new TransferArmUpCommand(transferArm),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "Transfer Arm Up", "Transfer Arm Up");}),
                new TransferArmDownCommand(transferArm),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "Transfer Arm Down Again", "Transfer Arm Down Again");}),
                new TransferWheelOffCommand(transferWheel),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "Transfer Wheel Off", "Transfer Wheel Off");})
        );
        addRequirements(spindexer, transferArm, transferWheel);
    }
}
