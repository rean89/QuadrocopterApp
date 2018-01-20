package de.hs_heilbronn.stud.areinsch.dronecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.file.attribute.GroupPrincipal;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.DroneListener;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.DroneSticks;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Requester;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.connection.ConnectionListener;
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
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.MotorPinData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.NavConfigData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.NavStatusData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.RcChannelData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.RcTuningData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.StatusData;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.WayPointData;


public class MainActivity extends AppCompatActivity implements DroneListener {

    private DroneSticks sticks;
    private Drone drone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sticks = findViewById(R.id.sticks);
        drone = new Drone();
        sticks.setDrone(drone);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Requester(drone);
    }

    @Override
    public void droneUpdate(DroneData data) {

        data.displayData();
    }

}
