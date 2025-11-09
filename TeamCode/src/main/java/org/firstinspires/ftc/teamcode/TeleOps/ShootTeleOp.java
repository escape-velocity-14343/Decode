package org.firstinspires.ftc.teamcode.TeleOps;

import android.util.Log;

import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.LogKittenCommand;
import org.firstinspires.ftc.teamcode.command.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@TeleOp (name = "ShootTeleOp", group = "Testing")
public class ShootTeleOp extends Robot {

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        GamepadEx controller = new GamepadEx(gamepad1);
        waitForStart();
        Button shoot = new GamepadButton(
                controller, GamepadKeys.Button.A
        );
//
//        controller.getGamepadButton(GamepadKeys.Button.A);
//        shoot
//                .whenPressed(new LogCatCommand("IT WAS PRESSED"))
//                .whenPressed(new TelemetryCommand(telemetry, "A was pressed"))
//                .whenPressed(new ShootCommandGroup(spindexer, transferArm, transferWheel, 1, telemetry));

        //controller.getGamepadButton(GamepadKeys.Button.A);
        shoot.whenPressed(
                new LogKittenCommand(Log.INFO, "ShootTeleOp", "IT WAS PRESSED")
                        .alongWith(
                        new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, 0, telemetry, aprilTag)));
//    (new LogCatCommand("IT WAS PRESSED"));

//        controller.getGamepadButton(GamepadKeys.Button.A).whenPressed(
//                new ShootCommandGroup(spindexer, transferArm, transferWheel, 1, telemetry)
//        );

        while (opModeIsActive()){
//            Log.println(Log.INFO, "it was pressed bs", "it was pressed bs");
            telemetry.addData("gamepad A pressed",controller.getGamepadButton(GamepadKeys.Button.A).get());
            update();
        }
    }
}
