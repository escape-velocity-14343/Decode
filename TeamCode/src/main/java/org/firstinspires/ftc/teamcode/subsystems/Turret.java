package org.firstinspires.ftc.teamcode.subsystems;

import android.util.Size;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class Turret extends SubsystemBase{
    private int max_pos = Constants.turret_max_pos;
    private int min_pos = Constants.turret_min_pos;
    private DcMotor turretMotor = null;

    public void init (HardwareMap hwMap){
        turretMotor = hwMap.get(DcMotor.class, "turretMotor");
        turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void aiming(int rotation){
        if (rotation != 0) {
            int cur = turretMotor.getCurrentPosition();
            if (cur + rotation <= max_pos && cur + rotation >= min_pos) {
                turretMotor.setTargetPosition(cur + rotation);
            }
            else {
                turretMotor.setTargetPosition(cur - (360 - rotation));
            }
            turretMotor.setPower(1);
            if (!turretMotor.isBusy()) {
                turretMotor.setPower(0);
            }
        }
    }
}

