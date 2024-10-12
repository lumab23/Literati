package com.aula.literatiapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.model.Message

@Composable
fun ChatScreen(messages: List<Message>, onMessageSend: (String) -> Unit, navController: NavController) {

    var messageText by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            ChatHeader(navController = navController)
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

@Composable
fun ChatHeader(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(10.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
            ) {
                Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "Back")
            }

            Spacer(modifier = Modifier.weight(0.7f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = "profile_picture",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .shadow(elevation = 8.dp),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = stringResource(id = R.string.username))
            }

            Spacer(modifier = Modifier.weight(1f))

        }

    }
}

@Composable
fun ChatInputField(
    messageText: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            value = messageText,
            onValueChange = onMessageChange,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .background(Color.White, shape = RoundedCornerShape(50)),
            placeholder = {
                Text(text = "Message...")
            },
            maxLines = 1,
            singleLine = true,
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = {
                    onSendClick()
                }
            )
        )

        if (messageText.isNotEmpty()) {
            IconButton(onClick = onSendClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "send"
                )
            }
        }

    }

}


@Composable
fun ChatMessageItem(message: Message) {

    val alignment = if (message.isSentByUser) {
        Alignment.CenterEnd
    } else {
        Alignment.CenterStart
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = alignment
    ) {
        Text(
            text = message.body,
            modifier = Modifier
                .background(
                    if (message.isSentByUser) Color.Green else Color.LightGray, // mudar as cores depois
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatInputFieldPreview() {
    var messageText by remember { mutableStateOf("") }

    ChatInputField(
        messageText = messageText,
        onMessageChange = { newText ->
            messageText = newText
        },
        onSendClick = {
            // Ação de envio pode ser simulada aqui
            println("Mensagem enviada: $messageText")
            messageText = "" // Limpar o campo de texto após enviar
        }
    )
}
