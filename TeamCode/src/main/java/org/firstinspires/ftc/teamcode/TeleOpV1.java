package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveFieldCentric;
import org.firstinspires.ftc.teamcode.subsystems.hood.hoodServo;
import org.firstinspires.ftc.teamcode.subsystems.intake.intakeServo;
import org.firstinspires.ftc.teamcode.subsystems.shooter.shooterMotor;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.transferArmServo;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.transferWheelServo;

@TeleOp (name = "TeleOp V1", group = "LinearOpMode")
public class TeleOpV1 extends LinearOpMode {
    private MecanumDriveFieldCentric drive;
    private shooterMotor shooter;
    private hoodServo hood;
    private transferArmServo transferArm;
    private transferWheelServo transferWheel;
    private intakeServo intake;

    public void runOpMode(){
        drive = new MecanumDriveFieldCentric();
        shooter = new shooterMotor();
        hood = new hoodServo();
        transferArm = new transferArmServo();
        transferWheel = new transferWheelServo();
        intake = new intakeServo();

        drive.init(hardwareMap);
        shooter.init(hardwareMap);
        hood.init(hardwareMap);
        transferArm.init(hardwareMap);
        transferWheel.init(hardwareMap);
        intake.init(hardwareMap);

        waitForStart();
        intake.on();
        transferWheel.on();
        shooter.on();
        while(opModeIsActive()){

        }
    }

}
