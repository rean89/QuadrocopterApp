package de.quadrocopter.quadrocopterapp

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.sample_video}")
        vv_main_video.setVideoURI(uri)

    }
}
