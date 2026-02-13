package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.Hood;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.mechanisms.Storage;

import java.util.function.Supplier;

@Configurable
@TeleOp
public class RedSideTeleOp extends OpMode {
    private Follower follower;
    public static Pose startingPose; //See ExampleAuto to understand how to use this
    private boolean automatedDrive;
    private Supplier<PathChain> pathChain;
    private TelemetryManager telemetryM;
    private boolean slowMode = false;
    private double slowModeMultiplier = 0.5;

    private double strafe;
    private double forward;
    private double rotate;

    private double dpad_translation;
    private double dpad_rotation;

    private final Shooter shooter = new Shooter();
    private final Intake intake = new Intake();

    private final Hood hood = new Hood();
    private final Storage storage = new Storage();




    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();
        telemetryM = PanelsTelemetry.INSTANCE.getTelemetry();

        pathChain = () -> follower.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(follower::getPose, new Pose(45, 98))))
                .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(follower::getHeading, Math.toRadians(45), 0.8))
                .build();

        shooter.init(hardwareMap, gamepad2);
        intake.init(hardwareMap, gamepad2, telemetry);
        hood.init(hardwareMap, gamepad2, telemetry);
        storage.init(hardwareMap, gamepad2, telemetry);
        dpad_translation = 0.0;
        dpad_rotation = 0.0;





    }

    @Override
    public void start() {
        //The parameter controls whether the Follower should use break mode on the motors (using it is recommended).
        //In order to use float mode, add .useBrakeModeInTeleOp(true); to your Drivetrain Constants in Constant.java (for Mecanum)
        //If you don't pass anything in, it uses the default (false)
        //Good afternoon everyone. Cell phones, earbuds, and Chromebooks have become a significant
        //distraction in my classroom as of late. At the beginning of the year, I voiced my position
        // of the matter, and was under the impression that you understood my expectation. My approach
        //as of lately has been to point out my frustration in hopes that you would realize that
        //continuing to disregard classroom rules would end with more direct measures.
        //My experiments have failed.
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {
        storage.update();

//        //Call this once per loop
        follower.update();
        telemetryM.update();
        shooter.update();
        intake.update();
        hood.update();

        if (gamepad1.dpad_up){
            dpad_translation = 0.3;
        }else if (gamepad1.dpad_down){
            dpad_translation = -0.3;
        }else{
            dpad_translation=0.0;
        }

        if (gamepad1.dpad_right){
            dpad_rotation = 0.1;
        }else if (gamepad1.dpad_left){
            dpad_rotation = -0.1;
        }else{
            dpad_rotation=0.0;
        }

        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x ;
        rotate = -gamepad1.right_stick_x;



            //Make the last parameter false for field-centric
            //In case the drivers want to use a "slowMode" you can scale the vectors

            //This is the normal version to use in the TeleOp
            if (!slowMode) follower.setTeleOpDrive(
                    forward,
                    strafe,
                    rotate,
                    false // Robot Centric
            );

                //This is how it looks with slowMode on
            else follower.setTeleOpDrive(
                    forward * 0.3,
                    strafe * 0.3,
                    rotate * 0.5,
                    false // Robot Centric
            );
//
//
//
//
//        //Slow Mode
//        if (gamepad1.rightBumperWasPressed()) {
//            slowMode = !slowMode;
//        }
//
//        //Optional way to change slow mode strength
//        if (gamepad1.xWasPressed()) {
//            slowModeMultiplier += 0.25;
//        }
//
//        //Optional way to change slow mode strength
//        if (gamepad1.yWasPressed()) {
//            slowModeMultiplier -= 0.25;
//        }
//
//
        telemetryM.debug("position", follower.getPose());
        telemetryM.debug("velocity", follower.getVelocity());
        telemetryM.debug("automatedDrive", automatedDrive);
        telemetry.addData("Slowmode", slowMode);
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", 180*follower.getPose().getHeading()/3.14159265358979);
    }
}
