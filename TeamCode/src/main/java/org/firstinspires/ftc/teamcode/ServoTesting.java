package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.mechanisms.ServoMechanics;

@TeleOp
public class ServoTesting extends OpMode {

    ServoMechanics bench = new ServoMechanics();
    private double angle;



    @Override
    public void init() {

        bench.init(hardwareMap);
        angle = -1.0;



    }

    @Override
    public void loop() {
            if (gamepad1.a){
                bench.setServoRotation(1.0);
            }else if (gamepad1.b){
                bench.setServoRotation(-1.0);
            }






        telemetry.addData("Data", bench.getServoRotation());

    }

}
