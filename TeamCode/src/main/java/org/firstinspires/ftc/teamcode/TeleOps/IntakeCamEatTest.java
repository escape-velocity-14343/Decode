package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

@TeleOp(group="Test")
@Configurable
public class IntakeCamEatTest extends Robot {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        pinpointSubsystem.setPose(new Pose2d( 0, 0, new Rotation2d(0)));
        setTurretCamExposure();
        setIntakeCamExposure();
        waitForStart();
        intakeCam.setEnableLiveView(false);
        aprilTag.setEnableLiveView(false);
        intake.takein();

        DefaultGoToPointCommand gtpc = new DefaultGoToPointCommand(drive, pinpointSubsystem, new Pose2d(0, 0, Rotation2d.fromDegrees(0)));
        StaticValues.setMotif(0, 0);
        StaticValues.setMotif(1, 0);
        StaticValues.setMotif(2, 0);
        StaticValues.setArtifacts(0, 0);
        StaticValues.setArtifacts(1, 0);
        StaticValues.setArtifacts(2, 0);

        CommandScheduler.getInstance().schedule(gtpc.alongWith(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor)));

        while (!isStopRequested()){
            update();

            gtpc.setTarget(new Pose2d(intakeCam.getFieldCoordinates(pinpointSubsystem.getPose()), new Rotation2d(0)));

            telemetry.addData("x", pinpointSubsystem.getPose().getX());
            telemetry.addData("y", pinpointSubsystem.getPose().getY());
            telemetry.addData("heading", pinpointSubsystem.getPose().getHeading());

        }
        end();
    }
}