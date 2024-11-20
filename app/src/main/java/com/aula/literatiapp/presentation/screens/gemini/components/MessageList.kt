package com.aula.literatiapp.presentation.screens.gemini.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.domain.model.MessageModel

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList : List<MessageModel>, isTyping: Boolean) {


    LazyColumn(modifier = modifier) {
        items(messageList) { message ->
            when (message.role) {
                "user" -> Text(
                    text = "You: ${message.message}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )
                "model" -> Text(
                    text = "Gemini: ${message.message}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        if (isTyping) {
            item {
                Text(
                    text = "Gemini is typing...",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

}