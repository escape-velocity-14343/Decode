package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

@TeleOp (name = "spindexerMotorPositionsFinder", group = "LinearOpMode")
public class spindexerMotorPositionsFinder extends LinearOpMode {
    DcMotor spindexerMotor;

    public void runOpMode() {
        sensOrangeEncoder spindexerEncoder = new sensOrangeEncoder("spindexerEncoder", hardwareMap);
        spindexerEncoder.setPositionOffset(ConstantsSpindexer.offset);
        spindexerMotor = hardwareMap.get(DcMotor.class, "spindexerMotor");
        waitForStart();
        while (opModeIsActive()) {
            spindexerMotor.setPower(gamepad1.left_stick_y);
            telemetry.addData("Spindexer Motor Position: ", spindexerEncoder.getDegrees());
            telemetry.update();
        }
    }
}

