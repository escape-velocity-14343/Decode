package org.firstinspires.ftc.teamcode.Auton;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.command.AprilTagMotifDetectionCommand;
import org.firstinspires.ftc.teamcode.command.DefaultGoToPointCommand;
import org.firstinspires.ftc.teamcode.command.GoToPointWithDefaultCommand;
import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.LogKittenCommand;
import org.firstinspires.ftc.teamcode.command.MotifShoot21Command;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.RaceAimShootAuto;
import org.firstinspires.ftc.teamcode.command.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.TurretAimDefaultCommand;
import org.firstinspires.ftc.teamcode.subsystems.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@Autonomous(name = "BabyAuton", group = "Auton")
public class BabyAuton extends Robot {
    DefaultGoToPointCommand toPoint;
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        StaticValues.resetMotif();
        StaticValues.resetArtifacts();
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new InstantCommand(() -> turret.setTargetPosition(-10)),
                        new AprilTagMotifDetectionCommand(aprilTag),
                        new InstantCommand(() -> turret.setTargetPosition(-90)),
                        new MotifShootCommandGroup(spindexer, shooter, transferWheel, transferArm, aprilTag, toPoint)
                )
        );
        waitForStart();
        while (opModeIsActive()){
            update();
        }
    }
}
