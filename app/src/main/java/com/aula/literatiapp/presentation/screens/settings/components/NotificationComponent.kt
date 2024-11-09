package com.aula.literatiapp.presentation.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.domain.model.Notification
import com.aula.literatiapp.presentation.common.sharedComponents.ProfileImageComponent

@Composable
fun NotificationComponent(notifInfo: Notification, navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            ProfileImageComponent(
                painter = rememberAsyncImagePainter(model = notifInfo.userImageUrl),
                onProfileClick = {},
                navController = navController,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = notifInfo.name + " " + notifInfo.action + " " + notifInfo.nomeLivroOuComunidade,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}