package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.command.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.IntakeOffCommand;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.PoopCommand;
import org.firstinspires.ftc.teamcode.command.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.ShooterIntakeCommandGroup;
import org.firstinspires.ftc.teamcode.command.ShooterOffCommand;
import org.firstinspires.ftc.teamcode.command.TransferArmDownCommand;
import org.firstinspires.ftc.teamcode.command.TransferWheelOffCommand;
import org.firstinspires.ftc.teamcode.command.TurretAimDefaultCommand;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

import java.io.File;

import dev.nullftc.profiler.Profiler;
import dev.nullftc.profiler.entry.BasicProfilerEntryFactory;
import dev.nullftc.profiler.exporter.CSVProfilerExporter;

@TeleOp (name = "TeleOp V2", group = "LinearOpMode")
public class TeleOpV2 extends Robot {
    @Override
    public void runOpMode() throws InterruptedException{
        GamepadEx controller = new GamepadEx(gamepad1);
        GamepadEx controller2 = new GamepadEx(gamepad2);
        initialize();
        pinpointSubsystem.update();
        drive.setHeadingSupplier(() -> Math.toRadians(pinpointSubsystem.getHeading().getDegrees() - 90 * StaticValues.getM()));
        drive.setDefaultCommand(new DefaultDriveCommand(drive,
                () -> -controller.getLeftY(),
                () -> -controller.getLeftX(),
                controller::getRightX));
        turret.setDefaultCommand(new TurretAimDefaultCommand(aprilTag, turret, pinpointSubsystem));

        controller.getGamepadButton(GamepadKeys.Button.X).whenPressed(new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel,  () -> 1, aprilTag, -1, () ->0));
        controller.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, () -> 2, aprilTag, -1, () ->0));
        controller.getGamepadButton(GamepadKeys.Button.A).whenPressed(new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint));
        controller.getGamepadButton(GamepadKeys.Button.B).whenPressed(new PoopCommand(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint));
        controller.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor));
        controller.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new InstantCommand(()->intake.setPower(-0.5))).whenReleased(new IntakeOffCommand(intake));
        controller2.getGamepadButton(GamepadKeys.Button.A).whenPressed(new ShooterIntakeCommandGroup(shooter, spindexer));
        new Trigger(()-> gamepad1.options && gamepad1.share).whileActiveOnce(new InstantCommand(()->pinpointSubsystem.setPose(new Pose2d(pinpointSubsystem.getTranslation(), Rotation2d.fromDegrees(90 * StaticValues.getM())))));
        new Trigger(()-> gamepad1.touchpad).whileActiveOnce(new ParallelCommandGroup(
                new IntakeOffCommand(intake),
                new TransferArmDownCommand(transferArm),
                new TransferWheelOffCommand(transferWheel),
                new InstantCommand(()->spindexer.setTargetPosition(spindexer.getDegrees())),
                new ShooterOffCommand(shooter)
        ));
        new Trigger(() -> !Util.inRange(gamepad2.left_stick_x, 0, 0.5)).whileActiveContinuous(()->spindexer.setPowerManual(gamepad2.left_stick_x)).whenInactive(()->spindexer.setPower(0));
        controller2.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(()->{intakeCam.setEnabled(true); intakeCam.setEnableLiveView(true); aprilTag.setEnableLiveView(true);}));
        controller2.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new InstantCommand(()->{intakeCam.setEnabled(false); intakeCam.setEnableLiveView(false); aprilTag.setEnableLiveView(false);}));

        //offsetter.getGamepadButton(GamepadKeys.Button.X).whenPressed(new OffSetBallCommand(0));
        //offsetter.getGamepadButton(GamepadKeys.Button.A).whenPressed(new OffSetBallCommand(1));
        //offsetter.getGamepadButton(GamepadKeys.Button.B).whenPressed(new OffSetBallCommand(2));
        //new Trigger(()-> controller.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5).whileActiveContinuous(new InstantCommand(()->aprilTag.detect()));
        setTurretCamExposure();
        setIntakeCamExposure();

        waitForStart();
        intakeCam.setEnableLiveView(false);
        aprilTag.setEnableLiveView(false);
        while (opModeIsActive()) {
            telemetry.addData("Pose x", pinpointSubsystem.getPose().getX());
            telemetry.addData("Pose y", pinpointSubsystem.getPose().getY());
            telemetry.addData("Pose heading", pinpointSubsystem.getHeading().getDegrees());
            telemetry.addData("Pose velocity", pinpointSubsystem.getTranslationalVelocity().getNorm());
            telemetry.addData("ATAG FPS", aprilTag.getFPS());
            telemetry.addData("Intake Cam FPS", intakeCam.getFPS());
            update();
        }
        //end();
    }
}
