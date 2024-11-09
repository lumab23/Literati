package com.aula.literatiapp.presentation.screens.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.User

@Composable
fun ChatHeader(navController: NavController, user: User) {
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
                    painter = rememberAsyncImagePainter(model = "https://i.pinimg.com/enabled_hi/564x/35/d8/3d/35d83d2e796d5ae4558396ba4adf2cc8.jpg"),
                    contentDescription = "profile_picture",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .shadow(elevation = 8.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.username
                )
            }

            Spacer(modifier = Modifier.weight(1f))

        }

    }
}