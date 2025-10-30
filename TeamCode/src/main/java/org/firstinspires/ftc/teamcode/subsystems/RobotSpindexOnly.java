package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public abstract class RobotSpindexOnly extends LinearOpMode {
    public SpindexerSubsystem spindexer;
    public void initialize(){
        spindexer = new SpindexerSubsystem(hardwareMap);
    }

    public void update(){
        CommandScheduler.getInstance().run();
        telemetry.update();
    }
}
