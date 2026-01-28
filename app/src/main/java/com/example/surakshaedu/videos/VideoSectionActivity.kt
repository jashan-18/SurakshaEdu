package com.example.surakshaedu.video

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.surakshaedu.databinding.ActivityVideoSectionBinding

class VideoSectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoSectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoSectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🌍 Earthquake
        binding.cardEarthquake.setOnClickListener {
            openYoutube("https://www.youtube.com/watch?v=BLEPakj1YTY")
        }

        // 🌊 Flood
        binding.cardFlood.setOnClickListener {
            openYoutube("https://www.youtube.com/watch?v=43M5mZuzHF8")
        }


        binding.cardFire.setOnClickListener {
            openYoutube("https://www.youtube.com/watch?v=1EB5i5qW3uM")
        }


        binding.cardCyclone.setOnClickListener {
            openYoutube("https://www.youtube.com/watch?v=5mA6Rk9Jk6c")
        }


        binding.cardEmergency.setOnClickListener {
            openYoutube("https://www.youtube.com/watch?v=VQnG9E7yK_s")
        }
    }

    private fun openYoutube(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
