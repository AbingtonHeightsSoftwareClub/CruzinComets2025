package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class PIDTuning extends OpMode {
    private DcMotorEx shooter;
    private boolean shooter_on = false;
    boolean right_bumper_Pressed_LastCycle = false; // Tracks the button's previous state
    private Gamepad gamepad;
    private double p, i, d, f;
    private double order_of_magnitude;
    @Override
    public void init(){
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        gamepad=gamepad1;
        shooter.setDirection(DcMotorEx.Direction.REVERSE);
        shooter.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        p=0.0;
        d=0.0;
        f=0.0;
        order_of_magnitude = 0.1;

    }

    @Override
    public void loop(){
        if (gamepad.right_bumper && !right_bumper_Pressed_LastCycle){
            shooter_on = !shooter_on;
        }

        if (shooter_on){
            shooter.setVelocity(3500.0);

        }else{
            shooter.setPower(0);
        }
        right_bumper_Pressed_LastCycle=gamepad.right_bumper;


        if (gamepad1.yWasPressed()){
            p+=order_of_magnitude;
        }
        else if (gamepad1.bWasPressed()){
            d+=order_of_magnitude;
        } else if (gamepad1.aWasPressed()){
            f+=order_of_magnitude;
        }

        if (gamepad1.dpadUpWasPressed()){
            order_of_magnitude*=10;
        }else if (gamepad1.dpadDownWasPressed()){
            order_of_magnitude/=10;
        }

        if (gamepad1.xWasPressed()){
            order_of_magnitude*=-1;
        }


        shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(p, 0.0, d, f));
        telemetry.addData("P", p);
        telemetry.addData("D", d);
        telemetry.addData("F", f);
        telemetry.addData("Order Of Magnitude", order_of_magnitude);
        telemetry.update();
    }
}
