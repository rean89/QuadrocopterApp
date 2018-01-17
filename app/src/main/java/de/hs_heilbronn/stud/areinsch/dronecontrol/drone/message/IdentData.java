package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class IdentData extends DroneData {

    /**
     * MultiWii version on the flight controller.
     */
    private float version;

    /**
     * Indicates type of the drone, e.g. tri-copter, quad-copter x, etc..
     */
    private int droneType;

    /**
     * Indicates the capbility of the flight controller.
     */
    private int capability;

    /**
     * Create a new ident data pkg.
     */
    public IdentData() {
        id = Drone.IDENT;
        version = 0;
        droneType = 0;
        capability = 0;
    }

    /**
     * Set the MultiWii version.
     * @param version Version of MultiWii.
     */
    public void setVersion(float version) {
        this.version = version;
    }

    /**
     * Get the MultiWii version.
     * @return Version of MultiWii.
     */
    public float getVersion() {
        return version;
    }

    /**
     * Set the drone type.
     * For more info, see MultiWii documentation.
     * @param droneType Type of drone.
     */
    public void setDroneType(int droneType) {
        this.droneType = droneType;
    }

    /**
     * Get the drone type.
     * For more info, see MultiWii doucmentation.
     * @return Type of drone.
     */
    public int getDroneType() {
        return droneType;
    }

    /**
     * Set the capability of the flight controller.
     * For more info, see MultiWii documentation.
     * @param capability Capability flags of the flight controller.
     */
    public void setCapability(int capability) {
        this.capability = capability;
    }

    /**
     * Get the capability flags of the flight controller.
     * For more info, see MultiWii documentation.
     * @return Capability flags of the flight controller.
     */
    public int getCapability() {
        return capability;
    }
}
