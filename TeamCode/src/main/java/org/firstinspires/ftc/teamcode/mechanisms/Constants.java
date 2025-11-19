package org.firstinspires.ftc.teamcode.mechanisms;

public class Constants {
    public static final double MAX_POWER = 1.0;
    public static final double MAX_SPEED = 6.0*28.0;
    
    public static final double COUNTS_PER_MOTOR_REV = 28.0;
    public static final double DRIVE_GEAR_REDUCTION = 3.7;
    public static final double WHEEL_CIRCUMFERENCE_MM = 104 * 3.14;
    public static final double COUNTS_PER_WHEEL_REV = COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION;
    public static final double COUNTS_PER_MM = COUNTS_PER_MOTOR_REV / WHEEL_CIRCUMFERENCE_MM;
}
