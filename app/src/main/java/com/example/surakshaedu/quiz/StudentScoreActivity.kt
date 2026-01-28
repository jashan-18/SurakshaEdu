package com.example.surakshaedu.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.surakshaedu.core.model.QuizResult
import com.example.surakshaedu.dashboard.teacher.StudentScoreAdapter
import com.example.surakshaedu.databinding.ActivityStudentScoreBinding
import com.google.firebase.firestore.FirebaseFirestore

class StudentScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentScoreBinding
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerScores.layoutManager = LinearLayoutManager(this)

        // 🔥 FETCH SCORES FROM FIRESTORE
        firestore.collection("quizResults")
            .get()
            .addOnSuccessListener { result ->
                val scores = result.toObjects(QuizResult::class.java)
                binding.recyclerScores.adapter = StudentScoreAdapter(scores)
            }
            .addOnFailureListener {
                // optional: show error message
            }
    }
}
