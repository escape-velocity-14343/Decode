package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

public class AprilTagAim extends CommandBase {
    private sensOrangeEncoder encoder;
    private AprilTagReader goal;
    private TurretMotor turretMotor;
    private  SquIDController powerManage;
    private Telemetry telemetry;
    public void init(HardwareMap hwMap, Telemetry telemetry) {
        encoder = new sensOrangeEncoder("turretEncoder", hwMap);
        goal = new AprilTagReader();
        turretMotor = new TurretMotor();
        powerManage = new SquIDController();
        powerManage.setPID(0.0005);
        goal.init(hwMap, telemetry);
        turretMotor.init(hwMap);
        this.telemetry = telemetry;
    }

    public double rotationAmount(double destination){
        if (destination > ConstantsTurret.turret_max_pos){
            return ConstantsTurret.turret_max_pos;
        }
        if (destination < ConstantsTurret.turret_min_pos){
            return (ConstantsTurret.turret_min_pos);
        }
        return destination;
    }

    public void execute() {
        telemetry.addData("hello", 5);
        double rotation = goal.periodic("Blue")[2];
        if (rotation <= 0.5 && rotation >= -0.5) {
            telemetry.addData("power", 0);
            turretMotor.setPowerTo(0);
        } else {
            telemetry.addData("power", powerManage.calculate(rotationAmount(encoder.getDegrees() + rotation), encoder.getDegrees()));
            turretMotor.setPowerTo(powerManage.calculate(rotationAmount(encoder.getDegrees() + rotation), encoder.getDegrees()));

        }
    }
}