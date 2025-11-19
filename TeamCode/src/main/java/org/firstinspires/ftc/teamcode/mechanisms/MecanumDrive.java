package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;

import java.util.Arrays;
import java.util.Collections;

public class MecanumDrive {
    private DcMotorEx frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private IMU imu;
    private GoBildaPinpointDriver odo; // Declare OpMode member for the Odometry Computer


    public void init(HardwareMap hwMap, Telemetry telemetry) {
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
        odo = hwMap.get(GoBildaPinpointDriver.class,"imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );


        /*
        Set the odometry pod positions relative to the point that the odometry computer tracks around.
        The X pod offset refers to how far sideways from the tracking point the
        X (forward) odometry pod is. Left of the center is a positive number,
        right of center is a negative number. the Y pod offset refers to how far forwards from
        the tracking point the Y (strafe) odometry pod is. forward of center is a positive number,
        backwards is a negative number.
         */
//        odo.setOffsets(-84.0, -168.0, DistanceUnit.MM); //these are tuned for 3110-0002-0001 Product Insight #1

        //TODO Set offsets. Must measure relative position of computer
        /*
        Set the kind of pods used by your robot. If you're using goBILDA odometry pods, select either
        the goBILDA_SWINGARM_POD, or the goBILDA_4_BAR_POD.
        If you're using another kind of odometry pod, uncomment setEncoderResolution and input the
        number of ticks per unit of your odometry pod.
         */
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

        /*
        Set the direction that each of the two odometry pods count. The X (forward) pod should
        increase when you move the robot forward. And the Y (strafe) pod should increase when
        you move the robot to the left.
         */
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();

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
        odo.update();

        // Converting from cartesian coordinates (x and y axis) to polar coordinates (A distance and angle, like in radar)
        double theta = Math.atan2(forward, strafe); // The angle of the direction we want to move in
        double radius = Math.hypot(strafe, forward); // How far we want to move
        // Seamus why are we calling ts the radius ts is litterally a line.
        // Seamus what the HELLL is our unit of measurement?!?!
        // Js guessing 
        Pose2D pos = odo.getPosition();
        theta = AngleUnit.normalizeRadians(theta -
                pos.getHeading(AngleUnit.RADIANS)); // Adjusting for the angle that the robot is already facing

        double newForward = radius * Math.sin(theta);
        double newStrafe = radius * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);

        


    }
}
