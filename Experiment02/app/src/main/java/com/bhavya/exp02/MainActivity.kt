package com.bhavya.exp02

import android.util.Log
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val TAG = "LifecycleDemo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        CustomToast.show(this, "onCreate()")
        Log.d(TAG, "onCreate() called")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onStart() {
        super.onStart()
        CustomToast.show(this, "onStart()")
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        CustomToast.show(this, "onResume()")
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        CustomToast.show(this, "onPause()")
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        CustomToast.show(this, "onStop()")
        Log.d(TAG, "onStop() called")
    }

    override fun onRestart() {
        super.onRestart()
        CustomToast.show(this, "onRestart()")
        Log.d(TAG, "onRestart() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        CustomToast.show(this, "onDestroy()")
        Log.d(TAG, "onDestroy() called")
    }
}