package com.aula.literatiapp.presentation.screens.chat.components

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Message
import com.aula.literatiapp.presentation.ui.theme.DarkGrey
import com.aula.literatiapp.presentation.ui.theme.Purple
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun ChatBubble(message: Message) {
    val isCurrentUser = message.senderId == Firebase.auth.currentUser?.uid
    val bubbleColor = if (isCurrentUser){
        Purple
    } else {
        DarkGrey
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        val aligment = if (!isCurrentUser) Alignment.CenterStart else Alignment.CenterEnd
        Row(
            modifier = Modifier.padding(8.dp)
                .background(color = bubbleColor, shape = RoundedCornerShape(8.dp))
                .align(aligment),
            verticalAlignment = Alignment.CenterVertically
        ){
            if(!isCurrentUser){
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "User pic",
                    modifier = Modifier
                        .padding(8.dp))
            }
            Text(
                text = message.message.trim(),
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .background(color = bubbleColor, shape = RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "User pic",
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = bubbleColor, shape = RoundedCornerShape(8.dp))
            )

        }
    }
}