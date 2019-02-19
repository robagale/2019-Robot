package org.usfirst.frc.team6500.robot;

import org.usfirst.frc.team6500.trc.systems.TRCDirectionalSystem;
import org.usfirst.frc.team6500.trc.wrappers.sensors.TRCEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import java.util.List;

public class AutoLift {

    TRCDirectionalSystem liftDrive;
    //TRCEncoder baseSwitch;
    Double dFSpeed = 1.0, dRSpeed = 1.0;

    DigitalInput switchBase, switchTop;

    enum LiftDirection {
        DOWN, UP, STOP
    };

    List<LiftStation> levels;   // list of floors or levels
    TRCEncoder locationEncoder;

    Double currentLocation = 0.0;
    LiftStation targetLocation;
    Double lowerTravel = 0.0;
    Double upperTravel = 0.0;
    LiftDirection currentDirection;
    boolean directDrive = false; 

    // a LiftStation is a floor, or stopping point, optional switch, height is mandatory
    class LiftStation {
        DigitalInput floorSwitch = null;
        Double height = 0.0;

        LiftStation(DigitalInput floor, Double height) {
            floorSwitch = floor;
            this.height = height;
        }
    }


    AutoLift(TRCDirectionalSystem drive, DigitalInput baseSwitch,  DigitalInput topSwitch, TRCEncoder locEncoder) {
        // super(motorPorts, motorTypes, true, 1.0, -0.6);
        liftDrive = drive;
        switchBase = baseSwitch;
        switchTop = topSwitch;
        locationEncoder = locEncoder;
    }

    // Add a new "floor" to the lift's list of floors
    int addLevel(DigitalInput levelSwitch, Double height) {
        levels.add(new LiftStation(levelSwitch, height));
        if (height > upperTravel)
            upperTravel = height; // This is the penthouse

        return levels.size() - 1;
    }

    void lowerTravel(Double limit) {
        lowerTravel = limit;
    }

    void upperTravel(Double limit) {
        upperTravel = limit;
    }

    void updateLift() {
        
        //Check if we hit a limit switch
        
        if (switchBase.get() || switchTop.get())
            liftDrive.fullStop();

        // Stop if we hit our floor sensor
        if (targetLocation.floorSwitch.get())
            liftDrive.fullStop();


        // where are we ?
        currentLocation = locationEncoder.getDistance();
        if ((currentLocation <= lowerTravel) || (currentLocation >= upperTravel)) {
            liftDrive.fullStop();
        }

        // if driver controlled then no position checking    
        if (directDrive) return;

        switch (currentDirection) {
        case DOWN:
            if (currentLocation == targetLocation.height) {
                liftDrive.fullStop();
                currentDirection = LiftDirection.STOP;
            } else if (currentLocation < targetLocation.height) {
                liftDrive.driveForward();
                currentDirection = LiftDirection.UP;
            }
            break;
        case UP:
            if (currentLocation == targetLocation.height) {
                liftDrive.fullStop();
                currentDirection = LiftDirection.STOP;
            } else if (currentLocation > targetLocation.height) {
                liftDrive.driveReverse();
                currentDirection = LiftDirection.DOWN;
            }
            break;
        case STOP:
            break;
        }
    }

    public void stop() {
        liftDrive.fullStop();
        currentDirection = LiftDirection.STOP;
    }

    public void gotoToLevel(int level) {
        // turn off direct drive
        directDrive = false;
        LiftDirection direction = LiftDirection.STOP;
        targetLocation = levels.get(level);

        if (currentLocation < targetLocation.height)
            direction = LiftDirection.UP;
        if (currentLocation > targetLocation.height)
            direction = LiftDirection.DOWN;

        switch (direction) {
        case UP:
            liftDrive.driveForward();
            break;
        case DOWN:
            liftDrive.driveReverse();
            break;
        case STOP:
            // DO nothing
            break;
        }
    }


    public void forceUp() {
        directDrive= true;
        liftDrive.driveForward();
    }

    public void forceUp(double speed) {
        liftDrive.valueDrive(speed);
    }

    public void forceDown() {
        directDrive= true;
        liftDrive.driveReverse();
    }

    public void forceDown(double speed) {
        liftDrive.valueDrive(-speed);
    }


}