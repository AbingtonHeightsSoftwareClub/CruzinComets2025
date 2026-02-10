package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    private DcMotorEx intake;
    private boolean intake_on;
    private Gamepad gamepad;
    private boolean direction;
    private boolean left_trigger_Pressed_LastCycle;



    public void init(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry){
        intake = hwMap.get(DcMotorEx.class, "intake");
        gamepad=gamepad1;


        // Reset the motor encoder so it reads 0 ticks
        intake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        left_trigger_Pressed_LastCycle=false;
        direction = true;

        intake.setDirection(DcMotorEx.Direction.REVERSE);

    }

    public void update(){

        if (gamepad.aWasPressed()){
            direction=!direction;
        }

        if (direction){
            intake.setDirection(DcMotorEx.Direction.REVERSE);
        }else{
            intake.setDirection(DcMotorEx.Direction.FORWARD);
        }

        if (gamepad.left_trigger>0.25 && !left_trigger_Pressed_LastCycle){
            intake_on= !intake_on;
        }

        if (intake_on){
            intake.setVelocity(2800.0);

        }else{
            intake.setVelocity(0.0);
        }

        left_trigger_Pressed_LastCycle=gamepad.left_trigger>0.25;

    }
}
