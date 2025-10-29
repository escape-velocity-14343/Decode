package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultDriveCommand extends CommandBase {
    MecanumDriveSubsystem drive;
    DoubleSupplier x, y, rx, heading;

    public DefaultDriveCommand(MecanumDriveSubsystem driveSubsystem, DoubleSupplier inputX, DoubleSupplier inputY, DoubleSupplier inputRx) {
        this.drive = driveSubsystem;
        this.x = inputX;
        this.y = inputY;
        this.rx = inputRx;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.drive(-x.getAsDouble() + getXModPower(), y.getAsDouble() + getYModPower(), rx.getAsDouble() + getRModPower());
    }

    public double getXModPower() {
        return 0.0;
    }

    public double getYModPower() {
        return 0.0;
    }

    public double getRModPower() {
        return 0.0;
    }
}