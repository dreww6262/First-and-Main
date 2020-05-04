package com.example.firstandmainwilliamson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

}
