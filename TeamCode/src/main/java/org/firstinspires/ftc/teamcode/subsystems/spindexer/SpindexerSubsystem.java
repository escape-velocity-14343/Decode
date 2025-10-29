package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    String[] artifacts = {"empty", "empty", "empty"};

    public SpindexerSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        CommandScheduler.getInstance().registerSubsystem();
        spindexer = hwMap.get(DcMotor.class, "spindexerMotor");
        spindexerEncoder = new sensOrangeEncoder("spindexerEncoder", hwMap);
        spindexerEncoder.setPositionOffset(ConstantsSpindexer.offset);
        artifactSensor = new RevColorSensorDetector(hwMap); //@WILLIAM IS THIS PEAK RUNTIME POLYMORPHISM

        this.telemtry = telemetry;
    }



    public void intake(int ballNum){
        setTargetPosition(120*(ballNum + add - 1) + 180);
        add += 1;
        if (add == 3) {
            add = 0;
        }
    }

    public void outake(int ballNum) {
        setTargetPosition((120*(ballNum + add - 1)));
        add += 1;
        if (add == 3) {
            add = 0;
        }
    }




    public int intakeAuto(String[] artifacts){
        for (int i = 0; i < 3; i++){
            if (artifacts[i] == "empty"){
                intake(i);
                return i;
            }
        }
        return -1;
    }

    public int outakeAuto(String[] artifacts, String color){
        for (int i = 0; i < 3; i++){
            if (artifacts[i].equals(color)){
                outake(i);
                return i;
            }
        }
        return -1;
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
<<<<<<< HEAD
=======
    public boolean isClose() {
        return isClose(targetPosition, getDegrees(), ConstantsSpindexer.tolerance);
    }

>>>>>>> refs/remotes/origin/master


    @Override
    public void periodic() {
        squIDController.setPID(ConstantsSpindexer.spindexerP);
        telemtry.addData("spidnexer encoder pos", spindexerEncoder.getDegrees());
        telemtry.addData("targetposis", targetPosition);
        telemtry.addData("power", squIDController.calculateAngleWrapping(targetPosition, spindexerEncoder.getDegrees()));
        telemtry.addData("the statement it is close is:", isClose(targetPosition, spindexerEncoder.getDegrees(), 5));
        if (!isClose(targetPosition, spindexerEncoder.getDegrees(), 5)) {
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
