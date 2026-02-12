package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp
public class ProximitySensor extends OpMode {
    /* Declare OpMode members. */

    private ColorSensor colorSensor;
    private int count;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        colorSensor = hardwareMap.colorSensor.get("proximity");
        count=0;
//        hoodWheel = hardwareMap.get(DcMotor.class, "hood");
//        brushWheel = hardwareMap.get(DcMotor.class, "brush");


    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

        //Heh.... SIX SEVEN

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {


    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {


        if (colorSensor instanceof DistanceSensor) {
            count+=1;

           telemetry.addData("Sensor",((DistanceSensor) colorSensor).getDistance(DistanceUnit.CM));
           telemetry.addData("Count",count);



        }

    }

        /*
         * Code to run ONCE after the driver hits STOP
         */
        @Override
        public void stop () {

//        brushWheel.setPower(0);
//        hoodWheel.setPower(0);
        }
    }
