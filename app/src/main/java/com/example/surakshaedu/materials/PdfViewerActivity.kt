package com.example.surakshaedu.materials

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.surakshaedu.databinding.ActivityPdfViewerBinding

class PdfViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pdfUrl = intent.getStringExtra("pdfUrl")

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$pdfUrl")
    }
}
