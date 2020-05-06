package com.example.firstandmainwilliamson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private var mapFragment: SupportMapFragment? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        mMap.addPolygon(
            PolygonOptions().clickable(false).add(
                LatLng(37.214853, -80.400877),
                LatLng(37.214507, -80.402374),
                LatLng(37.214175, -80.402357),
                LatLng(37.213786, -80.403870),
                LatLng(37.213199, -80.403615),
                LatLng(37.212769, -80.402443),
                LatLng(37.211054, -80.401522),
                LatLng(37.211494, -80.399840)
            )
        )
        val firstAndMainLocation = LatLng(37.213119, -80.401632)
        //mMap.addMarker(MarkerOptions().position(firstAndMainLocation).title("First & Main"))
        val zoom = 16.8f
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstAndMainLocation, zoom))
        mMap.uiSettings.setAllGesturesEnabled(true)
    }
}
