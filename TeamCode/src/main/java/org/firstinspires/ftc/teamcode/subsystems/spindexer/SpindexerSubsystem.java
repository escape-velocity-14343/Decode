package org.firstinspires.ftc.teamcode.subsystems.spindexer;
import static org.firstinspires.ftc.teamcode.subsystems.spindexer.ConstantsSpindexer.thres;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.lib.ArtifactSensor;
import org.firstinspires.ftc.teamcode.lib.RevColorSensorDetector;
import org.firstinspires.ftc.teamcode.lib.ThaiVPController;
import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.lib.sensOrangeEncoder;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

public class SpindexerSubsystem extends SubsystemBase {
    DcMotor spindexer;
    sensOrangeEncoder spindexerEncoder;
    ArtifactSensor artifactSensor;
    ThaiVPController rotationController = new ThaiVPController();
    double targetPosition = 0;
    byte[] ballsInSpindexer = new byte[]{0, 0, 0}; //0 means no ball, 1 means purple, 2 means CODE GREEN WILLIAM
    Telemetry telemtry;
    int add = 0;
    boolean manualControl = false;
    double lastPower = 0;

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
        manualControl = false;
        setTargetPosition(120*(ballNum) + 180);
    }

    public void outake(int ballNum) {
        manualControl = false;
        setTargetPosition((120*(ballNum)));
    }




    public int intakeAuto() {
        manualControl = false;
        for (int i = 0; i < 3; i++){
            if (StaticValues.getArtifacts(i) == 0){
                setTargetPosition(120 * (i) + 180);
                return i;
            }
        }
        return -1;
    }

    public int outakeAuto(int color){
        manualControl = false;
        for (int i = 0; i < 3; i++){
            if (StaticValues.getArtifacts(i) == color){
                telemtry.addData("OUTAKE AUTO POS:", i);
//                telemtry.addData("OUTAKE AUTO CURRENT:", spindexerEncoder.getDegrees());
                setTargetPosition(120*i);
                StaticValues.setArtifacts(i, 0);
                return i;
            }
        }
        return -1;
    }


    public void addColor(int color) {
        manualControl = false;
        for (int i = 0; i < 3; i++) {
            if (StaticValues.getArtifacts(i) == 0){
                StaticValues.setArtifacts(i, color);
                break;
            }
        }
    }

    public boolean hasSpace() {
        for (int i = 0; i < 3; i++) {
            if (StaticValues.getArtifacts(i) == 0) {
                return true;
            }
        }
        return false;
    }
    public int getRemainingSpace() {
        int space = 0;
        for (int i = 0; i < 3; i++) {
            if (StaticValues.getArtifacts(i) == 0) {
                space++;
            }
        }
        return space;
    }

    public void setTargetPosition(double targetPosition) {
        manualControl = false;
        this.targetPosition = targetPosition;
        if (targetPosition == 120) //correct for encoder nonlinearity (who made this encoder???)
            this.targetPosition = 115;
    }


    public void setPower(double power){
        //spindexer.setPower(Range.clip(power, -0.5, 0.5));
        if (Util.inRange(power, lastPower, 0.01))
            return;
        spindexer.setPower(power*StaticValues.getVoltageScalar());
        lastPower = power;
    }
    public void setPowerManual(double power) {
        manualControl = true;
        setPower(power);
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
    public void setManualControl(boolean set) {
        manualControl = set;
    }


    @Override
    public void periodic() {
        for (int i = 0; i < 3; i++){
            telemtry.addData("SPINDEXER BALLS", "balls" + StaticValues.getArtifacts(i));
        }
        rotationController.setPID(ConstantsSpindexer.kP);
        telemtry.addData("spidnexer encoder pos", spindexerEncoder.getDegrees());
        //telemtry.addData("targetposis", targetPosition);
        //telemtry.addData("power", rotationController.calculateAngleWrapping(targetPosition, spindexerEncoder.getDegrees())*100);
        if (!manualControl)
            setPower(rotationController.calculateAngleWrapping(targetPosition, spindexerEncoder.getDegrees()));

    }
}
