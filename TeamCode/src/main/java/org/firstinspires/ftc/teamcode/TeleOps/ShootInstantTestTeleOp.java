package org.firstinspires.ftc.teamcode.TeleOps;

import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.Commands.ShootSequenceInstantCommand;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

@TeleOp(name = "Instant shoot", group = "test")
public class ShootInstantTestTeleOp extends Robot {
    int ballnum = 1;
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        GamepadEx controller = new GamepadEx(gamepad1);

        waitForStart();

        Button shoot = new GamepadButton(
                controller, GamepadKeys.Button.A
        );

        controller.getGamepadButton(GamepadKeys.Button.A);
        shoot.whenPressed(new ShootSequenceInstantCommand(spindexer, transferArm, transferWheel, ballnum, telemetry));

        while (opModeIsActive()){
            telemetry.addData("opMode is ACTIVE",controller.getGamepadButton(GamepadKeys.Button.A).get());
            update();
        }

    }
}
