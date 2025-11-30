package org.firstinspires.ftc.teamcode.subsystems.spindexer;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.robot.Robot;

@TeleOp (name = "Spindexer PID Test", group = "Test")
public class SpindexerPIDTest extends Robot {
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            update();
            spindexer.setTargetPosition(ConstantsSpindexer.testTarget);
            spindexer.periodic();
        }
    }
}
