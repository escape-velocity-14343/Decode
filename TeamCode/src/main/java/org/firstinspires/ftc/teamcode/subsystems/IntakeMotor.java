package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeMotor extends SubsystemBase{
    private DcMotor intakeMotor;
    public void init(HardwareMap hwMap){
        intakeMotor = hwMap.get(DcMotor.class, "intakeMotor");
    }
    public void setPowerTo (double power){
        intakeMotor.setPower(power);
    }
}
