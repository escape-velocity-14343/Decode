package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

public class SpindexerSubsystem extends SubsystemBase {
    DcMotor spindexer;
    sensOrangeEncoder spindexerEncoder;
    SquIDController powerSet;
    public SpindexerSubsystem(HardwareMap hwMap){
        spindexer = hwMap.get(DcMotor.class,"spindexerMotor");
        spindexerEncoder = new sensOrangeEncoder("turretEncoder", hwMap);
        spindexerEncoder.setPositionOffset(ConstantsSpindexer.offset);
    }
    public void intake(int ballNum){
        while (spindexerEncoder.getDegrees() != 120*(ballNum - 1)){
            spindexer.setPower(powerSet.calculate(120*(ballNum - 1), spindexerEncoder.getDegrees()));
        }
    }

    public void outake(int ballNum){
        while (spindexerEncoder.getDegrees() != Math.abs((120*(ballNum - 1) + 180) - 360)){
            spindexer.setPower(powerSet.calculate(Math.abs((120*(ballNum - 1) + 180) - 360),spindexerEncoder.getDegrees()));
        }
    }
}
