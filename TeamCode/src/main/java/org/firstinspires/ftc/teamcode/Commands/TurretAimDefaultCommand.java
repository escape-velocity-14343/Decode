package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretSubsystem;

public class TurretAimDefaultCommand extends CommandBase {
    private AprilTagSubsystem aprilTag;
    private TurretSubsystem turret;

    public TurretAimDefaultCommand(HardwareMap hwMap, Telemetry telemetry){
        aprilTag = new AprilTagSubsystem(hwMap, telemetry);
        turret = new TurretSubsystem(hwMap);
    }

    @Override
    public void execute() {
        double[] rtn = aprilTag.periodic("blue");
        if (Math.abs(rtn[0]) > 0.5){
            turret.auto(rtn[0]);
        }
    }
}
