package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intakeServo {
    private CRServo intake;
    public void init(HardwareMap hwMap) {
        intake = hwMap.get(CRServo.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void on(){
        intake.setPower(-1);
    }
    public void off(){
        intake.setPower(0);
    }
    public void manual(Gamepad gamepad){
        if (gamepad.aWasPressed()){
            intake.setPower(1);
        } else if (gamepad.bWasPressed()) {
            intake.setPower(0);
        }
    }
}
