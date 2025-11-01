package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.MotifShootCommand;
import org.firstinspires.ftc.teamcode.command.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
@TeleOp (name = "TeleOp V2", group = "LinearOpMode")
public class TeleOpV2 extends Robot {
    @Override
    public void runOpMode() throws InterruptedException{
        GamepadEx controller = new GamepadEx(gamepad1);
        initialize();
        drive.setDefaultCommand(new DefaultDriveCommand(drive,
                () -> -controller.getLeftY(),
                () -> -controller.getLeftX(),
                controller::getRightX));
        controller.getGamepadButton(GamepadKeys.Button.X).whenPressed(new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, 1, telemetry));
        controller.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, 2, telemetry));
        controller.getGamepadButton(GamepadKeys.Button.A).whenPressed(new MotifShootCommand(spindexer, shooter, transferWheel, transferArm));
        controller.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor));
        controller.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new InstantCommand(()->intake.setPower(-0.5)));
        new Trigger(()-> gamepad1.options && gamepad1.share).whileActiveOnce(new InstantCommand(pinpointSubsystem::resetIMU));
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("Pose x", pinpointSubsystem.getPose().getX());
            telemetry.addData("Pose y", pinpointSubsystem.getPose().getY());
            telemetry.addData("Pose heading", pinpointSubsystem.getHeading().getDegrees());
            update();
        }
    }
}
