package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hood {
    private ServoMechanics hood_servo = new ServoMechanics();
    private Gamepad gamepad;
    private Telemetry telemetry;
    double angle;


    public void init(HardwareMap hwMap, Gamepad gamepad_pass_through, Telemetry telemetry) {
        hood_servo.init(hwMap, "hood");
        gamepad = gamepad_pass_through;
        telemetry = telemetry;
    }

    public void update() {

        if (gamepad.dpadUpWasPressed()){
            angle+=10;
            if (angle>290.0){
                angle=290.0;
            }
            hood_servo.setServoRotation(angle);
        }
        else if (gamepad.dpadDownWasPressed()){
            angle-=10;
            if (angle<0){
                angle=0;
            }
            hood_servo.setServoRotation(angle);
        }

    }
}
