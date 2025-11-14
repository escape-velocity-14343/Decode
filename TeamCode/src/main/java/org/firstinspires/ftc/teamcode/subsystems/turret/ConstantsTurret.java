package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class ConstantsTurret {
    public static double min = -90;
    public static double max = 90;
    public static double offset = 180;

    public static double targetposition = 0;
    public static double kp = 0.17;
    public static double apriltagkP = -0.0275;
    public static double shootingPos = -90;
    public static double obeliskPos = -25;

    public static double aprilTagOffset = -10;
}
