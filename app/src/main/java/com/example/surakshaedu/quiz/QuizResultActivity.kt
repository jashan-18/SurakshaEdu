package com.example.surakshaedu.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.surakshaedu.core.model.QuizResult
import com.example.surakshaedu.databinding.ActivityQuizResultBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class QuizResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizResultBinding
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        binding.tvResult.text = "Your Score: $score / $total"

        saveResultWithStudentName(score, total)

        binding.btnFinish.setOnClickListener {
            finish()
        }
    }

    private fun saveResultWithStudentName(score: Int, total: Int) {
        val uid = auth.currentUser?.uid ?: return

        firestore.collection("users").document(uid)
            .get()
            .addOnSuccessListener { doc ->
                val studentName = doc.getString("name") ?: "Unknown"

                val result = QuizResult(
                    studentName = studentName, // ✅ REAL NAME
                    quizTitle = "Disaster Safety",
                    score = score,
                    total = total
                )

                firestore.collection("quizResults").add(result)
            }
    }
}
