package de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message;

/**
 * Created by AnAnd on 22.12.2017.
 */

public abstract class DroneData {

    /**
     * Id of the data package.
     * Indicates the type of data.
     * For more infos, see MultiWii serial protocol documentation:
     * http://www.multiwii.com/wiki/index.php?title=Multiwii_Serial_Protocol
     */
    int id;

    /**
     * Get the package id.
     * Indicates the type of data.
     * For more infos, see MultiWii serial protocol documentation:
     * http://www.multiwii.com/wiki/index.php?title=Multiwii_Serial_Protocol
     * @return Id of the data pkg.
     */
    public int getId(){
        return id;
    }
}
