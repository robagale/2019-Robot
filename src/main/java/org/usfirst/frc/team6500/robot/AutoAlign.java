package org.usfirst.frc.team6500.robot;

import org.usfirst.frc.team6500.trc.sensors.TRCNetworkVision;
import org.usfirst.frc.team6500.trc.util.TRCNetworkData;
import org.usfirst.frc.team6500.trc.wrappers.systems.drives.TRCMecanumDrive;

import edu.wpi.first.networktables.NetworkTable;

public class AutoAlign
{
    private static TRCMecanumDrive drive;
    private static NetworkTable visionTable = null;

    public static void setDrive(TRCMecanumDrive robotDrive)
    {
        drive = robotDrive;
        visionTable = TRCNetworkData.getVisionTable();
    }

    public static void alignWithFloorTape()
    {
        boolean running = true;

        while (running)
        {
            double distance = visionTable.getEntry("distance").getDouble(0.0);
            double angle = visionTable.getEntry("angle").getDouble(0.0);


        }
    }

    public static void alignWithVisionTargets()
    {
        boolean running = true;

        while (running)
        {
            String instruction = visionTable.getEntry("instruction").getString("");

            if (instruction.equals("MF"))      // Move Forward
            {

            }
            else if (instruction.equals("ML")) // Move Left
            {

            }
            else if (instruction.equals("MR")) // Move Right
            {

            }

            else if (instruction.equals("TL")) // Turn Left
            {

            }
            else if (instruction.equals("TR")) // Turn Right
            {

            }
        }
    }
}