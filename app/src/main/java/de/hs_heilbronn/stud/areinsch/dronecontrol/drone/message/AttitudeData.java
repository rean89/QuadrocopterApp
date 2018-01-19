package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class AttitudeData extends DroneData {

    /**
     * X and Y angel of the attitude.
     */
    private float[] angel;

    /**
     * Heading of the attitude.
     */
    private float heading;

    /**
     * Create a new attitude data pkg.
     */
    public AttitudeData() {
        id = Drone.ATTITUDE;
        angel = new float[2];
        heading = 0.0f;
    }

    /**
     * Set x and y angel of the attitude.
     * @param angels X and Y angel.
     */
    public void setAngel(float[] angels) {
        angel = angels;
    }

    /**
     * Get x and y angel of the attitude.
     * @return X and y angel.
     */
    public float[] getAngel() {
        return angel;
    }

    /**
     * Set attitude heading.
     * @param heading Heading.
     */
    public void setHeading(float heading) {
        this.heading = heading;
    }

    /**
     * Get attitude heading.
     * @return Heading.
     */
    public float getHeading() {
        return heading;
    }

    @Override
    public void displayData() {

    }
}
