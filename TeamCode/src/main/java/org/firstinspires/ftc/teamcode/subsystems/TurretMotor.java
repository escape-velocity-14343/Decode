package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class TurretMotor extends SubsystemBase{
    private DcMotor turretMotor;

    public void init (HardwareMap hwMap){
        turretMotor = hwMap.get(DcMotor.class, "turretMotor");
    }

    public void setPowerTo(double power) {
        turretMotor.setPower(power);
    }
}

