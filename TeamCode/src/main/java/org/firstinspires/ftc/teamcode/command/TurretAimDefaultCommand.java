package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PinpointSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.turret.ConstantsTurret;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretSubsystem;

public class TurretAimDefaultCommand extends CommandBase {
    private AprilTagSubsystem aprilTag;
    private TurretSubsystem turret;
    private PinpointSubsystem pinpointSubsystem;
    boolean usePinpoint = true;
    double targetRotation = 0;

    public TurretAimDefaultCommand(AprilTagSubsystem aprilTag, TurretSubsystem turret, PinpointSubsystem pinpointSubsystem) {
        this.aprilTag = aprilTag;
        this.turret = turret;
        this.pinpointSubsystem = pinpointSubsystem;
        addRequirements(turret);
    }

    @Override
    public void execute() {
        if (aprilTag.getBearing() != 0)
            turret.setPowerManual(ConstantsTurret.apriltagkP * (aprilTag.getBearing() + ConstantsTurret.aprilTagOffset));
        //else if (usePinpoint) {
        //    targetRotation = pinpointSubsystem.getHeading().getDegrees() - pinpointSubsystem.getRotationToPoint(new Pose2d(67, 51, new Rotation2d(0))).getDegrees();
        //    if (Util.inRange(targetRotation, 0, 80))
        //        turret.setTargetPosition(targetRotation);
        //}
        else
            turret.setPowerManual(0);
    }

    public void setUsePinpoint(boolean usePinpoint) {
        this.usePinpoint = usePinpoint;
    }

}
