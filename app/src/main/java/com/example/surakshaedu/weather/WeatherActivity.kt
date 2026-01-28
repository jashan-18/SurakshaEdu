package com.example.surakshaedu.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.surakshaedu.databinding.ActivityWeatherBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    // 🔑 Replace with your OpenWeather API key
    private val apiKey = "Your API Key"
    private val city = "Jalandhar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchWeather()
    }

    private fun fetchWeather() {
        val url =
            "https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$apiKey"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                val obj = JSONObject(json!!)

                val temp = obj.getJSONObject("main").getDouble("temp")
                val condition =
                    obj.getJSONArray("weather").getJSONObject(0).getString("main")

                runOnUiThread {
                    binding.tvCity.text = city
                    binding.tvTemp.text = "$temp°C"
                    binding.tvCondition.text = condition
                }
            }
        })
    }
}
