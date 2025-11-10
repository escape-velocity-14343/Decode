package org.firstinspires.ftc.teamcode.subsystems;

public class StaticValues {
    private static int[] motif = {1, 1, 2};
    public static void setMotif(int place, int value){
        motif[place] = value;
    }
    public static int getMotif(int place){
        return motif[place];
    }

    private static int[] artifacts = {2, 1, 1};
    public static void setArtifacts(int place, int value){
        artifacts[place] = value;
    }
    public static int getArtifacts(int place){
        return artifacts[place];
    }
}
