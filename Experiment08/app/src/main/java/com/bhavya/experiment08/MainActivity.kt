package com.bhavya.experiment08

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var urlInput: EditText
    private lateinit var goButton: Button
    private lateinit var webView: WebView
    private lateinit var loadingProgress: ProgressBar
    private lateinit var statusText: TextView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlInput = findViewById(R.id.urlInput)
        goButton = findViewById(R.id.goButton)
        webView = findViewById(R.id.webView)
        loadingProgress = findViewById(R.id.loadingProgress)
        statusText = findViewById(R.id.statusText)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(
                view: WebView?,
                url: String?,
                favicon: android.graphics.Bitmap?
            ) {
                loadingProgress.visibility = View.VISIBLE
                statusText.text = "Loading page..."
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                loadingProgress.visibility = View.GONE
                statusText.text = url ?: "Page loaded"
                urlInput.setText(url ?: "")
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, progress: Int) {
                loadingProgress.progress = progress
                loadingProgress.visibility =
                    if (progress < 100) View.VISIBLE else View.GONE
            }
        }

        goButton.setOnClickListener {
            loadEnteredUrl()
        }

        urlInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO ||
                actionId == EditorInfo.IME_ACTION_DONE
            ) {
                loadEnteredUrl()
                true
            } else {
                false
            }
        }

        // Load a starting page.
        urlInput.setText("https://www.wikipedia.org")
        webView.loadUrl("https://www.wikipedia.org")
    }

    private fun loadEnteredUrl() {
        var url = urlInput.text.toString().trim()

        if (url.isEmpty()) {
            urlInput.error = "Please enter a website URL"
            urlInput.requestFocus()
            return
        }

        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "https://$url"
        }

        try {
            val parsedUri = android.net.Uri.parse(url)
            if (parsedUri.host.isNullOrBlank()) {
                urlInput.error = "Please enter a valid website URL"
                return
            }

            webView.loadUrl(url)
            statusText.text = "Loading $url"
        } catch (_: Exception) {
            Toast.makeText(
                this,
                "Please enter a valid website URL",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.menuHome -> {
                webView.loadUrl("https://www.wikipedia.org")
                true
            }

            R.id.menuAbout -> {
                AlertDialog.Builder(this)
                    .setTitle("About Web Explorer")
                    .setMessage(
                        "Web Explorer demonstrates Android Options Menus " +
                                "and WebView by loading websites inside the app."
                    )
                    .setPositiveButton("OK", null)
                    .show()
                true
            }

            R.id.menuExit -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (::webView.isInitialized) {
            webView.stopLoading()
            webView.destroy()
        }
        super.onDestroy()
    }
}