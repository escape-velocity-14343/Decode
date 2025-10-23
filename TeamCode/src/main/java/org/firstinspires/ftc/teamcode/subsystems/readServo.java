package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "readServo", group = "Linear OpMode")
public class readServo extends LinearOpMode {
    private Servo hood;
    private Servo transferArm;

    @Override
    public void runOpMode() {
        hood = hardwareMap.get(Servo.class, "hood");
        transferArm = hardwareMap.get(Servo.class, "transferArm");
        waitForStart();
        while (opModeIsActive()) {
            double cur_hood = hood.getPosition();
            if (gamepad1.aWasPressed()) {
                hood.setPosition(cur_hood + 0.1);
            }
            if (gamepad1.bWasPressed()){
                hood.setPosition(cur_hood - 0.1);
            }
            telemetry.addData("hood", cur_hood);
            double cur_transferArm = transferArm.getPosition();
            if (gamepad1.xWasPressed()){
                transferArm.setPosition(cur_transferArm + 0.1);
            }
            if (gamepad1.yWasPressed()){
                transferArm.setPosition(cur_transferArm - 0.1);
            }
            telemetry.addData("transferArm", cur_transferArm);
            telemetry.update();
        }
    }
}
