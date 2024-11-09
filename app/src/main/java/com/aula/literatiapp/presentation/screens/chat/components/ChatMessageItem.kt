package com.aula.literatiapp.presentation.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.domain.model.Message

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