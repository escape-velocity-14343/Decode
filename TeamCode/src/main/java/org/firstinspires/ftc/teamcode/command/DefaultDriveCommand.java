package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.lib.Util;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
@Configurable
public class DefaultDriveCommand extends CommandBase {
    MecanumDriveSubsystem drive;
    DoubleSupplier x, y, rx, heading;
    BooleanSupplier atagAlign;
    public static double atagkP = 0;


    public DefaultDriveCommand(MecanumDriveSubsystem driveSubsystem, DoubleSupplier inputX, DoubleSupplier inputY, DoubleSupplier inputRx, BooleanSupplier atagAlign) {
        this.drive = driveSubsystem;
        this.x = inputX;
        this.y = inputY;
        this.rx = inputRx;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.drive(-x.getAsDouble() + getXModPower(), y.getAsDouble() + getYModPower(),
                atagAlign.getAsBoolean() ? Robot.aprilTagX*atagkP : rx.getAsDouble() + getRModPower() );
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