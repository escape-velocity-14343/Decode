package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="AprilTagAimingTest", group = "Linear OpMode")
public class AprilTagAimingTest extends LinearOpMode {
    private AprilTagAim aim;


    @Override
    public void runOpMode() {
        AprilTagAim aim = new AprilTagAim();
        aim.init(hardwareMap, telemetry);
        waitForStart();
        while(opModeIsActive()) {
            aim.execute();
            telemetry.update();
        }
    }
}
