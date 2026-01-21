package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotorEx shooter;
    private boolean shooter_on = false;
    boolean right_bumper_Pressed_LastCycle = false; // Tracks the button's previous state
    private Gamepad gamepad;

    public void init(HardwareMap hwMap, Gamepad gamepad1){
        shooter = hwMap.get(DcMotorEx.class, "shooter");
        gamepad=gamepad1;

        shooter.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

    }

    public void update(){
        if (gamepad.right_bumper && !right_bumper_Pressed_LastCycle){
            shooter_on = !shooter_on;
        }

        if (shooter_on){
            shooter.setVelocity(2800.0);

        }else{
            shooter.setPower(0);
        }
        right_bumper_Pressed_LastCycle=gamepad.right_bumper;
    }

}
