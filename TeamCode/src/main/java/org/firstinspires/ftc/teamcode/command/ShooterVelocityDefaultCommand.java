//package org.firstinspires.ftc.teamcode.command;
//
//import com.arcrobotics.ftclib.command.CommandBase;
//
//import org.firstinspires.ftc.teamcode.lib.Localizer;
//import org.firstinspires.ftc.teamcode.subsystems.AprilTag.AprilTagSubsystem;
//import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterSubsystem;
//
//public class ShooterVelocityDefaultCommand extends CommandBase {
//    ShooterSubsystem shooter;
//    Localizer localizer;
//    public ShooterVelocityDefaultCommand(ShooterSubsystem shooter, Localizer localizer) {
//        this.shooter = shooter;
//        addRequirements(shooter);
//        this.localizer = localizer;
//    }
//    @Override
//    public void execute() {
//        shooter.shootFromDistance(localizer.getDistance());
//    }
//}
