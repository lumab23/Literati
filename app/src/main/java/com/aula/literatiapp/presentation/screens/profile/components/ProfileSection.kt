package com.aula.literatiapp.presentation.screens.profile.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aula.literatiapp.domain.model.User
import com.aula.literatiapp.presentation.ui.theme.AppTheme

@Composable
fun ProfileSection(user: User) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        AsyncImage(
            model = user.profilePictureUrl,
            contentDescription = "profile picture",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface),
        )

        Log.d("username", user.username)
        Text(
            text = user.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = user.username,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 4.dp)
        )

    }

}

@PreviewLightDark
@Composable
fun ProfileSectionPreview() {
    AppTheme {
        ProfileSection(user = User(
            id = "1",
            name = "Luma",
            username = "@mrdarcy",
            email = "garfield@myownpersonaldomain.com",
            profilePictureUrl = "https://i.pinimg.com/564x/ce/fd/c0/cefdc01ca32e9cb0b5a5742dbea7f337.jpg"
        ))
    }
}