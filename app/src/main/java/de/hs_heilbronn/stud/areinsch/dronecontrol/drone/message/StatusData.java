package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class StatusData extends DroneData {

    /**
     * Time in micro seconds needed for one cycle.
     */
    private int cycleTime;

    /**
     * Number of i2c errors.
     */
    private int i2cErrors;

    /**
     * Barometer available flag.
     */
    private boolean baroFlag;

    /**
     * Magnetometer available flag.
     */
    private boolean magFlag;

    /**
     * GPS module available flag.
     */
    private boolean gpsFlag;

    /**
     * Sonar available flag.
     */
    private boolean sonarFlag;

    /**
     * Box flags. See the MultiWii documentation for more info.
     */
    private int boxFlags;

    /**
     * Config flags. Indicates the current configuration.
     * See MultiWii documentation for more info.
     */
    private int configSettingFlags;

    /**
     * Create a new status data pkg.
     */
    public StatusData() {
        id = Drone.STATUS;
        cycleTime = 0;
        i2cErrors = 0;
        baroFlag = false;
        magFlag = false;
        gpsFlag = false;
        sonarFlag = false;
        boxFlags = 0;
        configSettingFlags = 0;
    }

    /**
     * Set the time, the flight controller needs for one loop.
     * @param cycleTime Time in micro seconds for one loop.
     */
    public void setCycleTime(int cycleTime) {
        this.cycleTime = cycleTime;
    }

    /**
     * Get the time, the flight controller needs for one loop
     * @return Time in micro seconds for one loop.
     */
    public int getCycleTime() {
        return cycleTime;
    }

    /**
     * Set the number of i2c errors.
     * @param i2cErrors Amount of i2c errors.
     */
    public void setI2cErrors(int i2cErrors) {
        this.i2cErrors = i2cErrors;
    }

    /**
     * Get the amount of i2c errors from the flight controller.
     * @return Number of i2c errors.
     */
    public int getI2cErrors() {
        return i2cErrors;
    }

    /**
     * Set the barometer flag.
     * Indicates, if a barometer is available.
     * @param barometer Barometer flag.
     */
    public void setBarometer(boolean barometer) {
        baroFlag = barometer;
    }

    /**
     * Flight controller has a barometer?
     * @return Barometer flag.
     */
    public boolean hasBarometer() {
        return baroFlag;
    }

    /**
     * Set the magnetometer flag.
     * Indicates, if a magnetometer is available.
     * @param magnetometer Magnetometer flag.
     */
    public void setMagnetometer(boolean magnetometer) {
        magFlag = magnetometer;
    }

    /**
     * Flight controller has a magnetometer?
     * @return Magnetometer flag.
     */
    public boolean hasMagnetometer() {
        return magFlag;
    }

    /**
     * Set the gps module available flag.
     * @param gpsModule GPS module flag.
     */
    public void setGpsModule(boolean gpsModule) {
        gpsFlag = gpsModule;
    }

    /**
     * Flight contoller has a gps module?
     * @return GPS module flag.
     */
    public boolean hasGpsModule() {
        return gpsFlag;
    }

    /**
     * Set the sonar available flag.
     * @param sonar Sonar flag.
     */
    public void setSonar(boolean sonar) {
        sonarFlag = sonar;
    }

    /**
     * Flight controller has a sonar module?
     * @return Sonar flag.
     */
    public boolean hasSonar() {
        return sonarFlag;
    }

    /**
     * Set the box flags.
     * For more information about box flags, see the MultiWii documentation.
     * @param boxFlags Box flags.
     */
    public void setBoxFlags(int boxFlags) {
        this.boxFlags = boxFlags;
    }

    /**
     * Get the box flags.
     * For more info about box flags, see MultiWii documentation.
     * @return Box flags.
     */
    public int getBoxFlags() {
        return boxFlags;
    }

    /**
     * Set the config flags.
     * The config flags, indicate the current settings.
     * For more info, see MultiWii documentation.
     * @param configFlags Config flags.
     */
    public void setConfigFlags(int configFlags) {
        this.configSettingFlags = configFlags;
    }

    /**
     * Get the config setting flags.
     * The config flags, indicate the current settings.
     * For more info, see MultiWii documentation.
     * @return Config flags.
     */
    public int getConfigFlags() {
        return configSettingFlags;
    }
}

