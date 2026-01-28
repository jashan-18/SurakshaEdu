package com.example.surakshaedu.dashboard.teacher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surakshaedu.core.model.QuizResult
import com.example.surakshaedu.databinding.ItemStudentScoreCardBinding

class StudentScoreAdapter(
    private val list: List<QuizResult>
) : RecyclerView.Adapter<StudentScoreAdapter.ScoreViewHolder>() {

    inner class ScoreViewHolder(val binding: ItemStudentScoreCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val binding = ItemStudentScoreCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val result = list[position]
        holder.binding.tvStudentName.text = result.studentName
        holder.binding.tvQuizTitle.text = "Quiz: ${result.quizTitle}"
        holder.binding.tvScore.text = "Score: ${result.score} / ${result.total}"
    }

    override fun getItemCount() = list.size
}
