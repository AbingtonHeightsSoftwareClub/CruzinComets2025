package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.mechanisms.Constants;


@TeleOp
public class LinearActuator extends OpMode {
    /* Declare OpMode members. */

    Servo linear;


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        linear = hardwareMap.get(Servo.class, "linear");
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


        if (gamepad1.yWasPressed()){
            linear.setPosition(1.0);
        }else if (gamepad1.aWasPressed()){
            linear.setPosition(0.0);
        }
        telemetry.addData("position", linear.getPosition());


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

//        brushWheel.setPower(0);
//        hoodWheel.setPower(0);
    }
}
