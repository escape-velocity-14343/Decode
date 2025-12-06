package org.firstinspires.ftc.teamcode.Auton;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.command.IntakeAutoCommandGroup;
import org.firstinspires.ftc.teamcode.command.IntakeOffCommand;
import org.firstinspires.ftc.teamcode.command.IntakeOnCommand;
import org.firstinspires.ftc.teamcode.command.MotifShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.command.TimeoutCommand;
import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;
import org.firstinspires.ftc.teamcode.subsystems.robot.StaticValues;

import java.sql.Time;

@Autonomous
@Disabled
public class TimeOutCommandTester extends Robot {
    public void runOpMode() {
        initialize();
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(new TimeoutCommand(new IntakeAutoCommandGroup(spindexer, intake, artifactSensor), 3000),
                new IntakeOffCommand(intake)
                )
        );
        waitForStart();
        while (opModeIsActive()) {
            update();
    }


    }
}
