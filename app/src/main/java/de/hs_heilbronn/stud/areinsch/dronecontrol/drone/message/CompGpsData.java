package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class CompGpsData extends DroneData {

    /**
     * Distance to home position.
     */
    private float disHome;

    /**
     * Direction to home position.
     */
    private float dirHome;

    /**
     * Create a comp gps pkg.
     */
    public CompGpsData() {
        id = Drone.COMP_GPS;

        disHome = 0.0f;
        dirHome = 0.0f;
    }

    /**
     * Set distance to home position.
     * @param distance Distance to home position.
     */
    public void setDistanceHome(float distance) {
        disHome = distance;
    }

    /**
     * Get distance to home position.
     * @return Distance to home position.
     */
    public float getDistanceHome() {
        return disHome;
    }

    /**
     * Set direction to home position.
     * @param direction Direction to home position.
     */
    public void setDirectionHome(float direction) {
        dirHome = direction;
    }

    /**
     * Get direction to home position.
     * @return Direction to home position.
     */
    public float getDirectionHome() {
        return dirHome;
    }

}
