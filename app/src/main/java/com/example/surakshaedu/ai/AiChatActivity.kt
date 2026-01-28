package com.example.surakshaedu.ai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.surakshaedu.databinding.ActivityAiChatBinding

class AiChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAiChatBinding
    private val messages = mutableListOf<ChatMessage>()
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAiChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ChatAdapter(messages)
        binding.recyclerChat.layoutManager = LinearLayoutManager(this)
        binding.recyclerChat.adapter = adapter

        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                sendMessage(text)
                binding.etMessage.text.clear()
            }
        }
    }

    private fun sendMessage(text: String) {

        // User message
        messages.add(ChatMessage(text, true))
        adapter.notifyDataSetChanged()
        scrollToBottom()

        // Typing indicator
        messages.add(ChatMessage("AI is typing...", false))
        adapter.notifyDataSetChanged()
        scrollToBottom()
        GroqApiClient.askAI(text) { aiReply ->
            runOnUiThread {

                if (messages.isNotEmpty() &&
                    messages.last().message == "AI is typing..."
                ) {
                    messages.removeAt(messages.size - 1)
                }

                messages.add(ChatMessage(aiReply, false))
                adapter.notifyDataSetChanged()
                scrollToBottom()
            }
        }

    }


    private fun scrollToBottom() {
        binding.recyclerChat.scrollToPosition(messages.size - 1)
    }

    private fun getAiReply(userText: String): String {
        return when {
            userText.contains("earthquake", true) ->
                "During an earthquake, drop, cover, and hold under a sturdy table."
            userText.contains("flood", true) ->
                "Move to higher ground and avoid flood water."
            userText.contains("fire", true) ->
                "Use stairs, not lifts, and stay low."
            else ->
                "I can help with disaster safety. Ask about earthquakes, floods, or fires."
        }
    }
}
