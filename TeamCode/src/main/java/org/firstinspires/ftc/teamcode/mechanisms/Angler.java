package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Angler {
    private ServoMechanics angling_servo = new ServoMechanics();
    private Gamepad gamepad;
    private Telemetry telemetry;

    double ball_state;


    public void init(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry) {
        angling_servo.init(hwMap, "angling");
        gamepad = gamepad1;
        telemetry = telemetry;
        ball_state=1.0;
    }

    public void update() {

        if (gamepad.yWasPressed()){

            angling_servo.setServoRotation(ball_state*290.0/3.0);
        }


        telemetry.addData("Rotation", angling_servo.getServoRotation());
        telemetry.addData("Ball State", ball_state);

    }
}
