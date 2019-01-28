package org.usfirst.frc.team6500.robot;

import org.usfirst.frc.team6500.trc.sensors.TRCNetworkVision;
import org.usfirst.frc.team6500.trc.wrappers.systems.drives.TRCMecanumDrive;

public class AutoAlign
{
    private static TRCMecanumDrive drive;


    public static void setDrive(TRCMecanumDrive robotDrive)
    {
        drive = robotDrive;
    }

    public static void alignWithFloorTape()
    {
        while (TRCNetworkVision.getContourX() != 0)
        {
            drive.driveCartesian(0.0, 0.0, 0.0);
        }
    }
}
//hello