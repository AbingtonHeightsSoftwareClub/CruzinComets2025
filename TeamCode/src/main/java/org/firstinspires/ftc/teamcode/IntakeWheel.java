/*
Copyright 2025 FIRST Tech Challenge Team 7055

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.mechanisms.Constants;

/**
 * This file contains a minimal example of an iterative (Non-Linear) "OpMode". An OpMode is a
 * 'program' that runs in either the autonomous or the TeleOp period of an FTC match. The names
 * of OpModes appear on the menu of the FTC Driver Station. When an selection is made from the
 * menu, the corresponding OpMode class is instantiated on the Robot Controller and executed.
 *
 * Remove the @Disabled annotation on the next line or two (if present) to add this OpMode to the
 * Driver Station OpMode list, or add a @Disabled annotation to prevent this OpMode from being
 * added to the Driver Station.
 */

@TeleOp

public class IntakeWheel extends OpMode {
    private DcMotorEx intake;
    private boolean intake_on;
    private Gamepad gamepad;
    private boolean direction;
    private boolean left_bumper_Pressed_LastCycle;


    @Override
    public void init() {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        gamepad=gamepad1;
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        // Reset the motor encoder so it reads 0 ticks
        intake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        left_bumper_Pressed_LastCycle=false;
        direction = true;

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

        //Heh.... SIX SEVEN

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {



    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {


        if (gamepad.dpadUpWasPressed()){
            intake_on= !intake_on;
        }

        if (gamepad.left_bumper && !left_bumper_Pressed_LastCycle){
            if (direction){
                direction=false;
                intake.setDirection(DcMotorSimple.Direction.FORWARD);
            }else{
                direction=true;
                intake.setDirection(DcMotorSimple.Direction.REVERSE);
            }
        }

        if (intake_on){
            intake.setVelocity(2800.0);

        }else{
            intake.setVelocity(0.0);
        }

        left_bumper_Pressed_LastCycle=gamepad.right_bumper;



    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }
}
