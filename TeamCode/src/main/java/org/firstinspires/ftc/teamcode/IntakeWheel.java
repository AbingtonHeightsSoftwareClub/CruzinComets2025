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
    /* Declare OpMode members. */
    
    DcMotorEx intakeWheel;
    DcMotor hoodWheel;

    DcMotor brushWheel;
    double TPS;
    int wheeltarget;
    double  max_speed;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        intakeWheel = hardwareMap.get(DcMotorEx.class, "intake_wheel");
        hoodWheel = hardwareMap.get(DcMotor.class, "hood");
        brushWheel = hardwareMap.get(DcMotor.class, "brush");


        intakeWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        wheeltarget = (int) (610 * Constants.COUNTS_PER_MM);


        TPS = (175 / 60) * Constants.COUNTS_PER_MM;
        max_speed = 2800.0 * TPS;

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


        intakeWheel.setTargetPosition(wheeltarget);

        intakeWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {


        if (gamepad1.y){
            intakeWheel.setVelocity(max_speed);
        }

        else if (gamepad1.b){
            intakeWheel.setVelocity(max_speed * 0.8);
        }

        else if (gamepad1.a){
            intakeWheel.setVelocity(max_speed * 0.7);
        }

        else if (gamepad1.x){
            intakeWheel.setVelocity(max_speed * 0.6);
        }

        else {
            intakeWheel.setVelocity(0.0);
        }

        if (gamepad1.dpad_up){
            hoodWheel.setPower(0.01);
        }

        else if (gamepad1.dpad_down){
            hoodWheel.setPower(-0.01);
        }

        else{
            hoodWheel.setPower(0);
        }

        if(gamepad1.right_trigger > 0) {
            brushWheel.setPower(1);
        }

        else {
            brushWheel.setPower(0);
        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        intakeWheel.setPower(0.0);
        brushWheel.setPower(0);
        hoodWheel.setPower(0);
    }
}
