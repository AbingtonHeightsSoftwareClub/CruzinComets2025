package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TuffAhhServos {
    private CRServo servoRotation;
    private Servo extendableRod;

    public void init(HardwareMap hardwareMap) {
        servoRotation = hardwareMap.get(CRServo.class, "TuffServoOne");
        extendableRod = hardwareMap.get(Servo.class, "TuffLinearServo");
    }

    public CRServo getWheelThing() { return servoRotation; }
    public Servo getExtendableRod() { return extendableRod; }

    public double setServoRotation(double power) {
        double previousPower = servoRotation.getPower();
        servoRotation.setPower(power);
        return previousPower;
    }

    public double extendRod(double size) {
        double previousLength = extendableRod.getPosition();
        extendableRod.setPosition(size);
        return previousLength;
    }

    public double getServoRotation() {
        return servoRotation.getPower();
    }
}
