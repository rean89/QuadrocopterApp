package de.quadrocopter.quadrocopterapp

import android.graphics.Color
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_layout.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(custom_toobar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, custom_toobar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        window.decorView.setBackgroundColor(Color.CYAN)

        window.decorView.setBackgroundColor(Color.BLUE)
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else  {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_gps_coordinateds_screen -> {

            }
            R.id.nav_home_screen -> {

            }
            R.id.nav_settings_screen -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}

class asynkSocketComunication() : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg p0: String?): String {
        TODO("Implement a Service which connects to a socket") //To change body of created functions use File | Settings | File Templates.
    }


}

// TODO: MULTIWII Parser
// TODO: MULTIWII Command file
// TODO: Create Fields for all Statistic members
//


// TODO: Implement more Members to display all Data needed!
// TODO: Maybe the Strings have to be exended to be the same size every time
// TODO: maybe a Parser?
data class TelemetrieData(var height: String, var latitude: String, var longitude: String)