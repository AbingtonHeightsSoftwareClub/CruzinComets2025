package org.firstinspires.ftc.teamcode;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Constants;

@TeleOp
public class pedroPathTest extends OpMode {
    private Follower follower;
    private Timer pathTimer, opModeTimer;

    public enum PathState {
        // START POSITION_END POSITION
        // DRIVE > MOVEMENT STATE
        // SHOOT > ATTEMPT TO SCORE THE ARTIFACT
        DRIVE_START_SHOOT,
        SHOOT_PRELOAD,
        DRIVE_SHOOT_END,

    }

    PathState pathState;

    private final Pose startPose = new Pose(0, 0, Math.toRadians(0));
    private final Pose shootPose = new Pose(20, 20, Math.toRadians(0));



    private PathChain driveStartShoot, driveShootEnd;

    public void buildPaths(){
        // put in coordinates for starting pose > ending pose
        driveStartShoot = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();


    }

    public void statePathUpdate(){
        switch(pathState){
            case DRIVE_START_SHOOT:
                follower.followPath(driveStartShoot, true);
                setPathState(PathState.SHOOT_PRELOAD); // Reset timer and make new state
                break;
            case SHOOT_PRELOAD:

                // check is follower done its path?
                if (!follower.isBusy() && pathTimer.getElapsedTimeSeconds()>5){
                    follower.followPath(driveShootEnd, true);
                    setPathState(PathState.DRIVE_SHOOT_END);
                    // transition to next state
                }
                break;

            case DRIVE_SHOOT_END:
                // All done
                if (!follower.isBusy()){
                    telemetry.addLine("Done all paths");
                }
                break;
            default:
                telemetry.addLine("No State Commanded");
        }
    }

    public void setPathState(PathState newState){
        pathState = newState;
        pathTimer.resetTimer();
    }

    @Override
    public void init(){

        pathState = PathState.DRIVE_START_SHOOT;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        // TODO add in any other init mechanisms
        buildPaths();
        follower.setPose(startPose);


    }
    @Override
    public void start(){
        opModeTimer.resetTimer();
        setPathState(pathState);
    }

    @Override
    public void loop(){
        follower.update();
        statePathUpdate();

        telemetry.addData("Path State", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("Path Time", pathTimer.getElapsedTimeSeconds());
    }

}
