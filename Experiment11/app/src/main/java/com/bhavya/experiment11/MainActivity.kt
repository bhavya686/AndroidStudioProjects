package com.bhavya.experiment11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().userAgentValue = packageName

        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)

        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)

        // Initial location: India Gate, New Delhi
        val indiaGate = GeoPoint(28.6129, 77.2295)

        mapView.controller.setZoom(15.0)
        mapView.controller.setCenter(indiaGate)

        // Create and add location marker
        val marker = Marker(mapView)
        marker.position = indiaGate
        marker.title = "India Gate"
        marker.snippet = "New Delhi, India"

        marker.setAnchor(
            Marker.ANCHOR_CENTER,
            Marker.ANCHOR_BOTTOM
        )

        mapView.overlays.add(marker)
        marker.showInfoWindow()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }
}