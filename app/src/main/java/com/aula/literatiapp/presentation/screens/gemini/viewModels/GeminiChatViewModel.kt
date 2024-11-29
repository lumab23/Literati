package com.aula.literatiapp.presentation.screens.gemini.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aula.literatiapp.BuildConfig
import com.aula.literatiapp.domain.model.MessageModel
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GeminiChatViewModel : ViewModel() {

    //private val apiKey = BuildConfig.GEMINI_API_KEY


    var prompt = mutableStateOf("")
        private set

    var resposta = mutableStateOf("")
        private set

    var messages = mutableStateListOf<MessageModel>()
        private set


    fun callIA(value: String) {
        prompt.value = value

        messages.add(MessageModel(message = value, role = "user"))

        viewModelScope.launch {
            try {
                val result = GeminiIA(value)
                Log.d("GeminiChatViewModel", "Gemini IA response: $result")

                messages.add(MessageModel(message = result, role = "model"))
                resposta.value = result

            } catch (e: Exception) {
                Log.e("GeminiChatViewModel", "Error calling Gemini IA: ${e.message}")
                resposta.value = "Error: ${e.message}"
                e.printStackTrace()
            }
        }
    }

    private suspend fun GeminiIA(value: String): String {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = ""
        )

        val response = generativeModel.generateContent(value)
        return response.text.toString()
    }

    fun clearChatHistoy() {
        messages.clear()
    }

}