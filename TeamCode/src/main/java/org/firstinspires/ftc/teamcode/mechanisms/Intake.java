package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor intake;
    private boolean intake_on;
    private Gamepad gamepad;



    public void init(HardwareMap hwMap, Gamepad gamepad1){
        intake = hwMap.get(DcMotor.class, "intake");
        gamepad=gamepad1;

    }

    public void update(){
        if (gamepad.bWasPressed()){
            intake_on= !intake_on;
        }

        if (intake_on){
            intake.setPower(0.8);

        }else{
            intake.setPower(0.0);
        }
    }
}
