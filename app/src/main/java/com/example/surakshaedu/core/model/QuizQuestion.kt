package com.example.surakshaedu.core.model

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int
)
