package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import java.util.ArrayList;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;


@TeleOp
public class mecanum_drive extends OpMode {

    private MecanumDrive drive = new MecanumDrive();


    @Override
    public void init() {
        drive.init(hardwareMap, telemetry);
    }

    @Override
    public void loop(){
        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double forward   = -gamepad1.right_stick_y;  // Note: pushing stick forward gives negative value
        if (gamepad1.dpad_up) {
            forward = 1.0;
        }

        else if (gamepad1.dpad_down) {
            forward = -1.0;
        }
        double strafe =  gamepad1.right_stick_x;
        double rotation = (gamepad1.dpad_right ? 1.0 : 0.0)  - (gamepad1.dpad_left ? 1.0 : 0.0);



        drive.drive(forward, strafe, rotation);
        telemetry.addData("Forward", forward);
        telemetry.addData("Strafe", strafe);
        telemetry.addData("Rotation", rotation);
    }
}
