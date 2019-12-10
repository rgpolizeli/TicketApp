package com.rgp.ticketapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.rgp.ticketapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configToolbar()
    }

    /**
     * Toolbar configuration.
     */
    private fun configToolbar() {
        setSupportActionBar(findViewById(R.id.mainToolbar))
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.mainToolbar)
            .setupWithNavController(navController, appBarConfiguration)
    }
}
