package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Flipper {

    private Gamepad gamepad;
    private Telemetry telemetry;
    private ServoMechanics servo = new ServoMechanics();


    public void init(HardwareMap hwMap, Gamepad gamepad1, Telemetry main_telemetry) {
        servo.init(hwMap, "spoon");
        servo.setServoRotation(290.0);
        gamepad = gamepad1;
        telemetry = main_telemetry;

    }

    public void update() {
        if (gamepad.bWasPressed()){
            servo.setServoRotation(240.0);
        } else if (gamepad.xWasPressed()){
            servo.setServoRotation(290.0);
        }
        telemetry.addData("Angle", servo.getServoRotation());

    }
}
