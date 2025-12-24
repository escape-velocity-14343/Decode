package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PinpointSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;
import org.firstinspires.ftc.teamcode.subsystems.turret.ConstantsTurret;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretSubsystem;

public class AprilTagAimDefaultCommand extends CommandBase {
    private AprilTagSubsystem aprilTag;
    private TurretSubsystem turret;
    private PinpointSubsystem pinpointSubsystem;
    boolean usePinpoint = false;
    double targetRotation = 0;

    public AprilTagAimDefaultCommand(AprilTagSubsystem aprilTag, TurretSubsystem turret, PinpointSubsystem pinpointSubsystem) {
        this.aprilTag = aprilTag;
        this.turret = turret;
        this.pinpointSubsystem = pinpointSubsystem;
        this.addRequirements(turret);
    }

    @Override
    public void execute() {
        pinpointSubsystem.setTranslation(aprilTag.getLocalization(turret.getTurretPositionRadians(), pinpointSubsystem.getHeading()));
        if (usePinpoint) {
            targetRotation = pinpointSubsystem.getHeading().getDegrees() - pinpointSubsystem.getRotationToPoint(new Pose2d(StaticValues.goalPos.getX(), StaticValues.getM() * StaticValues.goalPos.getY(), StaticValues.goalPos.getRotation())).getDegrees();
            targetRotation = AngleUnit.normalizeDegrees(targetRotation);
            if (Util.inRange(targetRotation, 0, 75))
                turret.setTargetPosition(targetRotation);
        }
        else if (aprilTag.getBearing() != 0)
            turret.setPowerManual(ConstantsTurret.apriltagkP * (aprilTag.getBearing() + ConstantsTurret.aprilTagOffset));

        else
            turret.setPowerManual(0);
    }
    public void setUsePinpoint(boolean usePinpoint) {
        this.usePinpoint = usePinpoint;
    }

}
