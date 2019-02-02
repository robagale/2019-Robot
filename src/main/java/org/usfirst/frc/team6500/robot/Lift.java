package org.usfirst.frc.team6500.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team6500.trc.systems.TRCDirectionalSystem;
import org.usfirst.frc.team6500.trc.util.TRCTypes.SpeedControllerType;

import edu.wpi.first.wpilibj.Spark;

public class Lift extends TRCDirectionalSystem{
    static Spark left, right;
   private static boolean isReady;

   public Lift(int[] motorPorts, SpeedControllerType[] motorTypes)
   {
       super(motorPorts, motorTypes, true, 1.0, -0.6);
   }

   public void liftToLevelOne()
   {
        while(!((WPI_TalonSRX) this.outputMotors.get(0)).getSensorCollection().isFwdLimitSwitchClosed())
        {
            this.driveForward();
        }
        this.fullStop();
   }
}