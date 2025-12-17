package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;


@TeleOp
public class mecanum_drive extends OpMode {

    org.firstinspires.ftc.teamcode.GoBildaPinpointDriver odo;

    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;



    @Override
    public void init() {

        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_drive");

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        odo = hardwareMap.get(GoBildaPinpointDriver.class,"pinpoint");

        odo.setEncoderResolution(org.firstinspires.ftc.teamcode.GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setOffsets((11.5-20.75)/2.54, -(1/2.54)*(3.6+17), DistanceUnit.CM);
        odo.setEncoderDirections(org.firstinspires.ftc.teamcode.GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);


        odo.resetPosAndIMU();

        telemetry.addData("Status", "Initialized");
        telemetry.addData("X offset", odo.getXOffset(DistanceUnit.CM));
        telemetry.addData("Y offset", odo.getYOffset(DistanceUnit.CM));
        telemetry.addData("Device Version", odo.getDeviceVersion());
        telemetry.addData("Device Scalar", odo.getYawScalar());
        telemetry.update();
    }

    public void moveRobot(){

        double forward   = -gamepad1.right_stick_y;  // Note: pushing stick forward gives negative value
        if (gamepad1.dpad_up) {
            forward = 1.0;
        }

        else if (gamepad1.dpad_down) {
            forward = -1.0;
        }
        double strafe =  gamepad1.right_stick_x;
        double rotate = (gamepad1.dpad_right ? 1.0 : 0.0)  - (gamepad1.dpad_left ? 1.0 : 0.0);

        telemetry.addData("Forward: ", forward);
        telemetry.addData("Strafe", strafe);
        telemetry.addData("Rotate", rotate);
        telemetry.update();
//        double forward = -gamepad1.left_stick_y;
//        double strafe = gamepad1.left_stick_x;
//        double rotate = gamepad1.right_stick_y;

        Pose2D pos = odo.getPosition();
        double heading = pos.getHeading(AngleUnit.RADIANS);

        double cosAngle = Math.cos((Math.PI/2)-heading);
        double sinAngle = Math.sin((Math.PI/2)-heading);

        double globalStrafe = -forward*sinAngle + strafe*cosAngle;
        double globalForward = forward*cosAngle + strafe*sinAngle;

        double[] newWheelSpeeds = new double[4];

        newWheelSpeeds[0] = globalForward + globalStrafe + rotate;
        newWheelSpeeds[1] = globalForward - globalStrafe - rotate;
        newWheelSpeeds[2] = globalForward - globalStrafe + rotate;
        newWheelSpeeds[3] = globalForward + globalStrafe - rotate;

        frontLeftMotor.setPower(newWheelSpeeds[0]);
        frontRightMotor.setPower(newWheelSpeeds[1]);
        backLeftMotor.setPower(newWheelSpeeds[2]);
        backRightMotor.setPower(newWheelSpeeds[3]);


    }

    @Override
    public void loop(){

    moveRobot();
    Pose2D pos = odo.getPosition();

    telemetry.addData("Robot X: ", odo.getPosX(DistanceUnit.CM));
    telemetry.addData("Robot Y: ", odo.getPosY(DistanceUnit.CM));
    telemetry.addData("Robot Heading: ", pos.getHeading(AngleUnit.DEGREES));
    telemetry.update();

    odo.update();






    }

}
