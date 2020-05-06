package com.example.firstandmainwilliamson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.*
import com.google.gson.internal.bind.MapTypeAdapterFactory
import kotlinx.android.synthetic.main.fragment_map.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mMapView: MapView

    companion object {
        var mapFragment: SupportMapFragment? = null
        val TAG: String = MapFragment::class.java.simpleName
        fun newInstance() = MapFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        val polygon: Polygon = mMap.addPolygon(
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
        val zoom: Float = 16.8f
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstAndMainLocation, zoom))
        mMap.uiSettings.setAllGesturesEnabled(true)
    }
}
