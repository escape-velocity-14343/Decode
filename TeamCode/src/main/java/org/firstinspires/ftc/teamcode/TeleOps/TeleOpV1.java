//package org.firstinspires.ftc.teamcode.TeleOps;
//
//import com.arcrobotics.ftclib.gamepad.GamepadEx;
//import com.arcrobotics.ftclib.gamepad.GamepadKeys;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.command.IntakeOffCommand;
//import org.firstinspires.ftc.teamcode.command.IntakeOnCommand;
//import org.firstinspires.ftc.teamcode.command.ShootCommandGroup;
//import org.firstinspires.ftc.teamcode.command.ShooterOffCommand;
//import org.firstinspires.ftc.teamcode.command.ShooterOnCommand;
//import org.firstinspires.ftc.teamcode.command.SpindexInCommand;
//import org.firstinspires.ftc.teamcode.command.DefaultDriveCommand;
//import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.Robot;
//import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.hood.HoodSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.transferArm.TransferArmSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.transferWheel.TransferWheelSubsystem;
//
//@TeleOp (name = "TeleOp V1", group = "LinearOpMode")
//@Disabled
//public class TeleOpV1 extends Robot {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        GamepadEx controller = new GamepadEx(gamepad1);
//        initialize();
//        drive.setDefaultCommand(new DefaultDriveCommand(drive,
//                controller::getLeftY,
//                controller::getLeftX,
//                controller::getRightX));
//
//        controller.getGamepadButton(GamepadKeys.Button.A).whenPressed(new ShootCommandGroup(shooter, spindexer, transferArm, transferWheel, "blue", telemetry));
//        controller.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new IntakeOnCommand(intake).alongWith(new SpindexInCommand(spindexer)))
//                .whenReleased(new IntakeOffCommand(intake));
//        controller.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(new ShooterOnCommand(shooter)).whenReleased(new ShooterOffCommand(shooter));;
//        waitForStart();
//        while (opModeIsActive()){
//            update();
//        }
//
//    }
//}
