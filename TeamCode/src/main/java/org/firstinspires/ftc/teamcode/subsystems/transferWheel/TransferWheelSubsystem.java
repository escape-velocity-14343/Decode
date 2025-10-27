package org.firstinspires.ftc.teamcode.subsystems.transferWheel;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TransferWheelSubsystem extends SubsystemBase {
    private CRServo transferWheel;
    public TransferWheelSubsystem(HardwareMap hwMap){
        transferWheel = hwMap.get(CRServo.class, "transferWheel");
    }

    public void on(){
        transferWheel.setPower(-1);
    }
    public void off(){
        transferWheel.setPower(0);
    }

    public void manual(Gamepad gamepad){
        if (gamepad.y){
            transferWheel.setPower(-1);
        } else {
            transferWheel.setPower(0);
        }
    }

}
