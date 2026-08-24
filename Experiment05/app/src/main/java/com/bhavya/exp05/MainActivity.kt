package com.bhavya.exp05

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "mad_lab_channel"
    private val NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        val btnNotify = findViewById<Button>(R.id.btnNotify)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        btnNotify.setOnClickListener {

            showNotification()

            tvStatus.text = "Notification Status: Sent"
        }
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                "MAD Lab Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.description = "Notifications for MAD Lab reminders"

            val notificationManager =
                getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                100
            )

            return
        }

        // Intent to open MainActivity when the notification is tapped
        val intent = Intent(this, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(
            this,
            CHANNEL_ID
        )
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("MAD Lab Reminder")
            .setContentText("Don't forget to complete your MAD Lab experiment.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this)
            .notify(NOTIFICATION_ID, notification)
    }
}