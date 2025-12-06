package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class ConstantsTurret {
    public static double min = -90;
    public static double max = 90;
    public static double offset = 112;

    public static double targetposition = 0;
    public static double kp = 0.0325;
    public static double apriltagkP = -0.025;
    public static double obeliskPosClose = -45;
    public static double shootingPosClose = -93;
    public static double shootingPosFar = -20;
    public static double obeliskPosFar = 0;

    public static double aprilTagOffset = 3;
    public static double exponent = 0.9;
}
