package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Storage {
    private DcMotorEx storage;
    private Gamepad gamepad;
    private double velocity;
    private boolean advancing_wheels;
    private boolean left_trigger_Pressed_LastCycle;
    private boolean direction;


    public void init(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry) {
        storage = hwMap.get(DcMotorEx.class, "storage");
        gamepad = gamepad1;
        // Reset the motor encoder so it reads 0 ticks
        storage.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        storage.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        advancing_wheels = false;
        velocity = 1000.0;
        left_trigger_Pressed_LastCycle=false;
        direction = true;
    }

    public void update() {

        if (gamepad.aWasPressed()){
            direction=!direction;
        }

        if (direction){
            storage.setDirection(DcMotorEx.Direction.FORWARD);
        }else{
            storage.setDirection(DcMotorEx.Direction.REVERSE);
        }


        if (gamepad.left_trigger>0.25 && !left_trigger_Pressed_LastCycle){
            advancing_wheels= !advancing_wheels;
        }

        if (advancing_wheels){
            storage.setVelocity(2800.0);

        }else{
            storage.setVelocity(0.0);
        }

        left_trigger_Pressed_LastCycle=gamepad.left_trigger>0.25;
    }
}
