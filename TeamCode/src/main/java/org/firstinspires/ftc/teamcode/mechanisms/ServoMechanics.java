package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoMechanics {
    private CRServo servoRotation;


    public void init(HardwareMap hardwareMap, String name) {
        servoRotation = hardwareMap.get(CRServo.class, name);
    }

//    public Servo getRotationObject() { return servoRotation; }


    public void setServoRotation(double angle) {
        // 0 to 290
        // -1 to 1

        double power = angle/145.0-1.0;


        servoRotation.setPower(angle);

    }


    public double getServoRotation() {
        return servoRotation.getPower();
    }
}