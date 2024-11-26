package com.aula.literatiapp.presentation.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ChannelItem(channelName: String, onClick: () -> Unit    ) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 2.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color.DarkGray)
        ,
        verticalAlignment = Alignment.CenterVertically)
    {
        Box(modifier = Modifier
            .padding(8.dp)
            .size(70.dp)
            .clip(CircleShape)
            .background(Color.Yellow)
            .clickable { onClick() }
        ){
            Text(text = channelName[0].toString(),
                modifier = Modifier
                    .padding(16.dp)
                    .size(70.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
                    .background(Color.Yellow),
                fontSize = 35.sp,
                color = Color.White,
                style = TextStyle(fontSize = 35.sp),
                textAlign = TextAlign.Center
            )
            Text(text = channelName,
                modifier = Modifier
                    .padding(8.dp),
                color = Color.White
            )
        }
    }
}