package org.firstinspires.ftc.teamcode.subsystems.turret;

import static org.firstinspires.ftc.onbotjava.OnBotJavaManager.initialize;

import com.arcrobotics.ftclib.geometry.Pose2d;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.TurretAimDefaultCommand;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

@TeleOp(group = "Test")
public class shootMoveTest extends Robot{
    @Override
    public void runOpMode () throws InterruptedException{
        initialize();
        turret.setDefaultCommand(new TurretAimDefaultCommand(aprilTag, turret, pinpointSubsystem));

        waitForStart();
        while (opModeIsActive()){
            StaticValues.goalPos = turret.movingShoot(pinpointSubsystem);
            update();
        }
    }
}