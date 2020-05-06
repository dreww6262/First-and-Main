package com.example.firstandmainwilliamson

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isMyServiceRunning(NotificationService::class.java)) {
            val i = Intent(this, NotificationService::class.java)
            this.startService(i)
        }

        val bnav: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navController = findNavController(R.id.nav_host_fragment)

        bnav.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.stores -> {
                    navController.navigate(R.id.storeFragment)
                    true
                }
                R.id.account -> {
                    navController.navigate(R.id.accountFragment)
                    true
                }
                R.id.map_nav -> {
                    navController.navigate(R.id.mapFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }

    }


    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager =
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

}
