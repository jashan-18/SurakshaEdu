package com.example.surakshaedu.dashboard.teacher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.surakshaedu.R
import com.example.surakshaedu.databinding.ActivityTeacherDashboardBinding
import com.example.surakshaedu.materials.TeacherUploadMaterialActivity
import com.example.surakshaedu.profile.ProfileActivity
import com.example.surakshaedu.quiz.StudentScoreActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TeacherDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherDashboardBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTeacherDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 Load real teacher name
        loadTeacherName()

        // 🔹 Upload Learning Material
        binding.cardUploadMaterial.setOnClickListener {
            startActivity(Intent(this, TeacherUploadMaterialActivity::class.java))
        }

        // 🔹 View Student Scores
        binding.cardViewScores.setOnClickListener {
            startActivity(Intent(this, StudentScoreActivity::class.java))
        }

        // 🔹 Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true

                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }

    // 🔥 Fetch teacher name from Firestore
    private fun loadTeacherName() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                binding.tvTeacherName.text =
                    document.getString("name") ?: "Teacher"
            }
    }
}
