package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    ServoMechanics bench = new ServoMechanics();
    private double angle;
    private Gamepad gamepad;
    private Telemetry telemetry;



    public void init(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry){
        bench.init(hwMap);
        angle = -1.0;
        gamepad=gamepad1;
        telemetry=telemetry;



    }

    public void update(){
        if (gamepad.a){
            bench.setServoRotation(180.0);
        }else if (gamepad.b){
            bench.setServoRotation(-270.0);
        }






        telemetry.addData("Data", bench.getServoRotation());
    }
}
