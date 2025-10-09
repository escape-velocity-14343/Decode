package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {
    private DcMotor turretMotor = null;
    private Servo hood = null;
    private double needed_angle;
    private double unitPerDegree;
    private double ballVelocity;

    public void init (HardwareMap hwMap){
        turretMotor = hwMap.get(DcMotor.class, "turretMotor");
        turretMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        unitPerDegree = Constants.servo_unit_per_degree;
        ballVelocity = Constants.ball_speed_shooter;

    }

    public void shoot(double distance, double height){
        needed_angle = (ballVelocity * ballVelocity + Math.sqrt(distance));
        //messed up the math and im not doing allthat rn
    }

}
