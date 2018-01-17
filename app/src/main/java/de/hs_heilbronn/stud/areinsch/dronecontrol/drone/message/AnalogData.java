package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class AnalogData extends DroneData {

    /**
     * Voltage of the battery as 1/10 volt.
     */
    private float vBat;

    /**
     * Estimated used current in mAh.
     */
    private int powerMeterSum;

    /**
     * Values to indicate quality of a wireless connection.
     */
    private int rssi;

    /**
     * Amperage of the battery?
     */
    private float amperage;

    /**
     * Create a new analog data pkg.
     */
    public AnalogData() {
        id = Drone.ANALOG;
        vBat = 0.0f;
        powerMeterSum = 0;
        rssi = 0;
        amperage = 0.0f;
    }

    /**
     * Set voltage of the battery.
     * @param vBat Voltage of battery.
     */
    public void setVBat(float vBat) {
        this.vBat = vBat;
    }

    /**
     * Get battery voltage.
     * @return Battery voltage.
     */
    public float getVBat() {
        return vBat;
    }

    /**
     * Set the power meter.
     * @param powerMeterSum Power meter.
     */
    public void setPowerMeterSum(int powerMeterSum) {
        this.powerMeterSum = powerMeterSum;
    }

    /**
     * Get power meter sum.
     * @return Power meter sum.
     */
    public int getPowerMeterSum() {
        return powerMeterSum;
    }

    /**
     * Set the rssi value.
     * @param rssi Rssi value.
     */
    public void setRSSI(int rssi) {
        this.rssi = rssi;
    }

    /**
     * Get the rssi value.
     * @return RSSI value.
     */
    public int getRssi() {
        return rssi;
    }

    /**
     * Set the amperage of the battery.
     * @param amperage Battery amperage.
     */
    public void setAmperage(float amperage) {
        this.amperage = amperage;
    }

    /**
     * Get amperage of the battery.
     * @return Battery amperage.
     */
    public float getAmperage() {
        return amperage;
    }

}
