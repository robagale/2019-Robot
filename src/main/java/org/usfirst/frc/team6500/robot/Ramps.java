package org.usfirst.frc.team6500.robot;

import edu.wpi.first.wpilibj.Servo;

public class Ramps
{
    static Servo servo;

    public static boolean initilizeRampsOnChannel(int channel)
    {
        servo = new Servo(channel); // TODO: set a definite channel
        return true;
    }

    public static void releaseRamps()
    {
        servo.setAngle(1); // TODO: find out minimum angle to open servo
    }
}
