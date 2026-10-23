package com.bhavya.experiment10

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnLoadContacts: Button
    private lateinit var tvContactCount: TextView
    private lateinit var tvEmptyState: TextView
    private lateinit var contactsContainer: LinearLayout

    private val permissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                loadContacts()
            } else {
                tvContactCount.text = "Denied"
                tvEmptyState.visibility = View.VISIBLE
                tvEmptyState.text =
                    "Contacts permission is needed to display your directory."
                contactsContainer.removeAllViews()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLoadContacts = findViewById(R.id.btnLoadContacts)
        tvContactCount = findViewById(R.id.tvContactCount)
        tvEmptyState = findViewById(R.id.tvEmptyState)
        contactsContainer = findViewById(R.id.contactsContainer)

        btnLoadContacts.setOnClickListener {
            if (
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                loadContacts()
            } else {
                permissionLauncher.launch(
                    Manifest.permission.READ_CONTACTS
                )
            }
        }
    }

    private fun loadContacts() {
        contactsContainer.removeAllViews()
        tvEmptyState.visibility = View.GONE

        var count = 0

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        try {
            contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )?.use { cursor ->

                val nameIndex = cursor.getColumnIndexOrThrow(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                )
                val numberIndex = cursor.getColumnIndexOrThrow(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )

                while (cursor.moveToNext()) {
                    val name = cursor.getString(nameIndex) ?: "Unknown"
                    val number = cursor.getString(numberIndex) ?: "No number"

                    addContactCard(name, number)
                    count++
                }
            }

            tvContactCount.text = "$count entries"

            if (count == 0) {
                tvEmptyState.visibility = View.VISIBLE
                tvEmptyState.text =
                    "No contacts with phone numbers were found."
            }

        } catch (e: SecurityException) {
            tvContactCount.text = "Unavailable"
            tvEmptyState.visibility = View.VISIBLE
            tvEmptyState.text = "Please grant Contacts permission."
        } catch (e: Exception) {
            tvContactCount.text = "Error"
            tvEmptyState.visibility = View.VISIBLE
            tvEmptyState.text = "Unable to load contacts. Please try again."
        }
    }
    private fun addContactCard(name: String, number: String) {
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(18, 16, 18, 16)

            background = GradientDrawable().apply {
                setColor(Color.WHITE)
                cornerRadius = 20f
                setStroke(1, Color.rgb(232, 231, 241))
            }
        }

        val nameView = TextView(this).apply {
            text = name
            textSize = 17f
            setTextColor(Color.rgb(32, 35, 58))
            typeface = Typeface.DEFAULT_BOLD
        }

        val numberView = TextView(this).apply {
            text = number
            textSize = 14f
            setTextColor(Color.rgb(107, 114, 128))
            setPadding(0, 6, 0, 0)
        }

        card.addView(nameView)
        card.addView(numberView)

        val cardParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            bottomMargin = 10
        }

        contactsContainer.addView(card, cardParams)
    }
}