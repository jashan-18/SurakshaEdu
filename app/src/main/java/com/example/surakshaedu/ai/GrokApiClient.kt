package com.example.surakshaedu.ai

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

object GroqApiClient {

    private const val API_KEY = "Your API Key"
    private const val URL = "https://api.groq.com/openai/v1/chat/completions"

    private val client = OkHttpClient()

    fun askAI(
        userMessage: String,
        callback: (String) -> Unit
    ) {
        val json = JSONObject().apply {
            put("model", "llama-3.1-8b-instant")
            put(
                "messages",
                JSONArray().put(
                    JSONObject().apply {
                        put("role", "user")
                        put("content", userMessage)
                    }
                )
            )
        }

        val body = RequestBody.create(
            "application/json".toMediaType(),
            json.toString()
        )

        val request = Request.Builder()
            .url(URL)
            .addHeader("Authorization", "Bearer $API_KEY")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                callback("Network error. Please check internet.")
            }

            override fun onResponse(call: Call, response: Response) {
                val raw = response.body?.string()

                if (raw.isNullOrBlank()) {
                    callback("Empty AI response.")
                    return
                }

                try {
                    val json = JSONObject(raw)
                    val reply = json
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content")

                    callback(reply)

                } catch (e: Exception) {
                    callback("Failed to parse AI response.")
                }
            }
        })
    }
}
