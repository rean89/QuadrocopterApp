package de.hs_heilbronn.stud.areinsch.dronecontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.DroneListener;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.DroneSticks;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Requester;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.DroneData;


public class MainActivity extends AppCompatActivity implements DroneListener {


    public static MainActivity instance;

    private DroneSticks sticks;
    private Drone drone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
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
