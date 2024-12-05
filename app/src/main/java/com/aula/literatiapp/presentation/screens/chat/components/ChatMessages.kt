package com.aula.literatiapp.presentation.screens.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.domain.model.Message
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.painterResource
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.ui.theme.DarkGrey


@Composable

fun ChatMessages(//
// chanelName: String,
                 messages: List<Message>,
                 onSendMessage: (String) -> Unit,
                 onImageClicked:() -> Unit) {
    val hideKeyboardController = LocalSoftwareKeyboardController.current
    val msg = remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(messages) { message ->
                ChatBubble(message = message)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(DarkGrey)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(onClick = {
                msg.value = ""
                onImageClicked()
            }) {
                Image(painter = painterResource(id = R.drawable.attach), contentDescription = "send")
            }
            TextField(
                value = msg.value,
                onValueChange = { msg.value = it },
                placeholder = { Text(text = "Type a message") },
                keyboardActions = KeyboardActions(onDone = {
                    hideKeyboardController?.hide()
                }),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = DarkGrey,
                    unfocusedContainerColor = DarkGrey,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedPlaceholderColor = Color.White,
                    unfocusedPlaceholderColor = Color.White
                ))
            IconButton(onClick = {
                onSendMessage(msg.value)
                msg.value = ""
            }) {
                Image(painter = painterResource(id = R.drawable.send), contentDescription = "send")
            }
        }
    }
}