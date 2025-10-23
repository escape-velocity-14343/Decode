package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SpindexerRun{
    private SpindexerGoTo spindexer;
    private Telemetry telemetry;


    public void init(Telemetry telemetry, HardwareMap hwMap) {
        double ball1 = 0;
        double ball2 = 120;
        double ball3 = 240;
        spindexer = new SpindexerGoTo();
        spindexer.init(hwMap);
        this.telemetry = telemetry;
    }
    public void execute(Gamepad gamepad1){
        if (gamepad1.a){
            spindexer.goTo(0);
            telemetry.addData("A was pressed", 1);
        } else if (gamepad1.b){
            spindexer.goTo(120);
            telemetry.addData("B was pressed", 2);
        } else if (gamepad1.x) {
            spindexer.goTo(240);
            telemetry.addData("C was pressed", 3);
        }
    }
}
