package org.firstinspires.ftc.teamcode.subsystems.transferArm;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TransferArmSubsystem extends SubsystemBase {
    private Servo transferArm;
    private ConstantsTransferArm constants;

    public TransferArmSubsystem(HardwareMap hwMap) {
        transferArm = hwMap.get(Servo.class, "transferArm");
        constants = new ConstantsTransferArm();
    }

    public void up() {
        transferArm.setPosition(constants.up);
    }
    public void down(){
        transferArm.setPosition(constants.down);
    }

    public void manual(Gamepad gamepad){
        if (gamepad.x){
            transferArm.setPosition(constants.up);
        }
    }
}
