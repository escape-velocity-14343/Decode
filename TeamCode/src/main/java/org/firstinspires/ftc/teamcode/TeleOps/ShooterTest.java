package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
@TeleOp(name = "ShooterTest", group = "test")
public class ShooterTest extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        GamepadEx controller = new GamepadEx(gamepad1);
        initialize();
        controller.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(()-> shooter.on()));
        controller.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new InstantCommand(()-> shooter.setPower(0)));
        waitForStart();
        while (opModeIsActive()){
            update();
        }
    }
}
