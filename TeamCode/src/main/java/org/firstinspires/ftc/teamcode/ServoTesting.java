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
    private ServoImplEx extendableRod;

    @Override
    public void init() {

        extendableRod=hardwareMap.get(ServoImplEx.class, "linear");
        extendableRod.setPwmEnable();

    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            extendableRod.setPwmRange(new PwmControl.PwmRange(0.5, 0.6));

        }



        telemetry.addData("Data", extendableRod.getPwmRange());

    }

}
