package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.spindexer.SpindexerSubsystem;

public class IntakeCommand {
    private IntakeSubsystem intake;
    private SpindexerSubsystem spindexer;

    public IntakeCommand(HardwareMap hwMap, Gamepad gamepad, Telemetry telemetry){
        intake = new IntakeSubsystem(hwMap);
        spindexer = new SpindexerSubsystem(hwMap, telemetry);
    }
}
