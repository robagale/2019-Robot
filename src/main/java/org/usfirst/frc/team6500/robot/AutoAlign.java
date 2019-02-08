package org.usfirst.frc.team6500.robot;

import org.usfirst.frc.team6500.trc.util.TRCNetworkData;
import org.usfirst.frc.team6500.trc.wrappers.systems.drives.TRCMecanumDrive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.AnalogInput;

public class AutoAlign
{
    private static TRCMecanumDrive drive;
    private static AnalogInput leftProx;
    private static AnalogInput rightProx;
    private static NetworkTable visionTable = null;

    public static void setupAlignment(TRCMecanumDrive robotDrive, AnalogInput leftSensor, AnalogInput rightSensor)
    {
        drive = robotDrive;
        leftProx = leftSensor;
        rightProx = rightSensor;
        visionTable = TRCNetworkData.getVisionTable();
    }

    public static void alignWithVisionTargets()
    {
        boolean running = true;

        while (running)
        {
            double distance = visionTable.getEntry("distance").getDouble(0.0);
            double angle = visionTable.getEntry("angle").getDouble(0.0);


        }
    }

    public static void alignWithFloorTape()
    {
        boolean running = true;

        while (running)
        {
            String instruction = visionTable.getEntry("instruction").getString("");

            if (instruction.equals("MF"))      // Move Forward
            {
                drive.driveCartesian(0.4, 0.0, 0.0);
            }
            else if (instruction.equals("ML")) // Move Left
            {
                drive.driveCartesian(0.0, -0.4, 0.0);
            }
            else if (instruction.equals("MR")) // Move Right
            {
                drive.driveCartesian(0.0, 0.4, 0.0);
            }

            else if (instruction.equals("TL")) // Turn Left
            {
                drive.driveCartesian(0.0, 0.0, -0.4);
            }
            else if (instruction.equals("TR")) // Turn Right
            {
                drive.driveCartesian(0.0, 0.0, 0.4);
            }
        }
    }
}