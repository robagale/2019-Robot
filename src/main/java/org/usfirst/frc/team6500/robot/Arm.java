package org.usfirst.frc.team6500.robot;

import edu.wpi.first.wpilibj.Spark;

public class Arm
{
    static Spark arm;
    
    public static void intializeGrabber()
	{
		
		arm = new Spark(0);
		
    }
    
    // TODO: LIft arm
}