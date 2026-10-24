package com.bhavya.experiment06

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val usnInput = findViewById<EditText>(R.id.usnInput)
        val genderGroup = findViewById<RadioGroup>(R.id.genderGroup)
        val courseSpinner = findViewById<Spinner>(R.id.courseSpinner)
        val termsCheckbox = findViewById<CheckBox>(R.id.termsCheckbox)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        val courses = arrayOf(
            "MCA",
            "MBA",
            "BCA",
            "BBA",
            "B.Com",
            "B.Sc"
        )

        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            courses
        )

        adapter.setDropDownViewResource(
            R.layout.spinner_dropdown_item
        )

        courseSpinner.adapter = adapter

        submitButton.setOnClickListener {

            val name = nameInput.text.toString().trim()
            val usn = usnInput.text.toString().trim()
            val selectedGenderId = genderGroup.checkedRadioButtonId

            if (name.isEmpty()) {
                nameInput.error = "Please enter your name"
                nameInput.requestFocus()
                return@setOnClickListener
            }

            if (usn.isEmpty()) {
                usnInput.error = "Please enter your USN"
                usnInput.requestFocus()
                return@setOnClickListener
            }

            if (selectedGenderId == -1) {
                Toast.makeText(
                    this,
                    "Please select your gender",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!termsCheckbox.isChecked) {
                Toast.makeText(
                    this,
                    "Please confirm your details",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val selectedGender =
                findViewById<RadioButton>(selectedGenderId)
                    .text.toString()

            val selectedCourse =
                courseSpinner.selectedItem.toString()

            resultText.visibility = android.view.View.VISIBLE
            resultText.text = """
                Registration Successful!

                Name: $name
                USN: $usn
                Gender: $selectedGender
                Course: $selectedCourse
            """.trimIndent()

            Toast.makeText(
                this,
                "Registration submitted successfully",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}