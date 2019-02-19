package org.usfirst.frc.team6500.robot;


import org.usfirst.frc.team6500.trc.systems.TRCDirectionalSystem;
import org.usfirst.frc.team6500.trc.util.TRCTypes.SpeedControllerType;
import org.usfirst.frc.team6500.trc.wrappers.sensors.TRCTalonEncoder;


public class Arm extends TRCDirectionalSystem
{
    private static boolean isReady = false;
    public static TRCTalonEncoder encoder;

    public Arm (int[] motorPorts, SpeedControllerType[] motorType)
    {
        super(motorPorts, motorType, true, Constants.ARM_SPEED_UP, Constants.ARM_SPEED_DOWN);
        encoder = new TRCTalonEncoder(Constants.ARM_MOTOR, Constants.ARM_DISTANCE_PER_PULSE, false);

        isReady = true;
    }


    public void armToUp()
    {
        if (!isReady) { return; }

        encoder.reset();
        while (encoder.getDistance() < Constants.ARM_POSITION_UP)
        {
            this.driveForward();
        }

        this.fullStop();
    }

    public void armToDown()
    {
        if (!isReady) { return; }

        encoder.reset();
        while (encoder.getDistance() > Constants.ARM_POSITION_DOWN)
        {
            this.driveReverse();
        }
    }
}
