package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class GpsData extends DroneData {

    /**
     * GPS fix flag.
     */
    private boolean fix;

    /**
     * Number of satellites.
     */
    private int numSat;

    /**
     * GPS latitude.
     */
    private float latitude;

    /**
     * GPS longitude.
     */
    private float longitude;

    /**
     * GPS altitude.
     */
    private float altitude;

    /**
     * GPS speed in m/s.
     */
    private float speed;


    /**
     * Create a new gps data pkg.
     */
    public GpsData() {
        id = Drone.GPS;
        fix = false;
        numSat = 0;
        latitude = 0.0f;
        longitude = 0.0f;
        altitude = 0.0f;
        speed = 0.0f;
    }

    /**
     * Set gps fix flag.
     * @param fix GPS fix flag.
     */
    public void setFixGPS(boolean fix) {
        this.fix = fix;
    }

    /**
     * Is GPS fix?
     * @return GPS fix flag.
     */
    public boolean isFixGPS() {
        return fix;
    }

    /**
     * Set the number of satellites.
     * @param numSat Number of satellites.
     */
    public void setNumSat(int numSat) {
        this.numSat = numSat;
    }

    /**
     * Get the number of satellites.
     * @return Number of satellites.
     */
    public int getNumSat() {
        return numSat;
    }

    /**
     * Set GPS latitude.
     * @param latitude Latitude.
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Get the GPS latitude.
     * @return Latitude.
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Set the GPS longitude.
     * @param longitude Longitude.
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the GPS longitutde.
     * @return Longitutde.
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Set the GPS altitude.
     * @param altitude GPS altitude.
     */
    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    /**
     * Get the GPS altitude.
     * @return GPS altitude.
     */
    public float getAltitude() {
        return altitude;
    }

    /**
     * Set the GPS speed in m/s.
     * @param speed GPS speed in m/s.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Get the GPS speed in m/s.
     * @return GPS speed in m/s.
     */
    public float getSpeed() {
        return speed;
    }

    @Override
    public void displayData() {

    }
}
