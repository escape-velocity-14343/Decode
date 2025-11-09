package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import static org.firstinspires.ftc.teamcode.subsystems.spindexer.ConstantsSpindexer.thres;

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
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

public class SpindexerSubsystem extends SubsystemBase {
    DcMotor spindexer;
    sensOrangeEncoder spindexerEncoder;
    ArtifactSensor artifactSensor;
    ThaiVPController rotationController = new ThaiVPController();
    double targetPosition = 0;
    Telemetry telemtry;
    int add = 0;
    public static int[] artifacts = {2, 1, 1};

    public SpindexerSubsystem(HardwareMap hwMap) {
        CommandScheduler.getInstance().registerSubsystem();
        spindexer = hwMap.get(DcMotor.class, "spindexerMotor");
        spindexerEncoder = new sensOrangeEncoder("spindexerEncoder", hwMap);
        spindexerEncoder.setPositionOffset(ConstantsSpindexer.offset);
        artifactSensor = new RevColorSensorDetector(hwMap); //@WILLIAM IS THIS PEAK RUNTIME POLYMORPHISM
        rotationController.setExponent(ConstantsSpindexer.exponent);

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

    public int outakeAuto(int color){
        for (int i = 0; i < 3; i++){
            if (artifacts[i] == color){
//                telemtry.addData("OUTAKE AUTO POS:", 120*i);
//                telemtry.addData("OUTAKE AUTO CURRENT:", spindexerEncoder.getDegrees());
                setTargetPosition(120*i);
                artifacts[i] = 0;
                return i;
            }
        }
        return -1;
    }


    public void addColor(int color){
        for (int i = 0; i < 3; i++){
            if (artifacts[i] == 0){
                artifacts[i] = color;
                break;
            }
        }
    }
    public boolean hasSpace() {
        for (int i : artifacts) {
            if (i==0) {
                return true;
            }
        }
        return false;
    }


    public void setTargetPosition(double targetPosition) {
        this.targetPosition = targetPosition;
    }


    public void setPower(double power){
        spindexer.setPower(Range.clip(power, -0.75, 0.75));
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
        for (int s : artifacts){
            telemtry.addData("SPINDEXER BALLS", "balls" + s);
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
