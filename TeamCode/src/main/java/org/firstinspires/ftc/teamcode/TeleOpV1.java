package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveFieldCentric;
import org.firstinspires.ftc.teamcode.subsystems.hood.HoodSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.transferArm.transferArmServo;
import org.firstinspires.ftc.teamcode.subsystems.transferWheel.transferWheelServo;

@TeleOp (name = "TeleOp V1", group = "LinearOpMode")
public class TeleOpV1 extends LinearOpMode {
    private MecanumDriveFieldCentric drive;
    private ShooterSubsystem shooter;
    private HoodSubsystem hood;
    private transferArmServo transferArm;
    private transferWheelServo transferWheel;
    private IntakeSubsystem intake;

    public void runOpMode(){
        drive = new MecanumDriveFieldCentric();
        shooter = new ShooterSubsystem(hardwareMap);
        hood = new HoodSubsystem(hardwareMap);
        transferArm = new transferArmServo();
        transferWheel = new transferWheelServo();
        intake = new IntakeSubsystem();

        drive.init(hardwareMap);
        transferArm.init(hardwareMap);
        transferWheel.init(hardwareMap);
        intake.init(hardwareMap);

        waitForStart();
        intake.takein();
        transferWheel.on();
        shooter.on();
        while(opModeIsActive()){

        }
    }

}
