package com.bhavya.experiment10

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnLoadContacts: Button
    private lateinit var tvContactCount: TextView
    private lateinit var tvContacts: TextView

    private val permissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                loadContacts()
            } else {
                tvContactCount.text = "Permission denied"
                tvContacts.text =
                    "Grant Contacts permission to view your contacts."

                Toast.makeText(
                    this,
                    "Contacts permission is required",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLoadContacts = findViewById(R.id.btnLoadContacts)
        tvContactCount = findViewById(R.id.tvContactCount)
        tvContacts = findViewById(R.id.tvContacts)

        btnLoadContacts.setOnClickListener {
            checkPermissionAndLoadContacts()
        }
    }

    private fun checkPermissionAndLoadContacts() {
        val permission = Manifest.permission.READ_CONTACTS

        if (
            ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            loadContacts()
        } else {
            permissionLauncher.launch(permission)
        }
    }

    private fun loadContacts() {
        val contacts = StringBuilder()
        var count = 0

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        try {
            val cursor: Cursor? = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )

            if (cursor == null) {
                tvContactCount.text = "Unable to load contacts"
                tvContacts.text = "The contacts provider returned no data."
                return
            }

            cursor.use {
                val nameIndex = it.getColumnIndexOrThrow(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                )

                val numberIndex = it.getColumnIndexOrThrow(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )

                while (it.moveToNext()) {
                    val name = it.getString(nameIndex) ?: "Unknown"
                    val number = it.getString(numberIndex) ?: "No number"

                    contacts.append("Name: ")
                        .append(name)
                        .append("\nPhone: ")
                        .append(number)
                        .append("\n\n")

                    count++
                }
            }

            tvContactCount.text = "Contacts found: $count"

            tvContacts.text = if (count > 0) {
                contacts.toString()
            } else {
                "No contacts with phone numbers found."
            }

        } catch (e: SecurityException) {
            tvContactCount.text = "Permission error"
            tvContacts.text = "Please grant Contacts permission."
        } catch (e: Exception) {
            tvContactCount.text = "Error loading contacts"
            tvContacts.text = "Unable to retrieve contacts. Please try again."
        }
    }
}