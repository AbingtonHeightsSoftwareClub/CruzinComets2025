package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.Arrays;
import java.util.Collections;

public class MecanumDrive {
    private DcMotorEx frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private IMU imu;


    public void init(HardwareMap hwMap) {
        frontLeftMotor = hwMap.get(DcMotorEx.class, "front_left_drive");
        frontRightMotor = hwMap.get(DcMotorEx.class, "front_right_drive");
        backLeftMotor = hwMap.get(DcMotorEx.class, "back_left_drive");
        backRightMotor = hwMap.get(DcMotorEx.class, "back_right_drive");
        
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
 

        frontLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        

        // Default is logo facing up and USB ports facing forward on Robot Controller
        imu = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );

        imu.initialize(new IMU.Parameters(RevOrientation));

    }

    public void drive(double forward, double strafe, double rotate) {
        // To learn about mecanum drive, go to this video https://www.youtube.com/watch?v=0k-Ey9bS9lE
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = Collections.max(Arrays.asList(Constants.MAX_POWER, frontLeftPower, backLeftPower, frontRightPower, backRightPower));
        frontLeftMotor.setVelocity(1.0);
        frontRightMotor.setVelocity(Constants.MAX_SPEED * ( frontRightPower/ maxPower));
        backLeftMotor.setVelocity(Constants.MAX_SPEED * (backLeftPower / maxPower));
        backRightMotor.setVelocity(Constants.MAX_SPEED * (backRightPower / maxPower));


    }

    public void driveFieldRelative(double forward, double strafe, double rotate){
        // Converting from cartesian coordinates (x and y axis) to polar coordinates (A distance and angle, like in radar)
        double theta = Math.atan2(forward, strafe); // The angle of the direction we want to move in
        double radius = Math.hypot(strafe, forward); // How far we want to move
        // Seamus why are we calling ts the radius ts is litterally a line.
        // Seamus what the HELLL is our unit of measurement?!?!
        // Js guessing 

        theta = AngleUnit.normalizeRadians(theta -
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)); // Adjusting for the angle that the robot is already facing

        double newForward = radius * Math.sin(theta);
        double newStrafe = radius * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);

        


    }
}
