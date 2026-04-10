package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class Constants {
    public static final double MAX_POWER = 0.33;
    public static final double MAX_SPEED = 6.0*28.0;
    public static final double DEMONSTRATION_SPEED = 0.4;
    public static final double COUNTS_PER_MOTOR_REV = 28.0;
    public static final double DRIVE_GEAR_REDUCTION = 3.7;
    public static final double WHEEL_CIRCUMFERENCE_MM = 104 * 3.14;
    public static final double COUNTS_PER_WHEEL_REV = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;
    public static final double COUNTS_PER_MM = COUNTS_PER_MOTOR_REV / WHEEL_CIRCUMFERENCE_MM;

    public static FollowerConstants followerConstants = new FollowerConstants().mass(6)
            .forwardZeroPowerAcceleration(-35.0)
            .lateralZeroPowerAcceleration(-53.0)
            .headingPIDFCoefficients(new PIDFCoefficients(2.0, 0.0, 0.1, 0.032))
            .translationalPIDFCoefficients(new PIDFCoefficients(0.3, 0, 0.005, 0.022));

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1.5, 1);


    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("front_right_drive")
            .rightRearMotorName("back_right_drive")
            .leftRearMotorName("back_left_drive")
            .leftFrontMotorName("front_left_drive")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(67.0)
            .yVelocity(58.9);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-(1/2.54)*(3.6+17))
            .strafePodX((11.5-20.75)/2.54)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("imu")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);



    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }


}
