package org.firstinspires.ftc.teamcode.TeleOps;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@TeleOp(group = "Test")
public class IntakeCamTest extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        setTurretCamExposure();
        setIntakeCamExposure();
        waitForStart();


        while (opModeIsActive()) {
            update();
            Log.i("IntakeCamTest", "Hue: " + intakeCam.getArtifactPose());
        }
        end();
    }
}
