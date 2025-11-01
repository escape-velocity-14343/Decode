package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import static org.firstinspires.ftc.teamcode.subsystems.spindexer.ConstantsSpindexer.thres;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.lib.ArtifactSensor;
import org.firstinspires.ftc.teamcode.lib.RevColorSensorDetector;
import org.firstinspires.ftc.teamcode.lib.ThaiVPController;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class SpindexerSubsystem extends SubsystemBase {
    DcMotor spindexer;
    sensOrangeEncoder spindexerEncoder;
    ArtifactSensor artifactSensor;
    ThaiVPController rotationController = new ThaiVPController();
    double targetPosition = 300;
    Telemetry telemtry;
    int add = 0;
    int[] artifacts = {2, 1, 1};

    public SpindexerSubsystem(HardwareMap hwMap) {
        CommandScheduler.getInstance().registerSubsystem();
        spindexer = hwMap.get(DcMotor.class, "spindexerMotor");
        spindexerEncoder = new sensOrangeEncoder("spindexerEncoder", hwMap);
        spindexerEncoder.setPositionOffset(ConstantsSpindexer.offset);
        artifactSensor = new RevColorSensorDetector(hwMap); //@WILLIAM IS THIS PEAK RUNTIME POLYMORPHISM
        rotationController.setExponent(1);

        this.telemtry = Robot.getTelemetry();
    }



    public void intake(int ballNum){
        setTargetPosition(120*(ballNum) + 180);
    }

    public void outake(int ballNum) {
        setTargetPosition((120*(ballNum)));
    }




    public int intakeAuto(){
        for (int i = 0; i < 3; i++){
            if (artifacts[i] == 0){
                setTargetPosition(120*(i) + 180);
                return i;
            }
        }
        return -1;
    }

    public int outakeAuto(int color) {
        if (artifacts[0] == color) {
            Log.i("autoshoot", "shooting ball 0");
            setTargetPosition(0);
            artifacts[0] = 0;
            return 0;
        }
        if (artifacts[1] == color) {
            Log.i("autoshoot", "shooting ball 1");
            setTargetPosition(120);
            artifacts[1] = 0;
            return 1;
        }
        if (artifacts[2] == color) {
            Log.i("autoshoot", "shooting ball 2");
            setTargetPosition(240);
            artifacts[2] = 0;
            return 2;
        }

        /*for (int i = 0; i < 3; i++){
            if (artifacts[i] == color) {
//                telemtry.addData("OUTAKE AUTO POS:", 120*i);
//                telemtry.addData("OUTAKE AUTO CURRENT:", spindexerE;ncoder.getDegrees());
                telemtry.addData("the ball we are shooting is", i);
                Log.i("shooter", "the ball we are shooting is: " + i);
                setTargetPosition(120*i);
                telemtry.addData("EARTH TO PULLAYUP", spindexerEncoder.getDegrees());
                artifacts[i] = 0;
                return i;
            }
        }*/
        return -1;
    }
    public boolean hasSpace() {
        for (int i : artifacts) {
            if (i==0) {
                return true;
            }
        }
        return false;
    }


    public void addColor(int color){
        for (int i = 0; i < 3; i++){
            if (artifacts[i] == 0){
                artifacts[i] = color;
                break;
            }
        }
    }


    public void setTargetPosition(double targetPosition) {
        this.targetPosition = targetPosition;
    }


    public void setPower(double power){
        spindexer.setPower(Range.clip(power, -0.5, 0.5));
    }

    public double getDegrees() {
        return spindexerEncoder.getDegrees();
//        return 0;
    }
    public boolean isClose(double a, double b, double thres) {
        //return Util.inRange(targetPosition, getDegrees(), 10);
        return Math.abs(AngleUnit.normalizeDegrees(a - b)) < thres;
    }

    public boolean isClose() {
        return isClose(targetPosition, getDegrees(), thres);
    }


    @Override
    public void periodic() {
        Log.i("SPINDEXER", "current inside" + artifacts);
        for (int s : artifacts){
            telemtry.addData("SPINDEXER BALLS", s);
        }
//        telemtry.addData("SPINDEXER BALLS", artifacts.toString());
        rotationController.setPID(ConstantsSpindexer.kP);
        telemtry.addData("spidnexer encoder pos", spindexerEncoder.getDegrees());
        telemtry.addData("targetposis", targetPosition);
        telemtry.addData("power", rotationController.calculateAngleWrapping(targetPosition, spindexerEncoder.getDegrees())*100);
//        telemtry.addData("the statement it is close is:", isClose(targetPosition, spindexerEncoder.getDegrees(), 5));
//        telemtry.addData("not clolse", !isClose(targetPosition, spindexerEncoder.getDegrees(), 5));
        setPower(rotationController.calculateAngleWrapping(targetPosition, spindexerEncoder.getDegrees()));
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
