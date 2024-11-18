package com.aula.literatiapp.presentation.screens.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.Message
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.TextField
import com.aula.literatiapp.presentation.screens.chat.components.ChatHeader
import com.aula.literatiapp.presentation.screens.chat.components.ChatInputField
import com.aula.literatiapp.presentation.screens.chat.components.ChatMessageItem
import com.google.ai.client.generativeai.type.content

@Composable
fun ChatScreen(
    messages: List<Message>,
    onMessageSend: (String) -> Unit,
    navController: NavController,
    user: String?
) {

    var messageText by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            ChatHeader(navController = navController, user = user)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                reverseLayout = true, // mensagens mais recentes no final
                contentPadding = PaddingValues(8.dp)
            ) {
                items(messages) { message ->
                    ChatMessageItem(message = message)
                }
            }

            ChatInputField(
                messageText = messageText,
                onMessageChange = { messageText = it },
                onSendClick = {
                    if (messageText.isNotBlank()) {
                        onMessageSend(messageText)
                        messageText = "" // limpa o campo depois de enviar a mensagem
                    }
                }
            )
        }
    }
}