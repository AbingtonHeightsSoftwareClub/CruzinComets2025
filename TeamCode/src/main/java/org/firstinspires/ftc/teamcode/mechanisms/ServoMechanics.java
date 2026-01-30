package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoMechanics {
    private Servo servoRotation;


    public void init(HardwareMap hardwareMap, String name) {
        servoRotation = hardwareMap.get(Servo.class, name);
    }

    public Servo getRotationObject() { return servoRotation; }


    public void setServoRotation(double angle) {
        // 0 to 290
        // -1 to 1
        if (angle>290.0){
            angle=290.0;
        }else if (angle<0.0){
            angle=0.0;
        }
        double power = angle/290.0;


        servoRotation.setPosition(power);


    }


    public double getServoRotation() {
        return servoRotation.getPosition();
    }
}