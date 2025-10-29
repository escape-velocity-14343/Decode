package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.InstantCommand;
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
                    Log.println(Log.INFO, "SHOOT COMMAND GROUP", "spindexing to the right place");
                }),
                new TransferArmDownCommand(transferArm),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "SHOOT COMMAND GROUP", "TRANSFER ARM DOWN");}),
                new SpindexOutCommand(spindexer, ballNum),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "SHOOT COMMAND GROUP", "Spindex Out Complete");}),
                new TransferWheelOnCommand(transferWheel),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "SHOOT COMMAND GROUP", "Transfer Wheels ON");}),
                new TransferArmUpCommand(transferArm),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "SHOOT COMMAND GROUP", "Transfer Arm Up");}),
                new TransferArmDownCommand(transferArm),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "SHOOT COMMAND GROUP", "Transfer Arm Down Again");}),
                new TransferWheelOffCommand(transferWheel),
                new InstantCommand(()->{
                    Log.println(Log.INFO, "SHOOT COMMAND GROUP", "Transfer Wheel Off");})
            );
        addRequirements(spindexer, transferArm, transferWheel);
    }
}
