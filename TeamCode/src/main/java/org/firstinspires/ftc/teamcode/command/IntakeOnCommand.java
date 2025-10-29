package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;

public class IntakeOnCommand extends CommandBase {
    IntakeSubsystem intakeSubsystem;
    public IntakeOnCommand(IntakeSubsystem intake){
        this.intakeSubsystem = intake;
        addRequirements(intake);
    }
    @Override
    public void initialize() {
        intakeSubsystem.takein();
    }
}
