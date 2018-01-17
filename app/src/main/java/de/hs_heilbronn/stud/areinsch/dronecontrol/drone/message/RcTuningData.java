package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class RcTuningData extends DroneData {

    /**
     * RC tuning values.
     */
    private float[] tuning;

    /**
     * Create a new rc tuning data pkg.
     */
    public RcTuningData() {
        id = Drone.RC_TUNING;
    }

    /**
     * Set the tuning values for the rc.
     * @param tuning Tuning values for rc.
     */
    public void setTuning(float[] tuning) {
        this.tuning = tuning;
    }

    /**
     * Get the tuning values of the rc.
     * @return Tuning values of the rc.
     */
    public float[] getTuning() {
        return tuning;
    }
}
