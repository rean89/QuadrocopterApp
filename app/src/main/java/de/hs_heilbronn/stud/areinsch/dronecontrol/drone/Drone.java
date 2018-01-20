package de.hs_heilbronn.stud.areinsch.dronecontrol.drone;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.connection.ConnectionListener;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.connection.TCPClient;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.AltitudeData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.AnalogData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.AttitudeData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.CompGpsData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.DroneData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.GpsData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.IdentData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.ImuData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.MiscData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.MotorData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.NavConfigData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.NavStatusData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.RcChannelData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.StatusData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.WayPointData;

/**
 * Created by AnAnd on 21.12.2017.
 */

public class Drone implements ConnectionListener {

    /*
     * Commands used to update information of the drone.
     * See the MultiWii Serial Protocol for more information.
     * http://www.multiwii.com/wiki/index.php?title=Multiwii_Serial_Protocol
     */

    /**
     *
     */
    public static final int IDENT = 100;

    /**
     *
     */
    public static final int STATUS = 101;

    /**
     *
     */
    public static final int IMU = 102;

    /**
     *
     */
    public static final int SERVO = 103;

    /**
     *
     */
    public static final int MOTOR = 104;

    /**
     *
     */
    public static final int RC = 105;

    /**
     *
     */
    public static final int GPS = 106;

    /**
     *
     */
    public static final int COMP_GPS = 107;

    /**
     *
     */
    public static final int ATTITUDE = 108;

    /**
     *
     */
    public static final int ALTITUDE = 109;

    /**
     *
     */
    public static final int ANALOG = 110;

    /**
     *
     */
    public static final int RC_TUNING = 111;

    /**
     *
     */
    public static final int MISC = 114;

    /**
     *
     */
    public static final int MOTOR_PINS = 115;

    /**
     *
     */
    public static final int WP = 118;

    /**
     *
     */
    public static final int NAV_STATUS = 121;

    /**
     *
     */
    public static final int NAV_CONFIG = 122;


    /**
     *
     */
    private static final int SET_RC = 200;

    /**
     *
     */
    private static final int SET_GPS = 201;

    /**
     *
     */
    private static final int SET_RC_TUNING = 204;

    /**
     *
     */
    private static final int ACC_CALIB = 205;

    /**
     *
     */
    private static final int MAG_CALIB = 206;

    /**
     *
     */
    private static final int SET_MISC = 207;

    /**
     *
     */
    private static final int RESET_CONFIG = 208;

    /**
     *
     */
    private static final int SET_WP = 209;

    /**
     *
     */
    private static final int SELECT_SETTING = 210;

    /**
     *
     */
    private static final int SET_HEAD = 211;

    /**
     *
     */
    private static final int SET_MOTOR = 214;

    /**
     *
     */
    private static final int SET_NAV_CONFIG = 215;


    /**
     * Tag for the logs.
     */
    private static final String TAG = "MY_DRONE";

    /**
     * Connection to the server.
     */
    private TCPClient connection;

    /**
     * Listeners get notified about updates.
     */
    private List<DroneListener> listeners;


    public Drone() {
        Log.d(TAG, "Create");
        listeners = new ArrayList<>();
        connection = new TCPClient(this);
        connection.connect();
    }

    public boolean isConnected() {
        return connection.isConnected();
    }

    public void connect() {
        connection.connect();
    }

    /**
     * Request a update for a set of informationfrom the drone.
     * For more info, seee the MultiWii Serial Protocol:
     * http://www.multiwii.com/wiki/index.php?title=Multiwii_Serial_Protocol
     * @param cmd Use the CMD enum.
     */
    public void update(int cmd) {
        if(cmd >= IDENT & cmd < SET_RC) {
            connection.reqData(Integer.toString(cmd));
        }
    }

    /**
     * Add a listener. Listeners get notified about updated values.
     * @param listener New listener.
     */
    public void addListener(DroneListener listener) {
        listeners.add(listener);
    }

