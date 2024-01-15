package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvPipeline;
import org.opencv.imgproc.Imgproc;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



public class Pipeline extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();
    public enum Location {
        LEFT,
        RIGHT,
        CENTER
    }

    int position;
    private Location location;
    public Pipeline (Telemetry t){ telemetry = t;}


    static final Rect LEFT_ROI = new Rect(
            new Point(0, 0),
            new Point(426, 720));
    static final Rect CENTER_ROI = new Rect(
            new Point(426, 0),
            new Point(852, 720)
    );
    static final Rect RIGHT_ROI = new Rect(
            new Point(852, 0),
            new Point(1278, 720)
    );


    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(0, 100, 20);
        Scalar highHSV = new Scalar(10, 255, 255);

        Core.inRange(mat, lowHSV, highHSV, mat);
        Mat right = mat.submat(RIGHT_ROI);
        Mat center = mat.submat(CENTER_ROI);
        Mat left = mat.submat(LEFT_ROI);

        double leftValue = Core.sumElems(left).val[0] /  LEFT_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;
        double centerValue = Core.sumElems(center).val[0] / CENTER_ROI.area() / 255;

        left.release();
        right.release();
        center.release();

        if(leftValue > rightValue){
            if(leftValue > centerValue){
                telemetry.addLine("left");
                position = 1;
            }else{
                telemetry.addLine("center");
                position = 2;
            }
            telemetry.update();
        }else{
            if(rightValue > centerValue){
                telemetry.addLine("right");
                position = 3;
            }else{
                telemetry.addLine("center");
                position = 2;
            }
            telemetry.update();
        }

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        Scalar colorEmpty = new Scalar(255, 0, 0);
        Scalar colorFull = new Scalar(0, 255, 0);
        Imgproc.rectangle(mat, LEFT_ROI, location == Location.LEFT? colorEmpty:colorFull);
        Imgproc.rectangle(mat, RIGHT_ROI, location == Location.RIGHT? colorEmpty:colorFull);
        Imgproc.rectangle(mat, CENTER_ROI, location == Location.CENTER? colorEmpty:colorFull);
        return mat;

    }
    public Location getLocation() {
        return location;
    }
    public int getPos(){
        return position;
    }
}
