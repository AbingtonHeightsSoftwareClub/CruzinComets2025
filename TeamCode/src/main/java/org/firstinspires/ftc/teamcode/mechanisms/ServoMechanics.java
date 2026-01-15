package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoMechanics {
    private Servo servoRotation;
    private Servo extendableRod;

    public void init(HardwareMap hardwareMap) {
        servoRotation = hardwareMap.get(Servo.class, "Servo");
    }

    public Servo getRotationObject() { return servoRotation; }


    public double setServoRotation(double power) {
        double previousPower = servoRotation.getPosition();
        servoRotation.setPosition(power);

        return previousPower;
    }


    public double getServoRotation() {
        return servoRotation.getPosition();
    }
}