package com.example.surakshaedu.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.surakshaedu.dashboard.student.StudentDashboardActivity
import com.example.surakshaedu.dashboard.teacher.TeacherDashboardActivity
import com.example.surakshaedu.databinding.ActivityRoleSelectionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RoleSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoleSelectionBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoleSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardTeacher.setOnClickListener {
            saveRole("teacher")
        }

        binding.cardStudent.setOnClickListener {
            saveRole("student")
        }
    }

    private fun saveRole(role: String) {
        val uid = auth.currentUser?.uid ?: return

        db.collection("users").document(uid)
            .update("role", role)
            .addOnSuccessListener {
                if (role == "teacher") {
                    startActivity(Intent(this, TeacherDashboardActivity::class.java))
                } else {
                    startActivity(Intent(this, StudentDashboardActivity::class.java))
                }
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Role save failed", Toast.LENGTH_SHORT).show()
            }
    }
}
