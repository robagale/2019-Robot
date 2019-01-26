package org.usfirst.frc.team6500.robot;

import edu.wpi.first.wpilibj.Servo;

public class Ramps
{
    static Servo leftServo, rightServo;

    public static boolean initilizeRamps()
    {
        leftServo = new Servo(Constants.RAMP_LEFT_SERVO_CHANNEL);
        rightServo = new Servo(Constants.RAMP_RIGHT_SERVO_CHANNEL);
        return true;
    }

    public static void releaseRamps()
    {
        // TODO: find out minimum angle to open servos
        leftServo.set(Constants.RAMP_LEFT_SERVO_ANGLE);
        rightServo.set(Constants.RAMP_RIGHT_SERVO_ANGLE);
    }
}

/*
 *  THE END!
 */
