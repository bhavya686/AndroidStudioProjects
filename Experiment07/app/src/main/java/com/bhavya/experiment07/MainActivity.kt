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
            name = "Taj Mahal",
            location = "Agra, India",
            description = "A famous white marble monument and symbol of love.",
            imageResId = R.drawable.taj_mahal
        ),
        Destination(
            name = "Eiffel Tower",
            location = "Paris, France",
            description = "An iconic landmark known for its impressive iron structure.",
            imageResId = R.drawable.eiffel_tower
        ),
        Destination(
            name = "Bali",
            location = "Indonesia",
            description = "A tropical destination known for its beaches and green landscapes.",
            imageResId = R.drawable.bali
        ),
        Destination(
            name = "Swiss Alps",
            location = "Switzerland",
            description = "A beautiful mountain region with spectacular scenic views.",
            imageResId = R.drawable.swiss_alps
        ),
        Destination(
            name = "Great Wall",
            location = "China",
            description = "A historic wall stretching across mountainous landscapes.",
            imageResId = R.drawable.great_wall
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

            private fun createDestinationRow(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val row = convertView ?: LayoutInflater.from(context)
                    .inflate(R.layout.item_destination, parent, false)

                val destination = getItem(position)!!

                val itemImage = row.findViewById<ImageView>(R.id.itemImage)
                val itemName = row.findViewById<TextView>(R.id.itemName)
                val itemLocation = row.findViewById<TextView>(R.id.itemLocation)

                itemImage.setImageResource(destination.imageResId)
                itemName.text = destination.name
                itemLocation.text = destination.location

                return row
            }
        }

        destinationList.adapter = adapter

        destinationList.setOnItemClickListener { _, _, position, _ ->
            showDestination(destinations[position])
        }

        // Display the first destination when the app starts.
        showDestination(destinations[0])
    }

    private fun showDestination(destination: Destination) {
        destinationImage.setImageResource(destination.imageResId)
        destinationName.text = destination.name
        destinationDescription.text = destination.description
    }
}