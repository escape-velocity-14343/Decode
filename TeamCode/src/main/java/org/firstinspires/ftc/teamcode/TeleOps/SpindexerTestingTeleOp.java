package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

@TeleOp(name = "SpindexerTesting", group = "testing")
public class SpindexerTestingTeleOp extends LinearOpMode {
    private SpindexerSubsystem spindexer;
    public void runOpMode(){
        spindexer = new SpindexerSubsystem(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()){
            if (gamepad1.a){
                spindexer.intake(1);
            }
            if (gamepad1.b){
                spindexer.intake(2);
            }
            if (gamepad1.x){
                spindexer.intake(3);
            }
            if (gamepad1.dpad_down){
                spindexer.outake(1);
            }
            if (gamepad1.dpad_right){
                spindexer.outake(2);
            }
            if (gamepad1.dpad_left){
                spindexer.outake(3);
            }
        }
    }
}
