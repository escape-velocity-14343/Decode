package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

public class TurretSubsystem extends SubsystemBase {
    private DcMotor turretMotor;
    private sensOrangeEncoder turretEncoder;
    private ConstantsTurret constants;
    private SquIDController powerManage;

    public TurretSubsystem(HardwareMap hwMap){
        turretMotor = hwMap.get(DcMotor.class, "turretMotor");
        turretEncoder = new sensOrangeEncoder("turretEncoder", hwMap);
    }

    public void manual(Gamepad gamepad) {
        if (gamepad.right_trigger > 0){
            if (turretEncoder.getDegrees() + gamepad.right_trigger < constants.max){
                turretMotor.setPower(powerManage.calculate(turretEncoder.getDegrees() + gamepad.right_trigger, turretEncoder.getDegrees()));
            } else {
                turretMotor.setPower(powerManage.calculate(constants.max, turretEncoder.getDegrees()));
            }
        }

        if (gamepad.left_trigger > 0){
            if (turretEncoder.getDegrees() + gamepad.left_trigger > constants.min){
                turretMotor.setPower(powerManage.calculate(turretEncoder.getDegrees() - gamepad.left_trigger, turretEncoder.getDegrees()));
            } else {
                turretMotor.setPower(powerManage.calculate(constants.min, turretEncoder.getDegrees()));
            }
        }
    }

    public void auto(double rotate){
        if (turretEncoder.getDegrees() + rotate > 0){
            if (turretEncoder.getDegrees() + rotate < constants.max && turretEncoder.getDegrees() + rotate > constants.min){
                turretMotor.setPower(powerManage.calculate(turretEncoder.getDegrees() + rotate, turretEncoder.getDegrees()));
            } else if (turretEncoder.getDegrees() + rotate > constants.min){
                turretMotor.setPower(powerManage.calculate(constants.max, turretEncoder.getDegrees()));
            } else {
                turretMotor.setPower(powerManage.calculate(constants.min, turretEncoder.getDegrees()));
            }
        }
    }
}
