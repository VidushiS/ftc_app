package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Created by singh on 10/9/2018.
 */

public class HardWareInit {

    DcMotor leftMotor, rightMotor, strafeMotor, liftMotor, intakeMotor;

    Servo IDCServo;
    CRServo IntakeBoy;

    //This is a method that does the main initializing for all the motors and the servos that will be used.
    public void initHardware(LinearOpMode op) {
        leftMotor = op.hardwareMap.dcMotor.get("leftMotor");
        rightMotor = op.hardwareMap.dcMotor.get("rightMotor");
        strafeMotor = op.hardwareMap.dcMotor.get("SpecialBoy");
        liftMotor = op.hardwareMap.dcMotor.get("liftMotor");
         IDCServo = op.hardwareMap.servo.get("IdontGiveACrap");
        IntakeBoy = op.hardwareMap.crservo.get("IntakeBoy");
        intakeMotor = op.hardwareMap.dcMotor.get("IntakeMotor");

         rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //LinearSlideMotor = op.hardwareMap.dcMotor.get("LinearSlideMotor");
        //*FlipMotor = op.hardwareMap.dcMotor.get("FlipMotor");



    }

    //this is the wait block so that the encoder counts can be registered and work
    public void waitBlock(LinearOpMode op) {
        while (op.opModeIsActive() && this.leftMotor.isBusy()) {
            op.telemetry.addData("leftMotor", this.leftMotor.getCurrentPosition());
            op.telemetry.addData("rightMotor", this.rightMotor.getCurrentPosition());
            op.telemetry.update();
            op.idle();
        }
    }

    //Resets encoder values for all the motors so that there are no mistakes.
    public void EncooderReseeter(LinearOpMode op) {
        this.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //this.strafeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        op.sleep(500);

        this.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       // this.strafeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    //Stops the motors when they have run their number of encoder counts
    public void MotorStop(LinearOpMode op) {
        this.leftMotor.setPower(0);
        this.rightMotor.setPower(0);
    }
    public void liftStop(LinearOpMode op){
        this.liftMotor.setPower(0);
    }
    public void liftReset(LinearOpMode op){
        this.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        op.sleep(509);
        this.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    //Base program for going straight when using encoder counts
    //If you want to go straight in the forward direction, both values should be negative
    public void GoStraight(int encoderCnts, double MotorSpd) {
        this.leftMotor.setTargetPosition(encoderCnts);
        this.rightMotor.setTargetPosition(encoderCnts);

        this.leftMotor.setPower(MotorSpd);
        this.rightMotor.setPower(MotorSpd);
    }

    //For Turning input encoderCnts should be negative and same with MotorSPd to turn clckwise
    public void Turn(int encoderCnts, double MotorSpd) {
        this.leftMotor.setTargetPosition(encoderCnts);
        this.rightMotor.setTargetPosition(-encoderCnts);

        this.leftMotor.setPower(MotorSpd);
        this.rightMotor.setPower(-MotorSpd);
    }

    //Base program for strafing using encoder counts.
    public void Strafe(int encoderCnts, double MotorSpd) {
        this.strafeMotor.setTargetPosition(encoderCnts);
        this.strafeMotor.setPower(MotorSpd);
    }

    //Stops strafe motors
    public void StrafeStop() {

        this.strafeMotor.setPower(0);
    }

    //Wait block used so that the encoder counts can be monitered.
    public void waitBlockStrafe(LinearOpMode op) {
        while (op.opModeIsActive() && this.strafeMotor.isBusy()) {
            op.telemetry.addData("leftMotor", this.strafeMotor.getCurrentPosition());

            op.telemetry.update();
            op.idle();
        }
    }

    public void waitBlockLift(LinearOpMode op) {
        while (op.opModeIsActive() && this.liftMotor.isBusy()) {
            op.telemetry.addData("liftMotor", this.liftMotor.getCurrentPosition());

            op.telemetry.update();
            op.idle();
        }
    }
}

