package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "CraterPark", group = "Exercises")

/**
 * Created by singh on 10/17/2018.
 */

public class AutoCraterPark extends LinearOpMode{

    HardWareInit hardware = new HardWareInit();

    @Override
    public void runOpMode(){
        hardware.initHardware(this);

        hardware.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        waitForStart();

        hardware.EncooderReseeter(this);

        //lift goes down
        /*hardware.LeftSlideMotor.setPower(.5);


        sleep(3000);

        hardware.LeftSlideMotor.setPower(0);
1

        //robot strafes to get hook off
        sleep(500);

        hardware.strafeMotor.setPower(0.5);
        sleep(2500);
        hardware.strafeMotor.setPower(0);

        //the lift goes back to its original position
        hardware.LeftSlideMotor.setPower(-.5);


        sleep(3000);

        hardware.LeftSlideMotor.setPower(0);




        hardware.GoStraight(3000, 1.0);
        hardware.waitBlock(this);
        hardware.MotorStop(this);
        hardware.EncooderReseeter(this);

        //Deploy the color servo. Below is the following mock code required for it to work.
        /*
        * while(ColorSensor1 = silver && ColorSensor2 = silver){
        *   robot should raise one arm and turn so that the middle is knocked off
        *   idle();
        *}
        *while(ColorSensor1 = Gold){
        * robot should turn in that direction to knock it off, opposite if other colorsensor reads gold
        *}
        * */

       /* hardware.Turn(400, .5);
        hardware.waitBlock(this);
        hardware.MotorStop(this);
        hardware.EncooderReseeter(this);


        hardware.GoStraight(1000, 1.0);
        hardware.waitBlock(this);
        hardware.MotorStop(this);
        hardware.EncooderReseeter(this);*/

     /*   hardware.strafeMotor.setPower(-1.0);
        sleep(4500);
        hardware.strafeMotor.setPower(0);
        sleep(50);*/


/*        hardware.GoStraight(5000, 1.0);
        hardware.waitBlock(this);
        hardware.MotorStop(this);
        hardware.EncooderReseeter(this);*/
        hardware.strafeMotor.setPower(-1.0);
        sleep(1000);
        hardware.strafeMotor.setPower(0);
        sleep(50);

        hardware.EncooderReseeter(this);
        hardware.Turn(300, 0.5);
        hardware.waitBlock(this);
        hardware.MotorStop(this);

        hardware.GoStraight(-3000, -1.0);
        hardware.waitBlock(this);
        hardware.MotorStop(this);
        hardware.EncooderReseeter(this);







    }
}