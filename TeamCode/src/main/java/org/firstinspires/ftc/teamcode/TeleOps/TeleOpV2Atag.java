package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.AprilTagAimDefaultCommand;
import org.firstinspires.ftc.teamcode.command.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.IntakeOffCommand;
import org.firstinspires.ftc.teamcode.command.IntakeOnCommand;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.PoopCommand;
import org.firstinspires.ftc.teamcode.command.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.ShootWithDistCommand;
import org.firstinspires.ftc.teamcode.command.ShooterIntakeCommandGroup;
import org.firstinspires.ftc.teamcode.command.ShooterOffCommand;
import org.firstinspires.ftc.teamcode.command.TransferArmDownCommand;
import org.firstinspires.ftc.teamcode.command.TransferWheelOffCommand;
import org.firstinspires.ftc.teamcode.command.TurretAimCommand;
import org.firstinspires.ftc.teamcode.command.TurretAimDefaultCommand;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

@TeleOp (name = "TeleOp V2 AprilTag", group = "LinearOpMode")
public class TeleOpV2Atag extends Robot {
    Translation2d cornerRelocPos = new Translation2d(-55, -59*StaticValues.getM());
    @Override
    public void runOpMode() throws InterruptedException{
        GamepadEx controller = new GamepadEx(gamepad1);
        GamepadEx controller2 = new GamepadEx(gamepad2);
        initialize();
        shooter.useAtag(true);

        pinpointSubsystem.update();
        drive.setHeadingSupplier(() -> Math.toRadians(pinpointSubsystem.getHeading().getDegrees() - 90 * StaticValues.getM()));
        drive.setDefaultCommand(new DefaultDriveCommand(drive,
                () -> -controller.getLeftY(),
                () -> -controller.getLeftX(),
                controller::getRightX));
        CommandScheduler.getInstance().setDefaultCommand(turret, new AprilTagAimDefaultCommand(aprilTag, turret, pinpointSubsystem));
        //a//im.setUsePinpoint(false);
        //shooter.setDefaultCommand(new ShooterVelocityDefaultCommand(shooter, aprilTag));

        controller.getGamepadButton(GamepadKeys.Button.X).whenPressed(new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel,  () -> 1, pinpointSubsystem, -1, () ->0));
        controller.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, () -> 2, pinpointSubsystem, -1, () ->0));
        controller.getGamepadButton(GamepadKeys.Button.A).whenPressed(new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem));
        controller.getGamepadButton(GamepadKeys.Button.B).whenPressed(new PoopCommand(spindexer, shooter, transferWheel, transferArm, pinpointSubsystem));
        controller.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor));
        controller.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new InstantCommand(()->intake.setPower(-0.5))).whenReleased(new IntakeOffCommand(intake));
        new Trigger(()-> gamepad1.options && gamepad1.share).whileActiveOnce(new InstantCommand(()->pinpointSubsystem.setPose(new Pose2d(pinpointSubsystem.getTranslation(), Rotation2d.fromDegrees(90 * StaticValues.getM())))));
        new Trigger(()-> gamepad1.touchpad).whileActiveOnce(new ParallelCommandGroup(
                new IntakeOffCommand(intake),
                new TransferArmDownCommand(transferArm),
                new TransferWheelOffCommand(transferWheel),
                new InstantCommand(()->spindexer.setTargetPosition(spindexer.getDegrees())),
                new ShooterOffCommand(shooter)
        ));
        new Trigger(() -> !Util.inRange(gamepad1.right_trigger, 0, 0.5)).whileActiveOnce(new IntakeOnCommand(intake)).whenInactive(new IntakeOffCommand(intake));

        new Trigger(()-> gamepad2.touchpad).whileActiveOnce(new ParallelCommandGroup(
                new IntakeOffCommand(intake),
                new TransferArmDownCommand(transferArm),
                new TransferWheelOffCommand(transferWheel),
                new InstantCommand(()->spindexer.setTargetPosition(spindexer.getDegrees())),
                new ShooterOffCommand(shooter)
        ));
        new Trigger(()->!spindexer.hasSpace()).whileActiveOnce(new ShootWithDistCommand(shooter, pinpointSubsystem)).whileActiveOnce(new InstantCommand(()->gamepad1.rumble(250)));
        new Trigger(() -> !Util.inRange(gamepad2.left_stick_x, 0, 0.5)).whileActiveContinuous(()->spindexer.setPowerManual(gamepad2.left_stick_x)).whenInactive(()->spindexer.setPower(0));
        //controller2.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(()->{intakeCam.setEnabled(true); intakeCam.setEnableLiveView(true); aprilTag.setEnableLiveView(true);}));
        //controller2.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new InstantCommand(()->{intakeCam.setEnabled(false); intakeCam.setEnableLiveView(false); aprilTag.setEnableLiveView(false);}));
        controller2.getGamepadButton(GamepadKeys.Button.A).whenPressed(new ShooterIntakeCommandGroup(shooter, spindexer));
        controller2.getGamepadButton(GamepadKeys.Button.B).whenPressed(new InstantCommand(() -> toPoint.setTarget(new Pose2d(-37, 40, new Rotation2d(0)))));
        controller.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new InstantCommand(()->pinpointSubsystem.setPose(new Pose2d(cornerRelocPos, Rotation2d.fromDegrees(-90 * StaticValues.getM()))))
        );

        setTurretCamExposure();
        setIntakeCamExposure();

        waitForStart();
        cornerRelocPos = new Translation2d(-65, -59*StaticValues.getM());
        intakeCam.setEnableLiveView(false);
        aprilTag.setEnableLiveView(false);
        intakeCam.end();
        while (opModeIsActive()) {
            telemetry.addData("Pose x", pinpointSubsystem.getPose().getX());
            telemetry.addData("Pose y", pinpointSubsystem.getPose().getY());
            telemetry.addData("Pose heading", pinpointSubsystem.getHeading().getDegrees());
            telemetry.addData("Pose velocity", pinpointSubsystem.getTranslationalVelocity().getNorm());

            update();
        }
        //end();
    }
}
