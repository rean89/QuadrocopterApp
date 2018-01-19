package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 23.12.2017.
 */

public class WayPointData extends DroneData {

    /**
     * Number of the way point.
     */
    private int no;

    /**
     * Current action?
     */
    private int action;

    /**
     * Latitude of the way point.
     */
    private float latitude;

    /**
     * Longitude of the way point.
     */
    private float longitude;

    /**
     * Altitude of the way point.
     */
    private float altitude;

    /**
     * Parameter 1. Varies according to action.
     */
    private int param1;

    /**
     * Parameter 2. Varies according to action.
     */
    private int param2;

    /**
     * Parameter 3. Varies according to action.
     */
    private int param3;

    /**
     * Flag. 0xa5 = last waypoint, otherwise 0.
     */
    private int flag;

    public WayPointData() {
        id = Drone.WP;

        no = 0;
        action = 0;
        latitude = 0.0f;
        longitude = 0.0f;
        altitude = 0.0f;
        param1 = 0;
        param2 = 0;
        param3 = 0;
        flag = 0;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    public int getParam1() {
        return param1;
    }

    public void setParam2(int param2) {
        this.param2 = param2;
    }

    public int getParam2() {
        return param2;
    }

    public void setParam3(int param3) {
        this.param3 = param3;
    }

    public int getParam3() {
        return param3;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    @Override
    public void displayData() {

    }
}
