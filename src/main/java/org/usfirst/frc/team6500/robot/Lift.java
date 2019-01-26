package org.usfirst.frc.team6500.robot;

import edu.wpi.first.wpilibj.Spark;

public class Lift {
    static Spark left, right;
   private static boolean isReady;

public static void initializeLift () {
    left = new Spark(Constants.LIFT_MOTOR_LEFT);
    right = new Spark(Constants.LIFT_MOTOR_RIGHT);

    isReady = true;

}
}