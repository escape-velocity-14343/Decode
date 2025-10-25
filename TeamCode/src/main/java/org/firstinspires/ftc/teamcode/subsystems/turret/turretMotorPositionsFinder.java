package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

@TeleOp (name = "turretMotorPositionsFinder", group = "LinearOpMode")
public class turretMotorPositionsFinder extends LinearOpMode {
    DcMotor turretMotor;

    public void runOpMode() {
        sensOrangeEncoder turretEncoder = new sensOrangeEncoder("turretEncoder", hardwareMap);
        turretMotor = hardwareMap.get(DcMotor.class, "turretMotor");
        waitForStart();
        while (opModeIsActive()) {
            turretMotor.setPower(gamepad1.left_stick_y);
            telemetry.addData("turret Motor Position: ", turretEncoder.getDegrees());
            telemetry.update();
        }
    }
}

