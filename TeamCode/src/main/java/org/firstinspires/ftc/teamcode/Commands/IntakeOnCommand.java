package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeSubsystem;

public class IntakeOnCommand extends CommandBase {
    public IntakeOnCommand(IntakeSubsystem intake){
        addRequirements(intake);
        intake.takein();
    }
}
