package org.firstinspires.ftc.teamcode.TeleOps;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Log tester", group = "test")
public class LogTestTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while (opModeIsActive()){
            Log.println(Log.INFO, "hello over and over again", "hello over and over again");
        }
    }
}
