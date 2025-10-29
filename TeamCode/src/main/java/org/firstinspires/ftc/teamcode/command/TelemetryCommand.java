package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryCommand extends CommandBase {
    Telemetry telemetry;
    public TelemetryCommand(Telemetry telemetry, String message){
        this.telemetry = telemetry;
        telemetry.addData(message, 0);
    }
    @Override
    public boolean isFinished(){
        return true;
    }
}
