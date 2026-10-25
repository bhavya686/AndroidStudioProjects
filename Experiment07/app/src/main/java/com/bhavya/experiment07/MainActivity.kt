package com.bhavya.experiment07

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Destination(
    val name: String,
    val location: String,
    val description: String,
    val imageResId: Int
)

class MainActivity : AppCompatActivity() {

    private lateinit var destinationImage: ImageView
    private lateinit var destinationName: TextView
    private lateinit var destinationDescription: TextView
    private lateinit var destinationList: ListView

    private val destinations = listOf(
        Destination(
            "Taj Mahal",
            "Agra, India",
            "A famous white marble monument and symbol of love.",
            android.R.drawable.ic_menu_gallery
        ),
        Destination(
            "Eiffel Tower",
            "Paris, France",
            "An iconic landmark known for its impressive iron structure.",
            android.R.drawable.ic_menu_camera
        ),
        Destination(
            "Bali",
            "Indonesia",
            "A tropical destination known for beaches and green landscapes.",
            android.R.drawable.ic_menu_compass
        ),
        Destination(
            "Swiss Alps",
            "Switzerland",
            "A beautiful mountain region with scenic views.",
            android.R.drawable.ic_menu_mapmode
        ),
        Destination(
            "Great Wall",
            "China",
            "A historic wall stretching across mountainous landscapes.",
            android.R.drawable.ic_menu_directions
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        destinationImage = findViewById(R.id.destinationImage)
        destinationName = findViewById(R.id.destinationName)
        destinationDescription = findViewById(R.id.destinationDescription)
        destinationList = findViewById(R.id.destinationList)

        val adapter = object : ArrayAdapter<Destination>(
            this,
            R.layout.item_destination,
            R.id.itemName,
            destinations
        ) {
            override fun getView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                return createDestinationRow(position, convertView, parent)
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                return createDestinationRow(position, convertView, parent)
            }

            private fun createDestinationRow(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val row = convertView ?: LayoutInflater.from(context)
                    .inflate(R.layout.item_destination, parent, false)

                val destination = getItem(position)!!

                row.findViewById<ImageView>(R.id.itemImage)
                    .setImageResource(destination.imageResId)

                row.findViewById<TextView>(R.id.itemName)
                    .text = destination.name

                row.findViewById<TextView>(R.id.itemLocation)
                    .text = destination.location

                return row
            }
        }

        destinationList.adapter = adapter

        destinationList.setOnItemClickListener { _, _, position, _ ->
            showDestination(destinations[position])
        }

        showDestination(destinations[0])
    }

    private fun showDestination(destination: Destination) {
        destinationImage.setImageResource(destination.imageResId)
        destinationName.text = destination.name
        destinationDescription.text = destination.description
    }
}