package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import com.arcrobotics.ftclib.command.CommandScheduler;
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
        CommandScheduler.getInstance().registerSubsystem();
        spindexer = hwMap.get(DcMotor.class, "spindexerMotor");
        spindexerEncoder = new sensOrangeEncoder("spindexerEncoder", hwMap);
        spindexerEncoder.setPositionOffset(ConstantsSpindexer.offset);
        artifactSensor = new RevColorSensorDetector(hwMap); //@WILLIAM IS THIS PEAK RUNTIME POLYMORPHISM

        this.telemtry = telemetry;
    }
    public void intake(int ballNum){
        //setTargetPosition(60*(ballNum - 1));
    }

    public void outake(int ballNum) {
        //setTargetPosition(Math.abs((120*(ballNum - 1) + 180) - 360));
    }

    public void setTargetPosition(double targetPosition) {
        //this.targetPosition = targetPosition;
    }


    public void setPower(double power){
        spindexer.setPower(power);
    }

    public double getDegrees() {
        //return spindexerEncoder.getDegrees();
        return 0;
    }
    public boolean isClose(double a, double b, double thres) {
        //return Util.inRange(targetPosition, getDegrees(), 10);
        return Math.abs(a - b) < thres;
    }
    public boolean isClose() {
        return isClose(targetPosition, getDegrees(), 5);
    }



    @Override
    public void periodic() {
        squIDController.setPID(ConstantsSpindexer.spindexerP);
        telemtry.addData("spidnexer encoder pos", spindexerEncoder.getDegrees());
        telemtry.addData("targetposis", targetPosition);
        telemtry.addData("power", squIDController.calculate(targetPosition, spindexerEncoder.getDegrees()));
        telemtry.addData("the statement it is close is:", isClose(targetPosition, spindexerEncoder.getDegrees(), 5));
        if (!isClose(targetPosition, spindexerEncoder.getDegrees(), 5)) {
            telemtry.addData("not clolse", !isClose(targetPosition, spindexerEncoder.getDegrees(), 5));
            setPower(squIDController.calculate(targetPosition, spindexerEncoder.getDegrees()));
        }
        //setPower(squIDController.calculate(0.0, spindexerEncoder.getDegrees()));
//        if (Util.inRange(spindexerEncoder.getDegrees() % 120, 0, 10)) {
//            if (artifactSensor.proximityDetected()) {
//                ballsInSpindexer[(int) (spindexerEncoder.getDegrees() / 120)] = (byte) (artifactSensor.greenDetected() ? 2 : 1);
//            }
//            else
//            {
//                ballsInSpindexer[(int) (spindexerEncoder.getDegrees() / 120)] = 0;
//            }
//        }
    }
}
