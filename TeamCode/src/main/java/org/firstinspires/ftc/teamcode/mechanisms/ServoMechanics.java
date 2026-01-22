package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoMechanics {
    private Servo servoRotation;


    public void init(HardwareMap hardwareMap) {
        servoRotation = hardwareMap.get(Servo.class, "storage_servo");
    }

    public Servo getRotationObject() { return servoRotation; }


    public void setServoRotation(double angle) {
        // 0 to 290
        // -1 to 1

        double power = angle/145.0-1.0;


        servoRotation.setPosition(power);

    }


    public double getServoRotation() {
        return servoRotation.getPosition();
    }
}