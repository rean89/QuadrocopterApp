package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class MotorPinData extends DroneData {

    /**
     * Motor pins.
     */
    private int[] motorPins;

    /**
     * Create a new motor pin data pkg.
     */
    public MotorPinData() {
        id = Drone.MOTOR_PINS;
    }

    /**
     * Set motor pins.
     * @param motorPins Motor pins.
     */
    public void setMotorPins(int[] motorPins) {
        this.motorPins = motorPins;
    }

    /**
     * Get motoro pins.
     * @return Motor pins.
     */
    public int[] getMotorPins() {
        return motorPins;
    }
}
