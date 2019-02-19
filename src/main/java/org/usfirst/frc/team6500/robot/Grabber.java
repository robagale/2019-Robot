package org.usfirst.frc.team6500.robot;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team6500.trc.systems.TRCDirectionalSystem;
import org.usfirst.frc.team6500.trc.util.TRCTypes.SpeedControllerType;


public class Grabber extends TRCDirectionalSystem
{
    private static boolean isReady = false;
    private static int[] ports;

    public Grabber(int[] motorPorts, SpeedControllerType[] motorTypes)
    {
        super(motorPorts, motorTypes, true, Constants.GRABBER_SPEED_EXPEL, Constants.GRABBER_SPEED_INTAKE);

        ports = motorPorts.clone();
        isReady = true;
    }


    @Override
    public void driveReverse() // Intake
    {
        if (!isReady) { return; }

        long startTime = System.currentTimeMillis();
        while (!((WPI_TalonSRX) this.outputMotors.get(ports[0])).getSensorCollection().isRevLimitSwitchClosed())
        {
            this.driveReverse();

            if (System.currentTimeMillis() - startTime > 2000) // If we don't get a cargo after 2 seconds, give up
            {
                break;
            }
        }
    }
}