package com.example.surakshaedu.ai

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surakshaedu.databinding.ItemChatAiBinding
import com.example.surakshaedu.databinding.ItemChatUserBinding

class ChatAdapter(
    private val list: List<ChatMessage>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (list[position].isUser) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val binding = ItemChatUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            UserViewHolder(binding)
        } else {
            val binding = ItemChatAiBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            AiViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = list[position]
        if (holder is UserViewHolder) {
            holder.binding.tvUserMsg.text = msg.message
        } else if (holder is AiViewHolder) {
            holder.binding.tvAiMsg.text = msg.message
        }
    }

    override fun getItemCount() = list.size

    class UserViewHolder(val binding: ItemChatUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    class AiViewHolder(val binding: ItemChatAiBinding) :
        RecyclerView.ViewHolder(binding.root)
}
