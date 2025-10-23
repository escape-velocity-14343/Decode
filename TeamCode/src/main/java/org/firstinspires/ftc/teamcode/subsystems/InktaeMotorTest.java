package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="IntakeMotorTest", group = "linear OpMode")
public class InktaeMotorTest extends LinearOpMode {
    private IntakeMotor intakeMotor;
    @Override
    public void runOpMode(){
        IntakeMotor intakeMotor = new IntakeMotor();
        intakeMotor.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            intakeMotor.setPowerTo(1.0);
        }
    }

}
