package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@TeleOp (name = "Shooter Look Up", group = "test")
public class ShooterLookUpFinderTeleOp extends Robot {
    @Override
    public void runOpMode() throws InterruptedException{
        GamepadEx controller = new GamepadEx(gamepad1);
        GamepadEx controller2 = new GamepadEx(gamepad2);
        initialize();
        controller.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor));
        controller.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(()-> transferWheel.on()));
        controller.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new InstantCommand(()-> transferWheel.off()));
        controller.getGamepadButton(GamepadKeys.Button.A).whenPressed(new InstantCommand(()-> transferArm.up()));
        controller.getGamepadButton(GamepadKeys.Button.B).whenPressed(new InstantCommand(()-> transferArm.down()));

        controller2.getGamepadButton(GamepadKeys.Button.A).whenPressed(new InstantCommand(()-> spindexer.outake(0)));
        controller2.getGamepadButton(GamepadKeys.Button.B).whenPressed(new InstantCommand(()-> spindexer.outake(1)));
        controller2.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(()-> spindexer.outake(2)));
        waitForStart();
        while (opModeIsActive()){
//            shooter.periodic();
            update();
        }
    }
}
