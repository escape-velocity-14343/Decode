package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SpindexerMotor {
    private DcMotor spindexerMotor;

    public void init (HardwareMap hwMap){
        spindexerMotor = hwMap.get(DcMotor.class, "spindexerMotor");
    }

    public void setPowerTo(double power) {
        spindexerMotor.setPower(power);
    }
}
