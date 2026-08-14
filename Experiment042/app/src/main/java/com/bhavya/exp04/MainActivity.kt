package com.bhavya.exp04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val username = findViewById<EditText>(R.id.etUsername)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val enteredUsername = username.text.toString()

            val intent = Intent(this, HomeActivity::class.java)

            intent.putExtra("username", enteredUsername)

            startActivity(intent)
        }
    }
}