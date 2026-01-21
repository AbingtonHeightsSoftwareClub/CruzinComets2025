package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Storage {
    private DcMotorEx intake;
    private boolean intake_on;
    private Gamepad gamepad;



    public void init(HardwareMap hwMap, Gamepad gamepad1){
        intake = hwMap.get(DcMotorEx.class, "intake");
        gamepad=gamepad1;

        // Reset the motor encoder so it reads 0 ticks
        intake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

    }

    public void update(){
        if (gamepad.bWasPressed()){
            intake_on= !intake_on;
        }

        if (intake_on){
            intake.setVelocity(2800.0);

        }else{
            intake.setVelocity(0.0);
        }
    }
}
