package de.hs_heilbronn.stud.areinsch.dronecontrol;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.VideoView;

import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Drone;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.DroneListener;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.DroneSticks;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.Requester;
import de.hs_heilbronn.stud.areinsch.dronecontrol.drone.message.DroneData;


public class MainActivity extends AppCompatActivity implements DroneListener {


    public static MainActivity instance;

    private DroneSticks sticks;
    private Drone drone;
    MediaPlayer mediaplayer;

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sticks = findViewById(R.id.sticks);
        drone = new Drone();
        sticks.setDrone(drone);

        videoView = (VideoView) findViewById(R.id.vv_main_video_view);
        Uri uri = Uri.parse(String.format("android.resource://%s/%s", getPackageName(), R.raw.sample_video));
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        videoView.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Requester(drone);
        videoView.start();
        drone.addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void droneUpdate(DroneData data) {
        data.displayData();
    }

}
