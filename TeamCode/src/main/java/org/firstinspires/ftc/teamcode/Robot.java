package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Robot {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;
    DcMotor rightSlideMotor;
    DcMotor leftSlideMotor;

    private final Telemetry telemetry;
    Robot(DcMotor flm, DcMotor blm, DcMotor frm, DcMotor brm, DcMotor rsm, DcMotor lsm, Telemetry t){
        frontLeftMotor = flm;
        frontRightMotor = frm;
        backRightMotor = brm;
        backLeftMotor = blm;
        rightSlideMotor = rsm;
        leftSlideMotor = lsm;
        this.telemetry = t;
        // braking
        /*
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         do */
    }
    /*
    fl, bl, fr, br

    forward: r, r, f, f
    backward: f, r, r, f
    left: f, f, r, r,
    right: r, r, f, f
    turnRight: r, f, r, f
    turnLeft: f, r, f, r
    */

    public void moveForward(int flm, int blm, int frm, int brm){
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setTargetPosition(flm);
        backLeftMotor.setTargetPosition(blm);
        frontRightMotor.setTargetPosition(frm);
        backRightMotor.setTargetPosition(brm);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        while(areBusy()) {
            frontLeftMotor.setPower(0.3);
            backLeftMotor.setPower(0.3);
            frontRightMotor.setPower(0.3);
            backRightMotor.setPower(0.3);
            telemetry.addData("FrontLeftMotor", frontLeftMotor.getCurrentPosition());
            telemetry.addData("BackLeftMotor", backLeftMotor.getCurrentPosition());
            telemetry.addData("FrontRightMotor", frontRightMotor.getCurrentPosition());
            telemetry.addData("BackRightMotor", backRightMotor.getCurrentPosition());
            telemetry.update();
        }
        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
        backRightMotor.setPower(0.0);


    }

    public void slidesUp(int rsm, int lsm){
        leftSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightSlideMotor.setTargetPosition(rsm);
        leftSlideMotor.setTargetPosition(lsm);

        rightSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightSlideMotor.setDirection(DcMotor.Direction.FORWARD);
        leftSlideMotor.setDirection(DcMotor.Direction.REVERSE);


        rightSlideMotor.setPower(0.7);
        leftSlideMotor.setPower(0.7);
    }

    public void slidesDown(int rsm, int lsm){
        leftSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightSlideMotor.setTargetPosition(rsm);
        leftSlideMotor.setTargetPosition(lsm);

        rightSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightSlideMotor.setDirection(DcMotor.Direction.REVERSE);
        leftSlideMotor.setDirection(DcMotor.Direction.FORWARD);


        rightSlideMotor.setPower(0.7);
        leftSlideMotor.setPower(0.7);
    }
    public void move(int flm, int blm, int frm, int brm){
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setTargetPosition(flm);
        backLeftMotor.setTargetPosition(blm);
        frontRightMotor.setTargetPosition(frm);
        backRightMotor.setTargetPosition(brm);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        while(areBusy()) {
            frontLeftMotor.setPower(0.3);
            backLeftMotor.setPower(0.3);
            frontRightMotor.setPower(0.3);
            backRightMotor.setPower(0.3);
        }
        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
        backRightMotor.setPower(0.0);


    }

    public void moveLeft(int flm, int blm, int frm, int brm){

        // Reset encoders
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //tick Marks

        frontLeftMotor.setTargetPosition(flm);
        backLeftMotor.setTargetPosition(blm);
        frontRightMotor.setTargetPosition(frm);
        backRightMotor.setTargetPosition(brm);

        // Tells it to start moving to the position

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //These are the directions that the motors will go in

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        // Don't make the power too fast

        while(areBusy()) {
            frontLeftMotor.setPower(0.3);
            backLeftMotor.setPower(0.3);
            frontRightMotor.setPower(0.3);
            backRightMotor.setPower(0.3);
        }
        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
        backRightMotor.setPower(0.0);



    }

    public void moveRight(int flm, int blm, int frm, int brm){

        // Reset encoders
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //tick Marks

        frontLeftMotor.setTargetPosition(flm);
        backLeftMotor.setTargetPosition(blm);
        frontRightMotor.setTargetPosition(frm);
        backRightMotor.setTargetPosition(brm);

        // Tells it to start moving to the position

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //These are the directions that the motors will go in

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        // Don't make the power too fast

        while(areBusy()) {
            frontLeftMotor.setPower(0.3);
            backLeftMotor.setPower(0.3);
            frontRightMotor.setPower(0.3);
            backRightMotor.setPower(0.3);
            telemetry.addData("FrontLeftMotor", frontLeftMotor.getCurrentPosition());
            telemetry.addData("BackLeftMotor", backLeftMotor.getCurrentPosition());
            telemetry.addData("FrontRightMotor", frontRightMotor.getCurrentPosition());
            telemetry.addData("BackRightMotor", backRightMotor.getCurrentPosition());
            telemetry.update();
        }
        frontLeftMotor.setPower(0.0);
        backLeftMotor.setPower(0.0);
        frontRightMotor.setPower(0.0);
        backRightMotor.setPower(0.0);



    }

    public void turnRight(int flm, int blm, int frm, int brm){

        // Reset encoders
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //tick Marks

        frontLeftMotor.setTargetPosition(flm);
        backLeftMotor.setTargetPosition(blm);
        frontRightMotor.setTargetPosition(frm);
        backRightMotor.setTargetPosition(brm);

        // Tells it to start moving to the position

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //These are the directions that the motors will go in

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        // Don't make the power too fast

        frontLeftMotor.setPower(0.6);
        backLeftMotor.setPower(0.6);
        frontRightMotor.setPower(0.6);
        backRightMotor.setPower(0.6);



    }

    public void turnLeft(int flm, int blm, int frm, int brm){

        // Reset encoders
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //tick Marks

        frontLeftMotor.setTargetPosition(flm);
        backLeftMotor.setTargetPosition(blm);
        frontRightMotor.setTargetPosition(frm);
        backRightMotor.setTargetPosition(brm);

        // Tells it to start moving to the position

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //These are the directions that the motors will go in

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        // Don't make the power too fast

        frontLeftMotor.setPower(0.6);
        backLeftMotor.setPower(0.6);
        frontRightMotor.setPower(0.6);
        backRightMotor.setPower(0.6);



    }

    public boolean areBusy(){
        return frontLeftMotor.isBusy() && backLeftMotor.isBusy() && frontRightMotor.isBusy() && backRightMotor.isBusy();
    }





}
