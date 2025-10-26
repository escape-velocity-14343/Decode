package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

@TeleOp (name = "ShootTeleOp", group = "Testing")
public class ShootTeleOp extends Robot {

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        GamepadEx controller = new GamepadEx(gamepad1);
        waitForStart();
        controller.getGamepadButton(GamepadKeys.Button.A).whenPressed(new ShootCommandGroup(spindexer, transferArm, transferWheel, 1, telemetry));
        while (opModeIsActive()){
            update();
        }
    }
}
