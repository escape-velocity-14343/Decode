package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "motorTest", group = "LinearOpMode")
public class motorTest extends LinearOpMode {
    @Override
    public void runOpMode() {
    DcMotor testMotor = hardwareMap.get(DcMotor.class, "testMotor");
    waitForStart();
    while (opModeIsActive()) {
        testMotor.setPower(gamepad1.left_stick_y);
    }
}
}