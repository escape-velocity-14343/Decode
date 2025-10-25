package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretSubsystem;

public class ShootCommand extends CommandBase {
    private TransferArmSubsystem transferArm;
    private SpindexerSubsystem spindexer;
    private Gamepad gamepad;
    public ShootCommand(HardwareMap hwMap, Gamepad gamepad) {
        transferArm = new TransferArmSubsystem(hwMap);
        spindexer = new SpindexerSubsystem(hwMap);
        this.gamepad = gamepad;
    }
    @Override
    public void execute(){
        if (gamepad.dpad_left){
            spindexer.outake(1);
            transferArm.up();
            transferArm.down();
        }
        if (gamepad.dpad_down){
            spindexer.outake(2);
            transferArm.up();
            transferArm.down();
        }
        if (gamepad.dpad_right){
            spindexer.outake(3);
            transferArm.up();
            transferArm.down();
        }
    }
}
