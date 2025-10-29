package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.LogKittenCommand;
import org.firstinspires.ftc.teamcode.lib.ArtifactSensor;
import org.firstinspires.ftc.teamcode.lib.RevColorSensorDetector;
import org.firstinspires.ftc.teamcode.lib.SquIDController;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;

public class SpindexerSubsystem extends SubsystemBase {
    DcMotor spindexer;
    sensOrangeEncoder spindexerEncoder;
    ArtifactSensor artifactSensor;
    SquIDController squIDController = new SquIDController();
    double targetPosition = 0;
    byte[] ballsInSpindexer = new byte[]{0, 0, 0}; //0 means no ball, 1 means purple, 2 means CODE GREEN WILLIAM
    Telemetry telemtry;
    int add = 0;
    String[] artifacts = {"purple", "purple", "green"};

    public SpindexerSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        CommandScheduler.getInstance().registerSubsystem();
        spindexer = hwMap.get(DcMotor.class, "spindexerMotor");
        spindexerEncoder = new sensOrangeEncoder("spindexerEncoder", hwMap);
        spindexerEncoder.setPositionOffset(ConstantsSpindexer.offset);
        artifactSensor = new RevColorSensorDetector(hwMap); //@WILLIAM IS THIS PEAK RUNTIME POLYMORPHISM

        this.telemtry = telemetry;
    }



    public void intake(int ballNum){
        setTargetPosition(120*(ballNum) + 180);
    }

    public void outake(int ballNum) {
        setTargetPosition((120*(ballNum)));
    }




    public int intakeAuto(){
        for (int i = 0; i < 3; i++){
            if (artifacts[i].equals("empty")){
                setTargetPosition(120*(i) + 180);
                return i;
            }
        }
        return -1;
    }

    public int outakeAuto(String color){
        for (int i = 0; i < 3; i++){
            if (artifacts[i].equals(color)){
                telemtry.addData("OUTAKE AUTO POS:", 120*(i));
                telemtry.addData("OUTAKE AUTO CURRENT:", spindexerEncoder.getDegrees());
                setTargetPosition((120*(i)));;
                artifacts[i] = "empty";
                return i;
            }
        }
        return -1;
    }


    public void addColor(String color){
        for (int i = 0; i < 3; i++){
            if (artifacts[i].equals("empty")){
                artifacts[i] = color;
            }
        }
    }


    public void setTargetPosition(double targetPosition) {
        this.targetPosition = targetPosition;
    }


    public void setPower(double power){
        spindexer.setPower(Range.clip(power, -0.4, 0.4));
    }

    public double getDegrees() {
        return spindexerEncoder.getDegrees();
//        return 0;
    }
    public boolean isClose(double a, double b, double thres) {
        //return Util.inRange(targetPosition, getDegrees(), 10);
        return Math.abs(a - b) < thres;
    }

    public boolean isClose() {
        return isClose(targetPosition, getDegrees(), ConstantsSpindexer.tolerance);
    }


    @Override
    public void periodic() {
        for (String s : artifacts){
            telemtry.addData("SPINDEXER BALLS", s);
        }
//        telemtry.addData("SPINDEXER BALLS", artifacts.toString());
        squIDController.setPID(ConstantsSpindexer.spindexerP);
        telemtry.addData("spidnexer encoder pos", spindexerEncoder.getDegrees());
        telemtry.addData("targetposis", targetPosition);
        telemtry.addData("power", squIDController.calculateAngleWrapping(targetPosition, spindexerEncoder.getDegrees()));
        telemtry.addData("the statement it is close is:", isClose(targetPosition, spindexerEncoder.getDegrees(), 5));
        if (!isClose(targetPosition, spindexerEncoder.getDegrees(), 10)) {
            telemtry.addData("not clolse", !isClose(targetPosition, spindexerEncoder.getDegrees(), 5));
            setPower(squIDController.calculateAngleWrapping(targetPosition, spindexerEncoder.getDegrees()));
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
