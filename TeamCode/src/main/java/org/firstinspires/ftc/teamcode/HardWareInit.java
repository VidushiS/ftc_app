package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;



/**
 * Created by singh on 10/9/2018.
 */

public class HardWareInit{

    DcMotor leftMotor, rightMotor, strafeMotor, liftMotor, IntakeMotor, FlipMotor;
    Servo IDCServo;

    public void initHardware(LinearOpMode op) {
        leftMotor = op.hardwareMap.dcMotor.get("leftMotor");
        rightMotor = op.hardwareMap.dcMotor.get("rightMotor");
        strafeMotor = op.hardwareMap.dcMotor.get("SpecialBoy");
          liftMotor = op.hardwareMap.dcMotor.get("liftMotor");
        //  IDCServo = op.hardwareMap.servo.get("IdontGiveACrap");
        // RightSlideMotor = op.hardwareMap.dcMotor.get("RightSlideMotor");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // RightSlideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
      /*  IntakeMotor = op.hardwareMap.dcMotor.get("IntakeMotor");


        */
        /*FlipMotor = op.hardwareMap.dcMotor.get("FlipMotor");

        DumpServo = op.hardwareMap.servo.get("DumpServo");*/
    }
    public void waitBlock(LinearOpMode op){
        while (op.opModeIsActive() && this.leftMotor.isBusy()){
            op.telemetry.addData("leftMotor", this.leftMotor.getCurrentPosition());
            op.telemetry.addData("rightMotor", this.rightMotor.getCurrentPosition());
            op.telemetry.update();
            op.idle();
        }
    }
    public void EncooderReseeter(LinearOpMode op){
        this.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.strafeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        op.sleep(500);

        this.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.strafeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void MotorStop(LinearOpMode op){
        this.leftMotor.setPower(0);
        this.rightMotor.setPower(0);
    }

    public void GoStraight(int encoderCnts, double MotorSpd){
        this.leftMotor.setTargetPosition(encoderCnts);
        this.rightMotor.setTargetPosition(encoderCnts);

        this.leftMotor.setPower(MotorSpd);
        this.rightMotor.setPower(MotorSpd);
    }
    //For Turning input encoderCnts should be negative and same with MotorSPd to turn clckwise
    public void Turn(int encoderCnts, double MotorSpd){
        this.leftMotor.setTargetPosition(encoderCnts);
        this.rightMotor.setTargetPosition(-encoderCnts);

        this.leftMotor.setPower(MotorSpd);
        this.rightMotor.setPower(-MotorSpd);
    }
    public void Strafe(int encoderCnts, double MotorSpd){
        this.strafeMotor.setTargetPosition(encoderCnts);
        this.strafeMotor.setPower(MotorSpd);
    }
    public void StrafeStop(){

        this.strafeMotor.setPower(0);
    }
    public void waitBlockStrafe(LinearOpMode op){
        while (op.opModeIsActive() && this.strafeMotor.isBusy()){
            op.telemetry.addData("leftMotor", this.strafeMotor.getCurrentPosition());

            op.telemetry.update();
            op.idle();
        }
    }

}