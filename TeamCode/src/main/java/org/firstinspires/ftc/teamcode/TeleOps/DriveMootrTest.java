package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp (group = "Test")
public class DriveMootrTest extends LinearOpMode {
    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftMotor = hardwareMap.dcMotor.get("driveFrontLeft");
        backLeftMotor = hardwareMap.dcMotor.get("driveBackLeft");
        frontRightMotor = hardwareMap.dcMotor.get("driveFrontRight");
        backRightMotor = hardwareMap.dcMotor.get("driveBackRight");
        waitForStart();
        while (opModeIsActive()) {
            frontLeftMotor.setPower(gamepad1.square ? 1 : 0);
            backLeftMotor.setPower(gamepad1.cross ? 1 : 0);
            frontRightMotor.setPower(gamepad1.triangle ? 1 : 0);
            backRightMotor.setPower(gamepad1.circle ? 1 : 0);
        }
    }
}
