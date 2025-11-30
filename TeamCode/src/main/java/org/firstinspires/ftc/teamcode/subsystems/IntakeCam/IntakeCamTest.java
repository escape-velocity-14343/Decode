package org.firstinspires.ftc.teamcode.subsystems.IntakeCam;

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
            telemetry.addData("Artifact Pose X", intakeCam.getRobotCentricTranslation().getX());
            telemetry.addData("Artifact Pose Y", intakeCam.getRobotCentricTranslation().getY());
            telemetry.addData("Artifact Pixel X", intakeCam.getArtifactPos().getX());
            telemetry.addData("Artifact Pixel Y", intakeCam.getArtifactPos().getY());
            Log.i("IntakeCamTest", "Hue: " + intakeCam.getArtifactPose());
        }
        end();
    }
}
