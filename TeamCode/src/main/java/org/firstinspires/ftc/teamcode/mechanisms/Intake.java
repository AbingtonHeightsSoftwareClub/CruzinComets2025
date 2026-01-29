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
    private boolean left_bumper_Pressed_LastCycle;



    public void init(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry){
        intake = hwMap.get(DcMotorEx.class, "intake");
        gamepad=gamepad1;
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        // Reset the motor encoder so it reads 0 ticks
        intake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        left_bumper_Pressed_LastCycle=false;
        direction = true;

    }

    public void update(){
        if (gamepad.bWasPressed()){
            intake_on= !intake_on;
        }

        if (gamepad.left_bumper && !left_bumper_Pressed_LastCycle){
            if (direction){
                direction=false;
                intake.setDirection(DcMotorSimple.Direction.FORWARD);
            }else{
                direction=true;
                intake.setDirection(DcMotorSimple.Direction.REVERSE);
            }
        }

        if (intake_on){
            intake.setVelocity(2800.0);

        }else{
            intake.setVelocity(0.0);
        }

        left_bumper_Pressed_LastCycle=gamepad.right_bumper;
    }
}
