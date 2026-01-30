package org.firstinspires.ftc.teamcode;

import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.mechanisms.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.ServoMechanics;

@TeleOp
public class ServoTest extends OpMode {
    private ServoMechanics servo = new ServoMechanics();


    @Override
    public void init() {

        servo.init(hardwareMap, "spoon");
        servo.setServoRotation(290.0);

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if (gamepad1.bWasPressed()){
            servo.setServoRotation(240.0);
        } else if (gamepad1.xWasPressed()){
            servo.setServoRotation(290.0);
        }
        telemetry.addData("Angle", servo.getServoRotation());

    }
}
