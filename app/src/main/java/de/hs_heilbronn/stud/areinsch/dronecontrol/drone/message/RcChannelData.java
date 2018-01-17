package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class RcChannelData extends DroneData {

    /**
     * Value of the throttle channel.
     */
    private int throttle;

    /**
     * Value of the pitch channel.
     */
    private int pitch;

    /**
     * Value of the yaw channel.
     */
    private int yaw;

    /**
     * Value of the roll channel.
     */
    private int roll;

    /**
     * Value of the aux channels 1-4.
     */
    private int[] aux;

    /**
     * Create a new rc channel data pkg.
     */
    public RcChannelData() {
        id = Drone.RC;
        aux = new int[4];
        throttle = 0;
        pitch = 0;
        yaw = 0;
        roll = 0;
    }

    /**
     * Set the values for aux channel 1-4.
     * @param aux Aux channel 1 to 4.
     */
    public void setAux(int[] aux) {
        this.aux = aux;
    }

    /**
     * Get the aux channel 1-4.
     * @return Aux channel 1-4.
     */
    public int[] getAux(){
        return aux;
    }

    /**
     * Set the value for the throttle rc channel.
     * @param throttle Value of the throttle rc channel.
     */
    public void setThrottle(int throttle) {
        this.throttle = throttle;
    }

    /**
     * Get the value for the throttle rc channel.
     * @return Throttle rc channel.
     */
    public int getThrottle() {
        return throttle;
    }

    /**
     * Set the pitch rc channel.
     * @param pitch Pitch rc channel.
     */
    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    /**
     * Get the pitch rc channel.
     * @return Pitch rc channel.
     */
    public int getPitch() {
        return pitch;
    }

    /**
     * Set the yaw rc channel.
     * @param yaw Yaw rc channel.
     */
    public void setYaw(int yaw) {
        this.yaw = yaw;
    }

    /**
     * Get the yaw rc channel.
     * @return Yaw rc channel.
     */
    public int getYaw() {
        return yaw;
    }

    /**
     * Set the roll rc channel.
     * @param roll
     */
    public void setRoll(int roll) {
        this.roll = roll;
    }

    /**
     * Get the roll rc channel.
     * @return Roll rc channel.
     */
    public int getRoll() {
        return roll;
    }

}
