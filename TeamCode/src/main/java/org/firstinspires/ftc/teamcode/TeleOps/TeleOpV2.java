package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.IntakeOffCommand;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.ShooterOffCommand;
import org.firstinspires.ftc.teamcode.command.TransferArmDownCommand;
import org.firstinspires.ftc.teamcode.command.TransferWheelOffCommand;
import org.firstinspires.ftc.teamcode.command.TurretAimDefaultCommand;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
@TeleOp (name = "TeleOp V2", group = "LinearOpMode")
public class TeleOpV2 extends Robot {
    @Override
    public void runOpMode() throws InterruptedException{
        GamepadEx controller = new GamepadEx(gamepad1);
        initialize();
        pinpointSubsystem.update();
        drive.setHeadingSupplier(() -> Math.toRadians(pinpointSubsystem.getHeading().getDegrees()+ (blueAlliance ? 90 : -90)));
        drive.setDefaultCommand(new DefaultDriveCommand(drive,
                () -> -controller.getLeftY(),
                () -> -controller.getLeftX(),
                controller::getRightX));
        turret.setDefaultCommand(new TurretAimDefaultCommand(aprilTag, turret, pinpointSubsystem));

        controller.getGamepadButton(GamepadKeys.Button.X).whenPressed(new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, 1, aprilTag, -1));
        controller.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, 2, aprilTag, -1));
        controller.getGamepadButton(GamepadKeys.Button.A).whenPressed(new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint));
        controller.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor));
        controller.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new InstantCommand(()->intake.setPower(-0.5))).whenReleased(new IntakeOffCommand(intake));
        new Trigger(()-> gamepad1.options && gamepad1.share).whileActiveOnce(new InstantCommand(()->pinpointSubsystem.setPose(new Pose2d(pinpointSubsystem.getTranslation(), Rotation2d.fromDegrees(blueAlliance ? -90 : 90)))));
        new Trigger(()-> gamepad1.touchpad).whileActiveOnce(new ParallelCommandGroup(
                new IntakeOffCommand(intake),
                new TransferArmDownCommand(transferArm),
                new TransferWheelOffCommand(transferWheel),
                new InstantCommand(()->spindexer.setTargetPosition(spindexer.getDegrees())),
                new ShooterOffCommand(shooter)
        ));
        //new Trigger(()-> controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5).whileActiveContinuous(new InstantCommand(()->aprilTag.detect()));
        while(!aprilTag.isStreaming());
        aprilTag.waitForSetExposure(1000,1000);
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("Pose x", pinpointSubsystem.getPose().getX());
            telemetry.addData("Pose y", pinpointSubsystem.getPose().getY());
            telemetry.addData("Pose heading", pinpointSubsystem.getHeading().getDegrees());
            update();
        }
    }
}
