package org.firstinspires.ftc.teamcode.subsystems.hood;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HoodSubsystem extends SubsystemBase {
    private Servo hood;
    private ConstantsHood constants;
    public HoodSubsystem(HardwareMap hwMap){
        hood = hwMap.get(Servo.class, "hood");
    }

    public void manual(Gamepad gamepad, Telemetry telemetry){
        if (gamepad.right_trigger > 0){
            if (hood.getPosition() + gamepad.right_trigger*0.3 < constants.max){
                hood.setPosition(hood.getPosition() + gamepad.right_trigger * 0.3);
            } else {
                hood.setPosition(constants.max);
            }
        }

        if (gamepad.left_trigger > 0){
            if (hood.getPosition() - gamepad.left_trigger*0.3 < constants.min){
                hood.setPosition(hood.getPosition() - gamepad.left_trigger * 0.3);
            } else {
                hood.setPosition(constants.min);
            }
        }
        telemetry.addData("current hood position is", hood.getPosition());
    }

}
