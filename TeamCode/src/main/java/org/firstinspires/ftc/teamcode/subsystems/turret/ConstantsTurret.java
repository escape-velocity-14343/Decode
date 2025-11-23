package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class ConstantsTurret {
    public static double min = -90;
    public static double max = 90;
    public static double offset = 115;

    public static double targetposition = 0;
    public static double kp = 0.1267;
    public static double apriltagkP = -0.02;
    public static double shootingPosClose = -93;
    public static double shootingPosFar = -5;
    public static double obeliskPosClose = -45;
    public static double obeliskPosFar = 0;

    public static double aprilTagOffset = 0;
}
