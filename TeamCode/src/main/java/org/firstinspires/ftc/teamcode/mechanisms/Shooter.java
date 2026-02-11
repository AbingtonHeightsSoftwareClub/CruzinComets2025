package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotorEx shooter;
    private boolean shooter_on = false;


    private Gamepad gamepad;
    private boolean right_trigger_Pressed_LastCycle;

    public void init(HardwareMap hwMap, Gamepad gamepad1) {
        shooter = hwMap.get(DcMotorEx.class, "shooter");
        gamepad = gamepad1;
        shooter.setDirection(DcMotorEx.Direction.REVERSE);
        shooter.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        right_trigger_Pressed_LastCycle = false;
    }

    public void update() {
        if (gamepad.right_trigger > 0.25 && !right_trigger_Pressed_LastCycle) {
            shooter_on = !shooter_on;
        }

        if (shooter_on) {
            shooter.setVelocity(2800.0);

        } else {
            shooter.setPower(0);
        }
        right_trigger_Pressed_LastCycle = gamepad.right_trigger > 0.25;
    }

}
