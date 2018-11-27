package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

/**
 * Created by singh on 11/19/2018.
 */
@Autonomous(name = "SamplingCraterPark", group = "Exercises")
public class SamplingCraterPark extends LinearOpMode {

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    HardWareInit hardware = new HardWareInit();

    private static final String VUFORIA_KEY = "AaZU7VL/////AAABmU+EvKJsKkDNtP+NLILzcix53P16ZsLT24wFbXU1PObg0arewAPIYoVFjQSJ8S6qGXd8wUmNIh7R0QTUJj4t7FMDeYVMJykqZ3nIS0qCPajinKFEB4T5I7mptJZuQuCicTwYXA4vCWfPM8+S6WuBTIt4encoSt5sXYT7mdQPeHB/oGBHDzk4Y/0r59kroxgWjpEx9BDBBvhDqT6OeO4KB26Bj4aoXvFiIPkAAWfHNaeOaSmc3nPWLc5H2PJfPHB5UEwLMZzMOaIvsTAEUym0Stwtz3sSyD6jk8ooB9NJy4dxSiNTIWSgXIAy+0F7QClPDSLiw6PlIKq46tUx3ImltjLKEY8erMCPlEXj1SdvnwtK";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    @Override
    public void runOpMode(){
        hardware.initHardware(this);

        hardware.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.strafeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();

        waitForStart();

     //   hardware.liftMotor.setPower(-0.7);

        //Will double check later on. Depends on where the hook is attached.
//        hardware.EncooderReseeter(this);
//        hardware.GoStraight(-300, -1.0);
//        hardware.waitBlock(this);
//        hardware.MotorStop(this);
//        hardware.EncooderReseeter(this);


            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }


        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            sleep(7000);
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());

                telemetry.update();
                sleep(5000);
                if (updatedRecognitions.size() == 2) {
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }
                    if (goldMineralX != -1 || silverMineral1X != -1 || silverMineral2X != -1) {
                        if (goldMineralX != -1 && ((goldMineralX < silverMineral1X) || (goldMineralX < silverMineral2X))) {
                            telemetry.addData("Gold Mineral Position", "Left");
//                            hardware.EncooderReseeter(this);
//
//                            hardware.GoStraight(-300, -1.0);
//                            hardware.waitBlock(this);
//                            hardware.MotorStop(this);
//                            hardware.EncooderReseeter(this);
//                            hardware.EncooderReseeter(this);
//                            hardware.Strafe(-3000, -0.5);
//                            hardware.waitBlockStrafe(this);
//                            hardware.StrafeStop();
                            telemetry.addData("Move", "Towards the Left Gold Mineral");
                            telemetry.update();
                            sleep(3000);
                        } else if (goldMineralX != -1 && ((goldMineralX > silverMineral1X) || (goldMineralX > silverMineral2X))) {
                            telemetry.addData("Gold Mineral Position", "Center");
                            telemetry.addData("Move", "Towards the Gold Mineral in the center, get it soon!");
                            telemetry.update();
                            sleep(3000);
                        } else {
                            telemetry.addData("Gold Mineral Position", "Right");
                            telemetry.addData("Move", "Towards the Gold Mineral on the right. Purple Unicorns!");
                            telemetry.update();
                            sleep(3000);
                        }
                    }
                }
                telemetry.update();
            }
        }
        if (tfod != null) {
            tfod.shutdown();
        }

    }
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