    /**
     * Disables the update notification for the listener.
     * @param listener Listener to remove.
     */
    public void removeListener(DroneListener listener) {
        listeners.remove(listener);
    }

    /**
     * Used to get new data from the socket connection.
     * @param data Raw data.
     */
    @Override
    public void dataReceived(String data) {

        if(data == null || data.isEmpty()) {
            Log.d(TAG, "No DATA BITCHES!");
            return;
        }

        DroneData dataPkg = null;
        String[] pkgParts = data.split(";");

        Log.d(TAG, "Data: " + data);
        Log.d(TAG, "Msg id: " + pkgParts[0]);

        if(pkgParts.length < 2) {
            return;
        }

        switch(Integer.parseInt(pkgParts[0])) {
            case IDENT:
                IdentData ident = new IdentData();
                ident.setVersion(Integer.parseInt(pkgParts[1]) / 100);
                ident.setDroneType(Integer.parseInt(pkgParts[2]));
                ident.setCapability(Integer.parseInt(pkgParts[4]));

                dataPkg = ident;
                Log.d(TAG, "Ident pkg received");
                break;
            case STATUS:
                StatusData status = new StatusData();
                status.setCycleTime(Integer.parseInt(pkgParts[1]));
                status.setI2cErrors(Integer.parseInt(pkgParts[2]));

                int sensorFlags = Integer.parseInt(pkgParts[3]);
                status.setBarometer(((sensorFlags & (1 << 1)) != 0));
                status.setMagnetometer(((sensorFlags & (1 << 2)) != 0));
                status.setGpsModule(((sensorFlags & (1 << 3)) != 0));
                status.setSonar(((sensorFlags & (1 << 4)) != 0));

                status.setBoxFlags(Integer.parseInt(pkgParts[4]));
                status.setConfigFlags(Integer.parseInt(pkgParts[5]));

                dataPkg = status;
                Log.d(TAG, "Status pkg received.");
                break;
            case IMU:
                ImuData imu = new ImuData();
                float[] tempIMU = new float[3];

                for(int i = 0; i < 3; i++) {
                   tempIMU[i] = Float.parseFloat(pkgParts[i + 1]) / 512;
                }
                imu.setAccel(tempIMU);
                for(int i = 0; i < 3; i++) {
                    tempIMU[i] = Float.parseFloat(pkgParts[i + 4]);
                }
                imu.setGyro(tempIMU);

                for(int i = 0; i < 3; i++) {
                    tempIMU[i] = Float.parseFloat(pkgParts[i + 7]);
                }
                imu.setMag(tempIMU);

                dataPkg = imu;
                Log.d(TAG, "IMU pkg received.");
                break;
            case SERVO:
                // TODO
                Log.d(TAG, "Servo pkg received.");
                break;
            case MOTOR:
                MotorData motor = new MotorData();
                float[] tempMotors = new float[4];
                for(int i = 0; i < 4; i++) {
                    // TODO Check if int or float.
                    tempMotors[i] = Float.parseFloat(pkgParts[i + 1]);
                }
                motor.setMotors(tempMotors);

                dataPkg = motor;
                Log.d(TAG, "Motor pkg received.");
                break;
            case RC:
                RcChannelData channels = new RcChannelData();
                channels.setRoll(Integer.parseInt(pkgParts[1]));
                channels.setPitch(Integer.parseInt(pkgParts[2]));
                channels.setYaw(Integer.parseInt(pkgParts[3]));
                channels.setThrottle(Integer.parseInt(pkgParts[4]));

                dataPkg = channels;
                Log.d(TAG, "RC pkg received.");
                break;
            case GPS:
                GpsData gps = new GpsData();
                gps.setFixGPS(pkgParts[1].equals("1"));
                gps.setNumSat(Integer.parseInt(pkgParts[2]));
                gps.setLatitude(Float.parseFloat(pkgParts[3]));
                gps.setLongitude(Float.parseFloat(pkgParts[4]));
                gps.setAltitude(Float.parseFloat(pkgParts[5]));
                gps.setSpeed(Float.parseFloat(pkgParts[6]));

                dataPkg = gps;
                Log.d(TAG, "GPS pkg received.");
                break;
            case COMP_GPS:
                CompGpsData compGPS = new CompGpsData();
                compGPS.setDistanceHome(Float.parseFloat(pkgParts[1]));
                compGPS.setDirectionHome(Float.parseFloat(pkgParts[2]));

                dataPkg = compGPS;
                Log.d(TAG, "Comp GPS pkg received.");
                break;
            case ATTITUDE:
                AttitudeData attitude = new AttitudeData();
                float[] tempAngel = new float[2];
                for(int i = 0; i < 2; i++) {
                    tempAngel[i] = Float.parseFloat(pkgParts[i + 1]) / 10;
                }
                attitude.setAngel(tempAngel);
                attitude.setHeading(Float.parseFloat(pkgParts[2]));

                dataPkg = attitude;
                Log.d(TAG, "Attitude pkg received.");
                break;
            case ALTITUDE:
                AltitudeData altitude = new AltitudeData();
                altitude.setEstAltitude(Float.parseFloat(pkgParts[1]));
                altitude.setVario(Float.parseFloat(pkgParts[2]));

                dataPkg = altitude;
                Log.d(TAG, "Altitude pkg received.");
                break;
            case ANALOG:
                AnalogData analog = new AnalogData();
                analog.setVBat(Float.parseFloat(pkgParts[1]) * 10);
                analog.setPowerMeterSum(Integer.parseInt(pkgParts[2]));
                analog.setRSSI(Integer.parseInt(pkgParts[3]));
                analog.setAmperage(Float.parseFloat(pkgParts[4]));

                dataPkg = analog;
                Log.d(TAG, "Analog pkg received.");
                break;
            case RC_TUNING:

                Log.d(TAG, "RC tuning pkg received.");
                break;
            case MISC:
                MiscData misc = new MiscData();
                misc.setMinThrottle(Float.parseFloat(pkgParts[2]));
                misc.setFailSafeThrottle(Float.parseFloat(pkgParts[5]));
                misc.setMagDeclination(Float.parseFloat(pkgParts[8]));
                misc.setVBatScale(Float.parseFloat(pkgParts[9]));
                misc.setVBatWarn1(Float.parseFloat(pkgParts[10]));
                misc.setVBatWarn2(Float.parseFloat(pkgParts[11]));
                misc.setVBatCrit(Float.parseFloat(pkgParts[12]));

                dataPkg = misc;
                Log.d(TAG, "MISC pkg received.");
                break;
            case MOTOR_PINS:

                Log.d(TAG, "Motor pin pkg received.");
                break;
            case WP:
                WayPointData waypoint = new WayPointData();
                waypoint.setNo(Integer.parseInt(pkgParts[1]));
                waypoint.setAction(Integer.parseInt(pkgParts[2]));
                waypoint.setLatitude(Float.parseFloat(pkgParts[3]));
                waypoint.setLongitude(Float.parseFloat(pkgParts[4]));
                waypoint.setAltitude(Float.parseFloat(pkgParts[5]));
                waypoint.setParam1(Integer.parseInt(pkgParts[6]));
                waypoint.setParam2(Integer.parseInt(pkgParts[7]));
                waypoint.setParam3(Integer.parseInt(pkgParts[8]));
                waypoint.setFlag(Integer.parseInt(pkgParts[9]));

                dataPkg = waypoint;
                Log.d(TAG, "Way point pkg received.");
                break;
            case NAV_STATUS:
                NavStatusData navStatus = new NavStatusData();
                navStatus.setNavMode(Integer.parseInt(pkgParts[1]));
                navStatus.setNavState(Integer.parseInt(pkgParts[2]));
                navStatus.setNavAction(Integer.parseInt(pkgParts[3]));
                navStatus.setNavWpNo(Integer.parseInt(pkgParts[4]));
                navStatus.setNavError(Integer.parseInt(pkgParts[5]));
                navStatus.setNavTarBearing(Integer.parseInt(pkgParts[6]));

                dataPkg = navStatus;
                Log.d(TAG, "Navigation status pkg received.");
                break;
            case NAV_CONFIG:
                NavConfigData navConfig = new NavConfigData();
                navConfig.setNavConfigFlags1(Integer.parseInt(pkgParts[1]));
                navConfig.setNavConfigFlags2(Integer.parseInt(pkgParts[1]));
                navConfig.setRadius(Float.parseFloat(pkgParts[3]));
                navConfig.setWpDistance(Float.parseFloat(pkgParts[4]));
                navConfig.setNavConfigMaxAltitude(Float.parseFloat(pkgParts[5]));
                navConfig.setMaxSpeed(Float.parseFloat(pkgParts[6]));
                navConfig.setMinSpeed(Float.parseFloat(pkgParts[7]));
                navConfig.setCrosstrack(Float.parseFloat(pkgParts[8]));
                navConfig.setBankMax(Float.parseFloat(pkgParts[9]));
                navConfig.setRthAltitude(Float.parseFloat(pkgParts[10]));
                navConfig.setLandSpeed(Float.parseFloat(pkgParts[11]));

                dataPkg = navConfig;
                Log.d(TAG, "Navigation config pkg received.");
                break;
            default:
                Log.d(TAG, "Invalid data received.");
        }

        for(DroneListener listener : listeners) {
            listener.droneUpdate(dataPkg);
        }
    }

