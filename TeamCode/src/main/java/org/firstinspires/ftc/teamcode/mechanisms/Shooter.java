package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.util.Timer;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotorEx shooter;


    private Timer opmodeTimer;


    private Gamepad gamepad;


    public void init(HardwareMap hwMap, Gamepad gamepad1) {
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();
        shooter = hwMap.get(DcMotorEx.class, "shooter");
        gamepad = gamepad1;
        shooter.setDirection(DcMotorEx.Direction.REVERSE);
        shooter.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

    }

    public void update() {


        if (gamepad.right_trigger > 0.25) {
            shooter.setVelocity(1800.0);

        } else if(gamepad.right_bumper){
            shooter.setVelocity(1300.0);
        }

        else {
            shooter.setPower(0);
        }

    }

    public void shoot(){
        opmodeTimer.resetTimer();
        if (opmodeTimer.getElapsedTimeSeconds() > 2 && opmodeTimer.getElapsedTimeSeconds() < 4){
            shooter.setVelocity(2100.00);
        }
        else{
            shooter.setVelocity(0.0);
        }
    }

}
