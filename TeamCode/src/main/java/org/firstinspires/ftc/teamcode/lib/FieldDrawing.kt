package org.firstinspires.ftc.teamcode.lib

import com.arcrobotics.ftclib.geometry.Pose2d
import com.arcrobotics.ftclib.geometry.Translation2d
import com.bylazar.field.PanelsField
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.constants.DrivebaseConstants

class FieldDrawing {
    private val panelsField = PanelsField.field


    fun draw(pose : Pose2d) {
        panelsField.setOffsets(PanelsField.presets.DEFAULT_FTC)
        panelsField.setStyle(fill = "none", outline = "black", width = 0.5)
        val robotCornerX = pose.rotation.cos*DrivebaseConstants.length/2 - pose.rotation.sin*DrivebaseConstants.width/2
        val robotCornerY = pose.rotation.sin*DrivebaseConstants.length/2 + pose.rotation.cos*DrivebaseConstants.width/2
    }
}