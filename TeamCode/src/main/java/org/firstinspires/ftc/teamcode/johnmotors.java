package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import java.util.ArrayList;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

@TeleOp
public class johnmotors extends LinearOpMode {
 // Declare OpMode members for each of the 4 motors.
 private ElapsedTime runtime = new ElapsedTime();
 private DcMotor frontLeftDrive = null;
 private DcMotor backLeftDrive = null;
 private DcMotor frontRightDrive = null;
 private DcMotor backRightDrive = null;
 private MecanumDrive drive = new MecanumDrive();




 @Override
 public void runOpMode() {
//
//     drive.init(hardwareMap, telemetry);
    
    
     // Initialize the hardware variables. Note that the strings used here must correspond
     // to the names assigned during the robot configuration step on the DS or RC devices.
     frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
     backLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
     frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
     backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");

     // ########################################################################################
     // !!!            IMPORTANT Drive Information. Test your motor directions.            !!!!!
     // ########################################################################################
     // Most robots need the motors on one side to be reversed to drive forward.
     // The motor reversals shown here are for a "direct drive" robot (the wheels turn the same direction as the motor shaft)
     // If your robot has additional gear reductions or uses a right-angled drive, it's important to ensure
     // that your motors are turning in the correct direction.  So, start out with the reversals here, BUT
     // when you first test your robot, push the left joystick forward and observe the direction the wheels turn.
     // Reverse the direction (flip FORWARD <-> REVERSE ) of any wheel that runs backward
     // Keep testing until ALL the wheels move the robot forward when you push the left joystick forward.


     frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
      backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
      frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
      backRightDrive.setDirection(DcMotor.Direction.REVERSE);

     // Wait for the game to start (driver presses START)
     telemetry.addData("Status", "Initialized");
     telemetry.update();

     waitForStart();
     runtime.reset();

     // run until the end of the match (driver presses STOP)
     while (opModeIsActive()) {
         double max;

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
         
//         drive.driveFieldRelative(forward, strafe, rotation);
         // ArrayList<Double> new_numbers = driveFieldRelative(forward, strafe, rotation);
         // forward = new_numbers.get(0);
         // strafe = new_numbers.get(1);

         // rotation = new_numbers.get(2);
         // Combine the joystick requests for each axis-motion to determine each wheel's power.
         // Set up a variable for each drive wheel to save the power level for telemetry.

         double frontLeftPower  = forward + strafe + rotation;
         double frontRightPower = forward - strafe - rotation;
         double backLeftPower   = forward - strafe + rotation;
         double backRightPower  = forward + strafe - rotation;

         // Normalize the values so no wheel power exceeds 100%
         // This ensures that the robot maintains the desired motion.
         max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
         max = Math.max(max, Math.abs(backLeftPower));
         max = Math.max(max, Math.abs(backRightPower));

         if (max > 1.0) {
             frontLeftPower  /= max;
             frontRightPower /= max;
             backLeftPower   /= max;
             backRightPower  /= max;
         }
         
         double speed = 0.5;
         if (gamepad1.right_trigger > 0.1){
             speed = 1.0;
         }
         frontLeftPower *= speed;
         frontRightPower *= speed;
         backLeftPower *= speed;
         backRightPower *= speed;
         // This is test code:
         //
         // Uncomment the following code to test your motor directions.
         // Each button should make the corresponding motor run FORWARD.
         //   1) First get all the motors to take to correct positions on the robot
         //      by adjusting your Robot Configuration if necessary.
         //   2) Then make sure they run in the correct direction by modifying the
         //      the setDirection() calls above.
         // Once the correct motors move in the correct direction re-comment this code.


         // Send calculated power to wheels
         frontLeftDrive.setPower(frontLeftPower);
         frontRightDrive.setPower(frontRightPower);
         backLeftDrive.setPower(backLeftPower);
         backRightDrive.setPower(backRightPower);

         // Wheel testing because they suck

         if (gamepad1.a) {
             backRightDrive.setPower(1);
         }

         if (gamepad1.b) {
             frontRightDrive.setPower(1);
         }

         if (gamepad1.y) {
             frontLeftDrive.setPower(1);
         }

         if (gamepad1.x) {
             backLeftDrive.setPower(1);
         }

         // Show the elapsed game time and wheel power.
         telemetry.addData("Front Left / Right", "%4.2f, %4.2f", frontLeftPower, frontRightPower);
         telemetry.addData("Back  Left / Right", "%4.2f, %4.2f", backLeftPower, backRightPower);
         telemetry.addData("Ts robot so tuff boiiii", "Running for " + runtime.toString());

         telemetry.update();
         
         
     }
 }
}
