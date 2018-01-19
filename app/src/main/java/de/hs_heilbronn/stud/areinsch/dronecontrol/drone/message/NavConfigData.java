package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class NavConfigData extends DroneData {

    private int navConfigFlags1;

    private int navConfigFlags2;

    private float navConfigRadius;

    private float navConfigWpDist;

    private float navConfigMaxAltitude;

    private float navConfigMaxSpeed;

    private float navConfigMinSpeed;

    private float navConfigCrosstrack;

    private float navConfigBankMax;

    private float rthAltitude;

    private float landSpeed;

    private float fence;

    private int maxWP;

    public NavConfigData() {
        id = Drone.NAV_CONFIG;

        navConfigFlags1 = 0;
        navConfigFlags2 = 0;
        navConfigRadius = 0.0f;
        navConfigWpDist = 0.0f;
        navConfigMaxAltitude = 0.0f;
        navConfigMaxSpeed = 0.0f;
        navConfigMinSpeed = 0.0f;
        navConfigCrosstrack = 0.0f;
        navConfigMaxAltitude = 0.0f;
        navConfigBankMax = 0.0f;
        rthAltitude = 0.0f;
        landSpeed = 0.0f;
        fence = 0.0f;
        maxWP = 0;
    }

    public void setNavConfigFlags1(int navConfigFlags1) {
        this.navConfigFlags1 = navConfigFlags1;
    }

    public int getNavConfigFlags1() {
        return navConfigFlags1;
    }

    public void setNavConfigFlags2(int navConfigFlags2) {
        this.navConfigFlags2 = navConfigFlags2;
    }

    public int getNavConfigFlags2() {
        return navConfigFlags2;
    }

    public void setRadius(float radius) {
        this.navConfigRadius = radius;
    }

    public float getRadius() {
        return navConfigRadius;
    }

    public void setWpDistance(float wpDistance) {
        this.navConfigWpDist = wpDistance;
    }

    public float getWpDistance() {
        return navConfigWpDist;
    }

    public void setNavConfigMaxAltitude(float maxAltitude) {
        this.navConfigMaxAltitude = maxAltitude;
    }

    public float getNavConfigMaxAltitude() {
        return navConfigMaxAltitude;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.navConfigMaxSpeed = maxSpeed;
    }

    public float getMaxSpeed() {
        return navConfigMaxSpeed;
    }

    public void setMinSpeed(float minSpeed) {
        navConfigMinSpeed = minSpeed;
    }

    public float getMinSpeed() {
        return navConfigMinSpeed;
    }

    public void setCrosstrack(float crosstrack) {
        this.navConfigCrosstrack = crosstrack;
    }

    public float getCrosstrack() {
        return navConfigCrosstrack;
    }

    public void setBankMax(float bankMax) {
        this.navConfigBankMax = bankMax;
    }

    public float getBankMax() {
        return navConfigBankMax;
    }

    public void setRthAltitude(float rthAltitude) {
        this.rthAltitude = rthAltitude;
    }

    public float getRthAltitude() {
        return rthAltitude;
    }

    public void setLandSpeed(float landSpeed) {
        this.landSpeed = landSpeed;
    }

    public float getLandSpeed() {
        return landSpeed;
    }

    public void setFence(float fence) {
        this.fence = fence;
    }

    public float getFence() {
        return fence;
    }

    public void setMaxWP(int maxWP) {
        this.maxWP = maxWP;
    }

    public int getMaxWP() {
        return maxWP;
    }

    @Override
    public void displayData() {

    }
}
