package org.firstinspires.ftc.teamcode.subsystems.hood;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class hoodServo {
    private Servo hood;
    private ConstantsHood cnostats;
    public void init(HardwareMap hwMap){
        hood = hwMap.get(Servo.class, "hood");
    }
    public void manual(Gamepad gamepad, Telemetry telemetry){
        if (hood.getPosition() + gamepad.left_stick_y*0.3 < cnostats.max && hood.getPosition() + gamepad.left_stick_y*0.3 > cnostats.min) {
            hood.setPosition(hood.getPosition() + gamepad.left_stick_y * 0.3);
        } else if (hood.getPosition() + gamepad.left_stick_y*0.3 < 0.3) {
            hood.setPosition(cnostats.min);
        } else {
            hood.setPosition(cnostats.max);
        }
        telemetry.addData("current hood position is", hood.getPosition());
    }

}
