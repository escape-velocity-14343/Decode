package org.firstinspires.ftc.teamcode.subsystems.IntakeCam;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class IntakeCamConstants {
    public static int exposureMicros = 1200;
    public static double minContourArea = 1500.0;
    public static double maxContourArea = 20000.0;
    public static double minCircularity = 0.6;
    public static double maxCircularity = 1.0;
}
