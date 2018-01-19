package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class MiscData extends DroneData {

    /**
     * Minimal throttle value for the motors.
     * Aka. Standgas.
     */
    private float minThrottle;

    /**
     * Maximum throttle for the motors.
     */
    private float maxThrottle;

    /**
     * Minimal throttle value accepted from the rc.
     */
    private float minCommand;

    /**
     * Throttole for fail safe mode.
     */
    private float failSafeThrottle;

    /**
     * No description available.
     */
    private int arm;

    /**
     * No description available.
     */
    private int lifetime;

    /**
     * Magnetic declination in 1/10 degree.
     */
    private float magDeclination;

    /**
     * No description available.
     */
    private float vBatScale;

    /**
     * Battery warning level 1 value.
     */
    private float vBatWarn1;

    /**
     * Battery warning level 2 value.
     */
    private float vBatWarn2;

    /**
     * Battery warning level critical value.
     */
    private float vBatCrit;

    public MiscData() {
        id = Drone.MISC;
        minThrottle = 0.0f;
        maxThrottle = 0.0f;
        minCommand = 0.0f;
        failSafeThrottle = 0.0f;
        arm = 0;
        lifetime = 0;
        magDeclination = 0.0f;
        vBatScale = 0.0f;
        vBatWarn1 = 0.0f;
        vBatWarn2 = 0.0f;
        vBatCrit = 0.0f;
    }

    /**
     * Set the minimal throttle.
     * @param minThrottle Minimum throttle.
     */
    public void setMinThrottle(float minThrottle) {
        this.minThrottle = minThrottle;
    }

    /**
     * Get the minimum throttle.
     * @return Minimum throttle.
     */
    public float getMinThrottle() {
        return minThrottle;
    }

    /**
     * Set the max throttle.
     * @param maxThrottle Maximum throttle.
     */
    public void setMaxThrottle(float maxThrottle) {
        this.maxThrottle = maxThrottle;
    }

    /**
     * Get the max throttle.
     * @return Max throttle.
     */
    public float getMaxThrottle() {
        return maxThrottle;
    }

    /**
     * Set the minimal allowed throttle from rc.
     * @param minCommand Min. throttle by rc.
     */
    public void setMinCommand(float minCommand) {
        this.minCommand = minCommand;
    }

    /**
     * Get the minimal allowed throttle by rc.
     * @return Min. throttle by rc.
     */
    public float getMinCommand() {
        return minCommand;
    }

    /**
     * Set the fail safe throttle value.
     * @param failSafeThrottle Fail safe throttle.
     */
    public void setFailSafeThrottle(float failSafeThrottle) {
        this.failSafeThrottle = failSafeThrottle;
    }

    /**
     * Get the fail safe throttle.
     * @return Fail safe throttle.
     */
    public float getFailSafeThrottle() {
        return failSafeThrottle;
    }

    /**
     * Set armed.
     * @param arm armed.
     */
    public void setARM(int arm) {
        this.arm = arm;
    }

    /**
     * Get armed.
     * @return armed.
     */
    public int getARM() {
        return arm;
    }

    /**
     * Set life time.
     * @param lifeTime Lifetime.
     */
    public void setLifeTime(int lifeTime) {
        this.lifetime = lifeTime;
    }

    /**
     * Get lifetime.
     * @return Lifetime.
     */
    public int getLifetime() {
        return lifetime;
    }

    /**
     * Set the magnetic declination in 1/10 degree.
     * @param magDeclination Magnetic declination.
     */
    public void setMagDeclination(float magDeclination) {
        this.magDeclination = magDeclination;
    }

    /**
     * Get the magnetix declination in 1/10 degree.
     * @return Magnetic declination.
     */
    public float getMagDeclination() {
        return magDeclination;
    }

    /**
     * Set battery voltage scale.
     * @param vBatScale Battery voltage scale value.
     */
    public void setVBatScale(float vBatScale) {
        this.vBatScale = vBatScale;
    }

    /**
     * Get battery voltage scale.
     * @return Battery voltage scale value.
     */
    public float getVBatScale() {
        return vBatScale;
    }

    /**
     * Set the battery voltage warn level 1.
     * @param vBatWarn1 Battery voltage warn level 1 value.
     */
    public void setVBatWarn1(float vBatWarn1) {
        this.vBatWarn1 = vBatWarn1;
    }

    /**
     * Get the battery voltage warn level 1.
     * @return Battery voltage warn level 1 value.
     */
    public float getVBatWarn1() {
        return vBatWarn1;
    }

    /**
     * Set the battery voltage warn level 2.
     * @param vBatWarn2 Battery voltage warn level 2 value.
     */
    public void setVBatWarn2(float vBatWarn2) {
        this.vBatWarn2 = vBatWarn2;
    }

    /**
     * Get the battery voltage warn level 2.
     * @return Battery voltage warn level2 value.
     */
    public float getVBarWarn2() {
        return vBatWarn2;
    }

    /**
     * Set the battery voltage warn level critical.
     * @param vBatCrit Battery voltage warn level critical value.
     */
    public void setVBatCrit(float vBatCrit) {
        this.vBatCrit = vBatCrit;
    }

    /**
     * Get the battery voltage warn level critical.
     * @return Battery voltage warn level critical value.
     */
    public float getVBatCrit() {
        return vBatCrit;
    }

    @Override
    public void displayData() {

    }
}
