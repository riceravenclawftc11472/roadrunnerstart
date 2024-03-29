package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
@Autonomous
public class CameraTest extends LinearOpMode {
    OpenCvCamera Webcam1;
    @Override
    public void runOpMode()  {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId",
                "id", hardwareMap.appContext.getPackageName());
        Webcam1 = OpenCvCameraFactory.getInstance()
                .createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        Pipeline pipeline = new Pipeline(telemetry);
        Webcam1.setPipeline(pipeline);
        Webcam1.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                Webcam1.startStreaming(1280,720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {}
        });




        // Motor config

        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        DcMotor rightSlideMotor = hardwareMap.get(DcMotor.class, "rightSlideMotor");
        DcMotor leftSlideMotor = hardwareMap.get(DcMotor.class, "leftSlideMotor");
        CRServo rightArmMotor = hardwareMap.get(CRServo.class, "rightArmServo");
        CRServo leftArmMotor = hardwareMap.get(CRServo.class, "leftArmServo");
        //Servo drone = hardwareMap.get(Servo.class, "droneServo");
        Servo wrist = hardwareMap.get(Servo.class, "wrist");
        Servo lock = hardwareMap.get(Servo.class, "lock");
        CRServo intake = hardwareMap.get(CRServo.class, "intake");


        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        Robot robot = new Robot(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, rightSlideMotor, leftSlideMotor, telemetry);

        while (!isStarted() && !isStopRequested()) {}
        int position1 = pipeline.getPos();
        telemetry.clearAll();
        telemetry.update();

        telemetry.addData("Pos:", pipeline.position);
        telemetry.update();
        sleep(2000);

        if(position1 == 1){

            robot.move(1200, 1200, -1200, -1200);
            robot.move(-1100, -1100, -1100, -1100);
            robot.move(-140, -140, 140, 140);
            //telemetry.addLine("left");
            telemetry.addData("Pos:", pipeline.position);
            telemetry.update();
        }else if(position1 == 3){

            robot.move(1200, 1200, -1200, -1200);
            robot.move(1100, 1100, 1100, 1100);
            robot.move(140, 140, -140, -140);
            //telemetry.addLine("right");
            telemetry.addData("Pos:", pipeline.position);
            telemetry.update();
        }else if(position1 == 2){

            robot.move(1240, 1240, -1240, -1240);
            //telemetry.addLine("center");
            telemetry.addData("Pos:", pipeline.position);
            telemetry.update();
        }
        sleep(10000);





    }
}


