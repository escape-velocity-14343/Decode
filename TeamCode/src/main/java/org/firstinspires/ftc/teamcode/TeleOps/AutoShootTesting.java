//package org.firstinspires.ftc.teamcode.TeleOps;
//
//import com.arcrobotics.ftclib.gamepad.GamepadEx;
//import com.arcrobotics.ftclib.gamepad.GamepadKeys;
//import com.bylazar.configurables.annotations.Configurable;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
//import org.firstinspires.ftc.teamcode.command.ShootCommandGroup;
//import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
//import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
//import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
//
//
//@TeleOp (name = "autoshoottest", group = "LinearOpMode")
//@Configurable
//public class AutoShootTesting extends Robot {
//    public static int apriltagnum = 0;
//    @Override
//    public void runOpMode() throws InterruptedException{
//        GamepadEx controller = new GamepadEx(gamepad1);
//        initialize();
//        controller.getGamepadButton(GamepadKeys.Button.X).whenPressed(new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm));
//        waitForStart();
//        while (opModeIsActive()) {
//            if (gamepad1.a) {
//                StaticValues.setMotif((int) apriltagnum, 2);
//            }
//            if (gamepad1.y) {
//                for (int i = 0; i < 3; i++) {
//                    if (StaticValues.getArtifacts(i) == 0) {
//                        StaticValues.setArtifacts(i, 1);
//                    }
//                }
//            }
//            if (gamepad1.b){
//                for (int i = 0; i < 3; i++) {
//                    if (StaticValues.getArtifacts(i) == 0) {
//                        StaticValues.setArtifacts(i, 2);
//                    }
//                }
//            }
//        }
//    }
//}
