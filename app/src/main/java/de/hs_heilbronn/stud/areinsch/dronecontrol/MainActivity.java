package de.hs_heilbronn.stud.areinsch.dronecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.DroneListener;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.DroneSticks;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.connection.ConnectionListener;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.DroneData;
//import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.connection.TCPClient;


public class MainActivity extends AppCompatActivity implements DroneListener {

    //private TCPClient client;
    private EditText txtField;
    private TextView txtOutput;
    private DroneSticks sticks;
    private Drone drone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sticks = findViewById(R.id.sticks);
        txtField = findViewById(R.id.textField);
        txtOutput = findViewById(R.id.textOutput);
        //client = new TCPClient(this);
        drone = new Drone();
        sticks.setDrone(drone);
    }

    public void onClick(View view) {
        String txt = txtField.getText().toString();
        //client.reqData(txt);
    }

    @Override
    public void droneUpdate(DroneData data) {

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                drone.update(Drone.IMU);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

}
