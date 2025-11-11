package org.firstinspires.ftc.teamcode.subsystems;

public class StaticValues {
    private static int[] motif = {1, 1, 2};
    public static void setMotif(int place, int value){
        motif[place] = value;
    }
    public static int getMotif(int place){
        return motif[place];
    }

    public static void resetMotif(){
        motif[0] = 1;
        motif[1] = 1;
        motif[2] = 1;
    }

    private static int[] artifacts = {2, 1, 1};
    public static void setArtifacts(int place, int value){
        artifacts[place] = value;
    }
    public static int getArtifacts(int place){
        return artifacts[place];
    }

    public static void resetArtifacts(){
        artifacts[0] = 2;
        artifacts[1] = 1;
        artifacts[2] = 1;
    }

    private static int m = 1;
    public static void setM(int value){
        m = value;
    }
    public static int getM(){
        return m;
    }
}
