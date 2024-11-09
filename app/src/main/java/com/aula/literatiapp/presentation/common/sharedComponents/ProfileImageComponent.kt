package com.aula.literatiapp.presentation.common.sharedComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileImageComponent(
    painter: Painter,
    onProfileClick: () -> Unit,
    navController: NavController,
    modifier: Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .size(35.dp)
            .clip(CircleShape),
        onClick = { onProfileClick() }
    ) {
        Image(
            painter = painter,
            contentDescription = "null",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
    }
}