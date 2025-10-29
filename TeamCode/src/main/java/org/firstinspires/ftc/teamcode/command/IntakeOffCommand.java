package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;

public class IntakeOffCommand extends CommandBase {
    IntakeSubsystem intake;
    public IntakeOffCommand(IntakeSubsystem intake){
        addRequirements(intake);
        this.intake = intake;
    }
    @Override
    public void initialize() {
        intake.setPower(0);
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
