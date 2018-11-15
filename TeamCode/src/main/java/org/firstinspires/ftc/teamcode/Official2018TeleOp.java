package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by singh on 10/9/2018.
 */
@TeleOp(name = "Official2018Teleop", group ="Exercises")
public class Official2018TeleOp extends LinearOpMode{

    double leftY, rightY, strafeY, strafeRight;
    HardWareInit init = new HardWareInit();
    GamePad gamepad = new GamePad();

    @Override
    public void runOpMode(){
        init.initHardware(this);
        init.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        init.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Mode", "Waiting");
        telemetry.update();

        waitForStart();
        sleep(500);

        telemetry.addData("Mode", "Active");
        while (opModeIsActive()){
            leftY = Range.clip(gamepad1.left_stick_y, -1,1);
            rightY = Range.clip(gamepad1.right_stick_y, -1, 1);
            strafeY = Range.clip(gamepad1.left_trigger, 0, 1);
            strafeRight = Range.clip(gamepad1.right_trigger,0,1);

            if(gamepad1.right_trigger > 0 && gamepad1.left_trigger == 0){
                init.strafeMotor.setPower(strafeRight);
                idle();
            }
            else if(gamepad1.left_trigger > 0 && gamepad1.right_trigger == 0 ){
                init.strafeMotor.setPower(-strafeY);
                idle();
            }
            else init.strafeMotor.setPower(0);
            idle();

            init.leftMotor.setPower(leftY);
            init.rightMotor.setPower(rightY);

            /*if(gamepad.x2(gamepad2.x) == 3){
                init.LeftSlideMotor.setPower(.5);
                init.RightSlideMotor.setPower(.5);
                idle();
            }
            else if(gamepad.x2(gamepad2.x) == 2){
                init.LeftSlideMotor.setPower(-.5);
                init.RightSlideMotor.setPower(-.5);
                idle();
            }
            else if(gamepad.x2(gamepad2.x) == 1){
                init.LeftSlideMotor.setPower(0);
                init.RightSlideMotor.setPower(0);
                idle();
            }*/

        /*    if(gamepad1.dpad_down){
                init.LeftSlideMotor.setPower(-.5);
                telemetry.addData("Motor speed", init.LeftSlideMotor.getPower());
                telemetry.update();
                idle();
            }
            else if(gamepad1.dpad_up){
                init.LeftSlideMotor.setPower(.5);

                telemetry.addData("Motor speed", init.LeftSlideMotor.getPower());
                telemetry.update();
                idle();
            }
            else if( !gamepad1.dpad_up && !gamepad1.dpad_down){
                init.LeftSlideMotor.setPower(.0);

                telemetry.addData("Motor speed", init.LeftSlideMotor.getPower());
                telemetry.update();
                idle();
            }
/*
            if(gamepad.b(gamepad2.b)){
                init.IntakeMotor.setPower(.5);
                idle();
            }
            else if(!gamepad.b(gamepad2.b)){
                init.IntakeMotor.setPower(.0);
                idle();
            }
            if(gamepad.a(gamepad2.a)){
                init.IntakeMotor.setPower(-.5);
                idle();
            }
            else if(!gamepad.a(gamepad2.a)){
                init.IntakeMotor.setPower(0);
                idle();
            }*/

            //Check to see if you can run the statements below as threads
            if (gamepad1.dpad_up == true && gamepad1.dpad_down == false){
                init.liftMotor.setPower(0.7);

            }
            if (gamepad1.dpad_down == true && gamepad1.dpad_down == false){
                init.liftMotor.setPower(-0.7);

            }
            if(gamepad1.dpad_down == false && gamepad1.dpad_up == false){
                init.liftMotor.setPower(0.0);

            }




            idle();
        }


    }
}