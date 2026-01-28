package com.example.surakshaedu.dashboard.student

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.surakshaedu.R
import com.example.surakshaedu.ai.AiChatActivity
import com.example.surakshaedu.databinding.ActivityStudentDashboardBinding
import com.example.surakshaedu.materials.StudentMaterialListActivity
import com.example.surakshaedu.profile.ProfileActivity
import com.example.surakshaedu.quiz.QuizActivity
import com.example.surakshaedu.video.VideoSectionActivity
import com.example.surakshaedu.weather.WeatherActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StudentDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDashboardBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadStudentName()

        binding.cardAI.setOnClickListener {
            startActivity(Intent(this, AiChatActivity::class.java))
        }

        binding.cardMaterials.setOnClickListener {
            startActivity(Intent(this, StudentMaterialListActivity::class.java))
        }

        binding.cardQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        binding.cardWeather.setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }

        // 🎥 Safety Video Card (YouTube)
        binding.cardVideo.setOnClickListener {
            startActivity(Intent(this, VideoSectionActivity::class.java))
        }



        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> true
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun loadStudentName() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection("users").document(uid)
            .get()
            .addOnSuccessListener {
                binding.tvStudentName.text =
                    it.getString("name") ?: "Student"
            }
    }
}
