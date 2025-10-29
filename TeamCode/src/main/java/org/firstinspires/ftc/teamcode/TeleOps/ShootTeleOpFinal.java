package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

@TeleOp (name = "Shoot FINAL TeleOp", group = "test")
public class ShootTeleOpFinal extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        GamepadEx controller = new GamepadEx(gamepad1);
        waitForStart();
        Button shoot = new GamepadButton(
                controller, GamepadKeys.Button.A
        );
    }
}
