package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class AltitudeData extends DroneData {

    /**
     * Estimated altitude.
     */
    private float estAltitude;

    /**
     * Estimated vario.
     */
    private float vario;

    /**
     * Create a new altitude data pkg.
     */
    public AltitudeData() {
        id = Drone.ALTITUDE;
        estAltitude = 0.0f;
        vario = 0.0f;
    }

    /**
     * Set the estimated altitude.
     * @param estAltitude Estimated altitude.
     */
    public void setEstAltitude(float estAltitude) {
        this.estAltitude = estAltitude;
    }

    /**
     * Get the estimated altitude.
     * @return Estimated altitude.
     */
    public float getEstAltitude() {
        return estAltitude;
    }

    /**
     * Set the estimated vario.
     * @param vario Estimated vario.
     */
    public void setVario(float vario) {
        this.vario = vario;
    }

    /**
     * Get the estimated vario.
     * @return Estimated vario.
     */
    public float getVario() {
        return vario;
    }
}
