package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Storage {
    private ServoMechanics storage_servo = new ServoMechanics();
    private Gamepad gamepad;
    private Telemetry telemetry;

    double ball_state;


    public void init(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry) {
        storage_servo.init(hwMap);
        gamepad = gamepad1;
        telemetry = telemetry;
        ball_state=1.0;
    }

    public void update() {

        if (gamepad.yWasPressed()){
            ball_state+=1.0;
            if (ball_state>3.0){
                ball_state=1.0;
            }
            storage_servo.setServoRotation(ball_state*290.0/3.0);
        }


        telemetry.addData("Rotation", storage_servo.getServoRotation());
        telemetry.addData("Ball State", ball_state);

    }
}
