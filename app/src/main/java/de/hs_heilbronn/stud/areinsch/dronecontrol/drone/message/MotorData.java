package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

/**
 * Created by AnAnd on 22.12.2017.
 */

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 *
 */
public class MotorData extends DroneData {

    /**
     * Throttle value for each motor;
     */
    private float[] motors;

    /**
     * Create a new motor data pkg.
     */
    public MotorData() {
        id = Drone.MOTOR;
        motors = new float[4];
    }

    /**
     * Set throttle for the motors.
     * @param motors Float array of the length 4. Throttle value for each motor.
     */
    public void setMotors(float[] motors) {
        this.motors = motors;
    }

    /**
     * Get the throttle of the motors.
     * @return Float array of the length 4. Throttle value for each motor.
     */
    public float[] getMotors() {
        return motors;
    }

    @Override
    public void displayData() {
        // TODO: display Throttle
    }
}
