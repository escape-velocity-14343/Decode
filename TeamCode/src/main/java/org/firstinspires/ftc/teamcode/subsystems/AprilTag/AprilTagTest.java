package org.firstinspires.ftc.teamcode.subsystems.AprilTag;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@TeleOp(group = "Test")
public class AprilTagTest extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        setTurretCamExposure();
        waitForStart();
        while (opModeIsActive()) {
            for (LynxModule hub : hubs) {
                hub.clearBulkCache();
            }
            pinpointSubsystem.periodic();
            aprilTag.periodic();
            turret.setPowerManual(0);
            turret.periodic();
            Translation2d robotPose = aprilTag.getLocalization(turret.getTurretPositionRadians(), pinpointSubsystem.getHeading());
            if (robotPose != null) {
                pinpointSubsystem.relocalize(robotPose);
                telemetry.addData("Robot X", robotPose.getX());
                telemetry.addData("Robot Y", robotPose.getY());
                telemetry.addData("Robot Heading", pinpointSubsystem.getHeading());
            }
            telemetry.update();
        }
    }
}
