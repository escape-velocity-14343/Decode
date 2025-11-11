package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;

public class RaceAimShootAuto extends CommandBase {
    private Command aim;
    private Command shoot;
    public RaceAimShootAuto(Command aim, Command shoot) {
        this.aim = aim;
        this.shoot = shoot;
    }

    @Override
    public void initialize() {
        aim.initialize();
        shoot.initialize();
    }

    @Override
    public boolean isFinished() {
        return aim.isFinished() || shoot.isFinished();
    }
}
