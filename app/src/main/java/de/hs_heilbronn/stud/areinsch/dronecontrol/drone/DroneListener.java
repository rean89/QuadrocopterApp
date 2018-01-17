package de.hs_heilbronn.stud.areinsch.dronecontrol.drone;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.DroneData;

/**
 * Created by AnAnd on 22.12.2017.
 */

public interface DroneListener {
    void droneUpdate(DroneData data);
}
