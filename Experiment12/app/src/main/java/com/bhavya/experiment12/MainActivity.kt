package com.bhavya.experiment12

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var btnFetch: Button
    private lateinit var tvStatus: TextView
    private lateinit var tvResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFetch = findViewById(R.id.btnFetch)
        tvStatus = findViewById(R.id.tvStatus)
        tvResponse = findViewById(R.id.tvResponse)

        btnFetch.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        btnFetch.isEnabled = false
        tvStatus.text = "Connecting to server..."
        tvResponse.text = "Please wait..."

        Thread {
            var connection: HttpURLConnection? = null

            try {
                val url = URL("https://jsonplaceholder.typicode.com/posts/1")
                connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"
                connection.connectTimeout = 10000
                connection.readTimeout = 10000

                val responseCode = connection.responseCode

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = connection.inputStream
                        .bufferedReader()
                        .use { it.readText() }

                    val jsonObject = JSONObject(response)
                    val formattedResponse = jsonObject.toString(4)

                    runOnUiThread {
                        tvStatus.text = "Success! HTTP Status: $responseCode"
                        tvResponse.text = formattedResponse
                    }
                } else {
                    throw Exception("HTTP Error: $responseCode")
                }

            } catch (e: Exception) {
                runOnUiThread {
                    tvStatus.text = "Request Failed"
                    tvResponse.text = e.message ?: "Unable to connect to server."
                }
            } finally {
                connection?.disconnect()

                runOnUiThread {
                    btnFetch.isEnabled = true
                }
            }
        }.start()
    }
}