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
        init.leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        init.rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        init.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        init.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        init.liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

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

            }
            else if(gamepad1.left_trigger > 0 && gamepad1.right_trigger == 0 ){
                init.strafeMotor.setPower(-strafeY);

            }
            else init.strafeMotor.setPower(0);


            init.leftMotor.setPower(leftY);
            init.rightMotor.setPower(rightY);


            //Check to see if you can run the statements below as threads
            if (gamepad1.dpad_up && !gamepad1.dpad_down){
                init.liftMotor.setPower(0.7);
            }
            if (gamepad1.dpad_down  && !gamepad1.dpad_up){
                init.liftMotor.setPower(-0.7);
            }
            if(!gamepad1.dpad_down && !gamepad1.dpad_up){
                init.liftMotor.setPower(0.0);
            }

            if(gamepad2.b && !gamepad2.a){
                init.IntakeBoy.setPower(0.5);
            }
            if(gamepad2.a && !gamepad2.b){
                init.IntakeBoy.setPower(-0.5);
            }
            if(!gamepad2.a && !gamepad2.b){
                init.IntakeBoy.setPower(0);
            }

            if (gamepad2.dpad_up && !gamepad2.dpad_down){
                init.intakeMotor.setPower(0.7);
            }
            if (gamepad2.dpad_down  && !gamepad2.dpad_up){
                init.intakeMotor.setPower(-0.7);
            }
            if(!gamepad2.dpad_down && !gamepad2.dpad_up){
                init.intakeMotor.setPower(0.0);
            }



            idle();
        }
//        init.leftMotor.setPower(0);
//        init.rightMotor.setPower(0);

    }
}