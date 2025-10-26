package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

@TeleOp (name = "motorTest", group = "LinearOpMode")
public class motorTest extends LinearOpMode {

    sensOrangeEncoder spindexerEncoder;
    @Override
    public void runOpMode() {
        DcMotor testMotor = hardwareMap.get(DcMotor.class, "spindexerMotor");
        spindexerEncoder = new sensOrangeEncoder("spindexerEncoder", hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            testMotor.setPower(gamepad1.left_stick_y);
            telemetry.addData("pos", spindexerEncoder.getDegrees());
            telemetry.update();
        }
    }
}