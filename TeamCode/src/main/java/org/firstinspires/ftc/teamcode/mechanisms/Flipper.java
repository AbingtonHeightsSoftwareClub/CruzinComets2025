package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Flipper {
    private final ServoMechanics storage_servo = new ServoMechanics();
    private Gamepad gamepad;
    private Telemetry telemetry;
    private double angle;


    public void init(HardwareMap hwMap, Gamepad gamepad1, Telemetry main_telemetry) {
        storage_servo.init(hwMap, "flipper");
        gamepad = gamepad1;
        telemetry = main_telemetry;
        angle = 0.0;
    }

    public void update() {
        storage_servo.setServoRotation(1.0);
        if (gamepad.xWasPressed()){


            angle+=5;
        }

//        if (storage_servo.getServoRotation()==30.0){
//            storage_servo.setServoRotation(0.0);
//        }


        telemetry.addData("Angling Angle: ", storage_servo.getServoRotation());
        telemetry.addData(" Angle: ", angle);

    }
}
