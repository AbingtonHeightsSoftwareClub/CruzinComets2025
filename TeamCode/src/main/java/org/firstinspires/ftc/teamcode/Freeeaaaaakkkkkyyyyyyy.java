package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.TuffAhhServos;

@TeleOp
public class Freeeaaaaakkkkkyyyyyyy extends OpMode {

    TuffAhhServos bench = new TuffAhhServos();

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            bench.setServoRotation(bench.getServoRotation() + .1);
            bench.extendRod(1.0);
        }

        else {
            bench.setServoRotation(bench.getServoRotation());
        }

        if(gamepad1.b) {
            bench.setServoRotation(bench.getServoRotation() - .1);
        }

        else {
            bench.setServoRotation(bench.getServoRotation());
        }
    }

}
