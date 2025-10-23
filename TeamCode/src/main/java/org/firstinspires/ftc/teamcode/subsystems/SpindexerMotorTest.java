package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name="SpndexerMotorTest", group = "Linear OpMode")
public class SpindexerMotorTest extends LinearOpMode {
    private SpindexerRun test;


    @Override
    public void runOpMode() {
        test = new SpindexerRun();
        waitForStart();
        test.init(telemetry, hardwareMap);
        while (opModeIsActive()) {
            test.execute(gamepad1);
        }
    }

}
