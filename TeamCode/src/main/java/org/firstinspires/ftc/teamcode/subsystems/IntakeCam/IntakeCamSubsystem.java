//package org.firstinspires.ftc.teamcode.subsystems.IntakeCam;
//
//import android.graphics.Color;
//import android.util.Size;
//
//import com.arcrobotics.ftclib.command.SubsystemBase;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.opencv.Circle;
//import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
//import org.firstinspires.ftc.vision.opencv.ColorRange;
//import org.firstinspires.ftc.vision.opencv.ImageRegion;
//
//import java.util.List;
//
//public class IntakeCamSubsystem extends SubsystemBase {
//    ColorBlobLocatorProcessor colorLocator;
//    VisionPortal portal;
//    Telemetry telemetry;
//    public void IntakeCamSubsytem(HardwareMap hwMap, Telemetry telemetry) {
//        this.telemetry = telemetry;
//        colorLocator = new ColorBlobLocatorProcessor.Builder()
//                .setTargetColorRange(ColorRange.ARTIFACT_PURPLE)   // Use a predefined color match
//                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
//                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.75, 0.75, 0.75, -0.75))
//                .setDrawContours(true)   // Show contours on the Stream Preview
//                .setBoxFitColor(0)       // Disable the drawing of rectangles
//                .setCircleFitColor(Color.rgb(255, 255, 0)) // Draw a circle
//                .setBlurSize(5)          // Smooth the transitions between different colors in image
//
//                // the following options have been added to fill in perimeter holes.
//                .setDilateSize(15)       // Expand blobs to fill any divots on the edges
//                .setErodeSize(15)        // Shrink blobs back to original size
//                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)
//
//                .build();
//        portal = new VisionPortal.Builder()
//                .addProcessor(colorLocator)
//                .setCameraResolution(new Size(320, 240))
//                .setCamera(hwMap.get(WebcamName.class, "intakeCam"))
//                .build();
//    }
//
//    @Override
//    public void periodic() {
//        List<ColorBlobLocatorProcessor.Blob> blobs = colorLocator.getBlobs();
//        ColorBlobLocatorProcessor.Util.filterByCriteria(
//                ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA,
//                IntakeCamConstants.minContourArea, IntakeCamConstants.maxContourArea, blobs);
//        ColorBlobLocatorProcessor.Util.filterByCriteria(
//                ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY,
//                IntakeCamConstants.minCircularity, IntakeCamConstants.maxCircularity, blobs);
//
//        for (ColorBlobLocatorProcessor.Blob b : blobs) {
//
//            Circle circleFit = b.getCircle();
//            telemetry.addLine(String.format("%5.3f      %3d     (%3d,%3d)",
//                    b.getCircularity(), (int) circleFit.getRadius(), (int) circleFit.getX(), (int) circleFit.getY()));
//        }
//
//}
//
//
//        // WARNING:  To view the stream preview on the Driver Station, this code runs in INIT mode.
//        while (opModeIsActive() || opModeInInit()) {
//            telemetry.addData("preview on/off", "... Camera Stream\n");
//
//            // Read the current list
//
//
//              // filter out very small blobs.
//
//
//            telemetry.addLine("Circularity Radius Center");
//
//            // Display the Blob's circularity, and the size (radius) and center location of its circleFit.
//            for (ColorBlobLocatorProcessor.Blob b : blobs) {
//
//                Circle circleFit = b.getCircle();
//                telemetry.addLine(String.format("%5.3f      %3d     (%3d,%3d)",
//                        b.getCircularity(), (int) circleFit.getRadius(), (int) circleFit.getX(), (int) circleFit.getY()));
//            }
//
//            telemetry.update();
//            sleep(100); // Match the telemetry update interval.
//        }
//    }
//}
