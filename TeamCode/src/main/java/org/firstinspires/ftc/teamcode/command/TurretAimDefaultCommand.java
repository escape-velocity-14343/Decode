package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.turret.ConstantsTurret;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretSubsystem;

public class TurretAimDefaultCommand extends CommandBase {
    private AprilTagSubsystem aprilTag;
    private TurretSubsystem turret;

    public TurretAimDefaultCommand(AprilTagSubsystem aprilTag, TurretSubsystem turret) {
        this.aprilTag = aprilTag;
        this.turret = turret;
        addRequirements(turret);
    }

    @Override
    public void execute() {
        turret.setPowerManual(ConstantsTurret.apriltagkP*aprilTag.getBearing());
    }
}
