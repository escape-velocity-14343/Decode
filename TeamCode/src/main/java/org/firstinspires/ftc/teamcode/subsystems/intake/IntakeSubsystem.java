package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem extends SubsystemBase {
    private CRServo intake;
    public void init(HardwareMap hwMap) {
        intake = hwMap.get(CRServo.class, "intake");
    }
    public void takein(){
        intake.setPower(1);
    }
    public void stop(){
        setPower(0);
    }

    public void setPower(double power){
        intake.setPower(power);
    }
    public void manual(Gamepad gamepad){
        if (gamepad.aWasPressed()){
            intake.setPower(1);
        } else if (gamepad.bWasPressed()) {
            intake.setPower(0);
        }
    }
}
