package org.firstinspires.ftc.teamcode.command;

import android.util.Log;

import com.arcrobotics.ftclib.command.CommandBase;

public class LogKittenCommand extends CommandBase {
    int priority;
    String tag;
    String message;
    public LogKittenCommand(int priority, String tag, String message) {
        this.priority = priority;
        this.tag = tag;
        this.message = message;

    }
    @Override
    public void initialize() {
        Log.println(priority, tag, message);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
