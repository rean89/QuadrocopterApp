package de.hs_heilbronn.stud.areinsch.dronecontrol.drone;


public class Requester {

    public static int[] requestCodes = {
            Drone.IDENT,
            Drone.STATUS,
            Drone.IMU,
            Drone.SERVO,
            Drone.MOTOR,
            Drone.RC,
            Drone.GPS,
            Drone.COMP_GPS,
            Drone.ATTITUDE,
            Drone.ALTITUDE,
            Drone.ANALOG,
            Drone.RC_TUNING,
            Drone.MISC,
            Drone.MOTOR_PINS,
            Drone.WP,
            Drone.NAV_STATUS,
            Drone.NAV_CONFIG
    };

    public Requester(final Drone drone) {
        for (final int requestCode : requestCodes) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        while (!interrupted()) {
                            Thread.sleep(1000);
                            drone.update(requestCode);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

}
