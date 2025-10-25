package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

public class SpindexerMotor {
    DcMotor spindexer;
    sensOrangeEncoder spindexerEncoder;
    SquIDController powerSet;
    public void init(HardwareMap hwMap){
        spindexer = hwMap.get(DcMotor.class,"spindexerMotor");
        spindexerEncoder = new sensOrangeEncoder("turretEncoder", hwMap);
        //powerset initialize
    }
    public void intake(int ballNum){
        while (spindexerEncoder.getDegrees() != 60*(ballNum - 1)){
            spindexer.setPower(1/*use SQUID*/);
        }
    }
}
