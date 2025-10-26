package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lib.ArtifactSensor;
import org.firstinspires.ftc.teamcode.lib.RevColorSensorDetector;
import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

public class SpindexerSubsystem extends SubsystemBase {
    DcMotor spindexer;
    sensOrangeEncoder spindexerEncoder;
    ArtifactSensor artifactSensor;
    SquIDController squIDController = new SquIDController();
    double targetPosition = 0;
    byte[] ballsInSpindexer = new byte[]{0, 0, 0}; //0 means no ball, 1 means purple, 2 means CODE GREEN WILLIAM
    Telemetry telemtry;

    public SpindexerSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        spindexer = hwMap.get(DcMotor.class, "spindexerMotor");
        spindexerEncoder = new sensOrangeEncoder("turretEncoder", hwMap);
        spindexerEncoder.setPositionOffset(ConstantsSpindexer.offset);
        artifactSensor = new RevColorSensorDetector(hwMap); //@WILLIAM IS THIS PEAK RUNTIME POLYMORPHISM
        squIDController.setPID(ConstantsSpindexer.spindexerP);
        this.telemtry = telemetry;
    }
    public void intake(int ballNum){
        spindexer.setTargetPosition(60*(ballNum - 1));
    }

    public void outake(int ballNum) {
        spindexer.setTargetPosition(Math.abs((120*(ballNum - 1) + 180) - 360));
    }

    public void setTargetPosition(double targetPosition) {
        this.targetPosition = targetPosition;
    }


    public void setPower(double power){
        spindexer.setPower(power);
    }

    public double getDegrees() {
        return spindexerEncoder.getDegrees();
    }
    public boolean isClose() {
        return Util.inRange(targetPosition, getDegrees(), 10);
    }


    @Override
    public void periodic() {
        telemtry.addData("spidnexer encoder pos", spindexerEncoder.getDegrees());
        setPower(squIDController.calculateAngleWrapping(targetPosition, spindexerEncoder.getDegrees()));
        if (Util.inRange(spindexerEncoder.getDegrees() % 120, 0, 10)) {
            if (artifactSensor.proximityDetected()) {
                ballsInSpindexer[(int) (spindexerEncoder.getDegrees() / 120)] = (byte) (artifactSensor.greenDetected() ? 2 : 1);
            }
            else
            {
                ballsInSpindexer[(int) (spindexerEncoder.getDegrees() / 120)] = 0;
            }
        }
    }
}
