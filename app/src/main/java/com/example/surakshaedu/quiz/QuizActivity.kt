package com.example.surakshaedu.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.surakshaedu.core.model.QuizQuestion
import com.example.surakshaedu.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding

    private var currentIndex = 0
    private var score = 0

    private val questions = listOf(
        QuizQuestion(
            "What should you do during an earthquake?",
            listOf("Run outside", "Hide under table", "Use lift", "Stand near window"),
            1
        ),
        QuizQuestion(
            "What is safest during flood?",
            listOf("Drive fast", "Stay on bridge", "Move to higher ground", "Swim"),
            2
        ),
        QuizQuestion(
            "Which is the safest place during a fire?",
            listOf("Near windows", "In lift", "Use stairs", "Under bed"),
            2
        ),
        QuizQuestion(
            "What number should you dial in India for emergency help?",
            listOf("100", "101", "112", "108"),
            2
        ),
        QuizQuestion(
            "What should you do if gas leakage is detected?",
            listOf(
                "Turn on lights",
                "Use mobile phone",
                "Open windows and leave area",
                "Light a matchstick"
            ),
            2
        ),
        QuizQuestion(
            "Which item is MOST important in a disaster emergency kit?",
            listOf("Video games", "Snacks only", "First aid kit", "Books"),
            2
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadQuestion()

        binding.btnNext.setOnClickListener {

            // 🔒 Prevent skipping question
            if (!checkAnswer()) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (currentIndex < questions.size - 1) {
                currentIndex++
                loadQuestion()
            } else {
                // Quiz finished
                val intent = Intent(this, QuizResultActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        if (currentIndex >= questions.size) return

        val q = questions[currentIndex]
        binding.tvQuestion.text = q.question
        binding.option1.text = q.options[0]
        binding.option2.text = q.options[1]
        binding.option3.text = q.options[2]
        binding.option4.text = q.options[3]
        binding.radioGroup.clearCheck()
    }

    private fun checkAnswer(): Boolean {
        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) return false

        val selectedIndex =
            binding.radioGroup.indexOfChild(findViewById<RadioButton>(selectedId))

        if (selectedIndex == questions[currentIndex].correctIndex) {
            score++
        }
        return true
    }
}
