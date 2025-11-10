package org.firstinspires.ftc.teamcode.lib

import com.arcrobotics.ftclib.geometry.Pose2d
import com.arcrobotics.ftclib.geometry.Translation2d
import com.bylazar.field.PanelsField
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

class FieldDrawing {
    private val panelsField = PanelsField.field

    fun draw(pose : Pose2d) {
        panelsField.setOffsets(PanelsField.presets.DEFAULT_FTC)

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
        panelsField.setStyle(fill = "rgba(0,0,255,0.0)", outline = "blue", width = 1.5)
        moveTo(c1.x, c1.y)
        lineTo(c2.x, c2.y)
        lineTo(c3.x, c3.y)
        lineTo(c4.x, c4.y)
        lineTo(c1.x, c1.y) // close

        // Draw heading line from center to front of robot
        val frontX = cx + cos * halfLength
        val frontY = cy + sin * halfLength
        panelsField.setStyle(fill = "rgba(0,0,0,0.0)", outline = "red", width = 2.0)
        moveTo(cx, cy)
        lineTo(frontX, frontY)

        // Draw a small tick centered at the front (perpendicular to heading)
        val tickLength = 3.0 // total tick length in inches (adjust as desired)
        val tickHalf = tickLength / 2.0
        // perpendicular unit vector to heading: (-sin, cos)
        val px = -sin
        val py = cos
        val tickAx = frontX + px * tickHalf
        val tickAy = frontY + py * tickHalf
        val tickBx = frontX - px * tickHalf
        val tickBy = frontY - py * tickHalf
        panelsField.setStyle(fill = "rgba(0,0,0,0.0)", outline = "red", width = 2.0)
        moveTo(tickAx, tickAy)
        lineTo(tickBx, tickBy)
        panelsField.update()
    }
}