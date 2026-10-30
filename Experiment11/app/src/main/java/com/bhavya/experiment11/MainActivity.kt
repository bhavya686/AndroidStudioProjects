package com.bhavya.experiment11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
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

        // Display student details
        val studentDetails = findViewById<TextView>(R.id.tvStudentDetails)
        studentDetails.text = "Name: Bhavya Khatri  |  USN: 25MCAR0217"

        // Initialize OpenStreetMap
        mapView = findViewById(R.id.mapView)

        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)

        // JAIN University, Jayanagar, Bengaluru
        val jainUniversity = GeoPoint(12.918019, 77.592066)

        // Set map zoom and center
        mapView.controller.setZoom(17.0)
        mapView.controller.setCenter(jainUniversity)

        // Create location marker
        val marker = Marker(mapView)
        marker.position = jainUniversity
        marker.title = "JAIN University"
        marker.snippet = "44/4, District Fund Road, Jayanagar 9th Block, Bengaluru"

        marker.setAnchor(
            Marker.ANCHOR_CENTER,
            Marker.ANCHOR_BOTTOM
        )

        // Add marker to map
        mapView.overlays.add(marker)

        // Display location information
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