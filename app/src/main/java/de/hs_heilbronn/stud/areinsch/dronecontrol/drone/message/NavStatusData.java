package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;

/**
 * Created by AnAnd on 22.12.2017.
 */

public class NavStatusData extends DroneData {

    /**
     * Navigation mode:
     * - None
     * - Position hold
     * - RTH
     * - Mission
     */
    private int navMode;

    /**
     * Navigation sate.
     * - None
     * - RTH start
     * - RTH enroute
     * - Position hold infinity
     * - Position hold timed
     * - Way point enroute
     * - Process next
     * - Jump
     * - Start land
     * - Land in progress
     * - landed
     * - Settling before land
     * - Start descent
     */
    private int navState;

    /**
     * Navigation action.
     */
    private int navAction;

    /**
     * Number of the current way point?
     */
    private int navWpNo;

    /**
     * Navigation errors.
     * - Nav sys is working
     * - Navigation system is working.
     * - Next waypoint distance is more than the safety limit, aborting mission
     * - GPS reception is compromised - pausing mission, COPTER IS ADRIFT!
     * - Error while reading next waypoint from memory, aborting mission.
     * - Mission Finished.
     * - Waiting for timed position hold.
     * - Invalid Jump target detected, aborting mission.
     * - Invalid Mission Step Action code detected, aborting mission.
     * - Waiting to reach return to home altitude.
     * - GPS fix lost, mission aborted - COPTER IS ADRIFT!
     * - Copter is disarmed, navigation engine disabled.
     * - Landing is in progress, check attitude if possible.
     */
    private int navError;

    /**
     * No idea.
     */
    private int navTarBearing;

    /**
     * Create a new navigation status data pkg.
     */
    public NavStatusData() {
        id = Drone.NAV_STATUS;

        navMode = 0;
        navState = 0;
        navAction = 0;
        navWpNo = 0;
        navError = 0;
        navTarBearing = 0;
    }

    public void setNavMode(int navMode) {
        this.navMode = navMode;
    }

    public int getNavMode() {
        return navMode;
    }

    public void setNavState(int navState) {
        this.navState = navState;
    }

    public int getNavState() {
        return navState;
    }

    public void setNavAction(int navAction) {
        this.navAction = navAction;
    }

    public int getNavAction() {
        return navAction;
    }

    public void setNavWpNo(int wpNo) {
        this.navWpNo = wpNo;
    }

    public int getNavWpNo() {
        return navWpNo;
    }

    public void setNavError(int navError) {
        this.navError = navError;
    }

    public int getNavError() {
        return navError;
    }

    public void setNavTarBearing(int targetBearing) {
        this.navTarBearing = targetBearing;
    }

    public int getNavTarBearing() {
        return navTarBearing;
    }

}