    public void sendRC(float throttle, float roll, float pitch, float yaw) {
        String dataPkg = SET_RC + ";" + roll + ";" + pitch + ";" + yaw + ";" + throttle;
        connection.sendData(dataPkg);
    }

    public void sendGPS(boolean fix, int numSat, float latitude, float longitude, float altitude, float speed) {
        String stringFix = fix ? "1" : "0";
        String dataPkg = SET_GPS + ";" + stringFix + ";" + numSat + ";" + latitude + ";"
                + longitude + ";" + altitude + ";" + speed;
        connection.sendData(dataPkg);
    }

    /*
    public void sendTunning() {

    }*/

    /*
    public void sendMISC(int powerTrigger, int minThrottle, int maxThrottle, int minCommand,
                         int failsafeThrottle, int arm, int lifetime, int magDeclination,
                         float vBatScale, float vBatWarn1, float vBatWarn2, float vBatCrit) {

    }*/

    public void sendWayPoint(int no, int action, float lat, float lon, float alt, int param1,
                             int param2, int param3, int flags) {
        String dataPkg = SET_WP + ";" + no + ";" + action + ";" + lat + ";" + lon + ";" +  alt
                + ";" + param1 + ";" + param2 + ";" + param3 + ";" + flags;
        connection.sendData(dataPkg);
    }

    public void sendHead(int magHold) {
        String dataPkg = SET_HEAD + ";" + magHold;
        connection.sendData(dataPkg);
    }

    /*
    public void sendMotor() {

    }*/


    public void sendNavConfig(int flag1, int flag2, float radius, float distance, float maxAlt,
                              float maxSpeed, float minSpeed, int crosstrack, int bankMax,
                              float rthAlt, float landSpeed, float fence, int maxWP) {
        String dataPkg = SET_NAV_CONFIG + ";";
        connection.sendData(dataPkg);
    }
    /*
    public void sendCalbAccel() {

    }*/
    /*
    public void sendCalibMag() {

    }*/

    /*
    public void sendResetConfig() {

    }*/

    /*
    public void sendSelectSetting() {

    }*/


}
