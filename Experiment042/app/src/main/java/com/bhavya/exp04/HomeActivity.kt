package com.bhavya.exp04

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val username = intent.getStringExtra("username")

        if (!username.isNullOrEmpty()) {
            tvWelcome.text = "Welcome, $username!"
        }

        btnLogout.setOnClickListener {
            finish()
        }
    }
}