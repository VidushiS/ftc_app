package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by singh on 10/31/2018.
 */
@Autonomous(name= "ScorePark", group="Exercises")
public class AutoScorePark extends LinearOpMode {
    HardWareInit hardware = new HardWareInit();

    @Override
    public void runOpMode() {
        hardware.initHardware(this);
        hardware.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.strafeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //    hardware.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //  hardware.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        waitForStart();

        //hardware.EncooderReseeter(this);

        //lift goes down
      /*  hardware.LeftSlideMotor.setPower(.5);
       // hardware.RightSlideMotor.setPower(.5);

        sleep(3000);

        hardware.LeftSlideMotor.setPower(0);
      //  hardware.RightSlideMotor.setPower(0);

        //robot strafes to get hook off
        sleep(500);

        hardware.strafeMotor.setPower(0.5);
        sleep(2500);
        hardware.strafeMotor.setPower(0);

        //the lift goes back to its original position
        hardware.LeftSlideMotor.setPower(-.5);
       // hardware.RightSlideMotor.setPower(-.5);

        sleep(3000);

        hardware.LeftSlideMotor.setPower(0);
       // hardware.RightSlideMotor.setPower(0);*/


     /*   hardware.GoStraight(5000, 1.0);
        hardware.waitBlock(this);
        hardware.MotorStop(this);
        hardware.EncooderReseeter(this);

        hardware.IDCServo.setPosition(1);
        telemetry.addData("Servo Position", hardware.IDCServo.getPosition());
        telemetry.update();
        sleep(1000);
        hardware.IDCServo.setPosition(-1);
        telemetry.addData("Servo Position", hardware.IDCServo.getPosition());
        telemetry.update();*/
        hardware.EncooderReseeter(this);
        hardware.Strafe(-6000, -0.5);
        hardware.waitBlockStrafe(this);
        hardware.StrafeStop();

        hardware.strafeMotor.setPower(-0.5);
        sleep(4000);
        hardware.strafeMotor.setPower(0);
        sleep(50);

        //Turning for clockwise
        hardware.EncooderReseeter(this);
        hardware.Turn(-300, -0.5);
        hardware.waitBlock(this);
        hardware.MotorStop(this);

        hardware.EncooderReseeter(this);
        hardware.GoStraight(-5000, -1.0);
        hardware.waitBlock(this);
        hardware.MotorStop(this);
     /*   hardware.IDCServo.setPosition(0.05);

        sleep(3000);
        hardware.IDCServo.setPosition(0.5);


        sleep(50);

        hardware.leftMotor.setPower(-1.0);
        hardware.rightMotor.setPower(-1.0);

        sleep(3000);
        hardware.leftMotor.setPower(0);
        hardware.rightMotor.setPower(0);
*/
    }

}