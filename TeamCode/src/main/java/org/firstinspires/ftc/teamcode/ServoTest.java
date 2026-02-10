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
    private CRServo test_servo;


    @Override
    public void init() {

        test_servo = hardwareMap.get(CRServo.class, "test_servo");

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        test_servo.setPower(1.0);

    }
}
