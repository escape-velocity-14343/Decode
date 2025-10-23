package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

public class SpindexerGoTo {
    public SpindexerMotor spindexerMotor;
    public SquIDController powerManage;
    public sensOrangeEncoder encoder;
    public void init(HardwareMap hwMap){
        spindexerMotor = new SpindexerMotor();
        powerManage = new SquIDController();
        encoder = new sensOrangeEncoder("spindexerEncoder", hwMap);
        spindexerMotor.init(hwMap);
    }
    public void goTo(double angle){
        spindexerMotor.setPowerTo(powerManage.calculate(angle, encoder.getDegrees()));
    }
}
