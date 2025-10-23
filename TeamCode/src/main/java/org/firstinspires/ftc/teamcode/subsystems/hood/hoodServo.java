package org.firstinspires.ftc.teamcode.subsystems.hood;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class hoodServo {
    private Servo hood;
    public void init(HardwareMap hwMap){
        hood = hwMap.get(Servo.class, "hood");
    }
    public void manual(Gamepad gamepad){
        hood.setPosition(hood.getPosition() + gamepad.left_stick_y);
    }

}
