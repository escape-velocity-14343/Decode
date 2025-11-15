package org.firstinspires.ftc.teamcode.lib

import com.arcrobotics.ftclib.geometry.Pose2d
import com.arcrobotics.ftclib.geometry.Translation2d
import com.bylazar.field.CanvasRotation
import com.bylazar.field.FieldPresetParams
import com.bylazar.field.PanelsField
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

class FieldDrawing {
    private val panelsField = PanelsField.field

    fun draw(pose : Pose2d) {
        panelsField.setOffsets(
            FieldPresetParams(name = "FTC Field", rotation = CanvasRotation.DEG_90, offsetX = -36.0, offsetY = -36.0, reverseXY = true, flipX = true, flipY = true))

        // Compute robot rectangle corners (robot size: 15" length x 14" width)
        val halfLength = 15.0 / 2.0
        val halfWidth  = 14.0 / 2.0

        val cos = pose.rotation.cos
        val sin = pose.rotation.sin

        val cx = pose.x
        val cy = pose.y

        val c1 = Translation2d(
            cx + cos * halfLength - sin * halfWidth,
            cy + sin * halfLength + cos * halfWidth
        )
        val c2 = Translation2d(
            cx - cos * halfLength - sin * halfWidth,
            cy - sin * halfLength + cos * halfWidth
        )
        val c3 = Translation2d(
            cx - cos * halfLength + sin * halfWidth,
            cy - sin * halfLength - cos * halfWidth
        )
        val c4 = Translation2d(
            cx + cos * halfLength + sin * halfWidth,
            cy + sin * halfLength - cos * halfWidth
        )

        // simple cursor/line helpers for this PanelsField API
        fun moveTo(x: Double, y: Double) {
            panelsField.moveCursor(x, y)
        }
        fun lineTo(x: Double, y: Double) {
            panelsField.line(x, y)
        }

        // Draw rectangle outline: set style, move to first corner, then line to each corner and back

        moveTo(c1.x, c1.y)
        panelsField.setStyle(fill = "rgba(0,0,255,0.0)", outline = "blue", width = 1.5)
        lineTo(c2.x, c2.y)
        panelsField.setStyle(fill = "rgba(0,0,255,0.0)", outline = "green", width = 1.5)
        lineTo(c3.x, c3.y)
        panelsField.setStyle(fill = "rgba(0,0,255,0.0)", outline = "yellow", width = 1.5)
        lineTo(c4.x, c4.y)
        panelsField.setStyle(fill = "rgba(0,0,255,0.0)", outline = "red", width = 1.5)
        lineTo(c1.x, c1.y) // close

        // Draw heading line from center to front of robot
        val frontX = cx + cos * halfLength
        val frontY = cy + sin * halfLength
        panelsField.setStyle(fill = "rgba(0,0,0,0.0)", outline = "purple", width = 2.0)
        moveTo(cx, cy)
        lineTo(frontX, frontY)

        panelsField.update()
    }
}