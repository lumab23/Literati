package com.aula.literatiapp.presentation.screens.profile.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.User
import com.aula.literatiapp.presentation.screens.settings.viewModels.SettingsViewModel
import com.aula.literatiapp.presentation.ui.theme.AppTheme

@Composable
fun ProfileSection(viewModel: SettingsViewModel) {

    val userName by viewModel.userName.collectAsState()
    val userProfilePictureUrl by viewModel.userProfilePictureUrl.collectAsState()

    fun decodeBase64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            null
        }
    }

    val profileBitmap = if (userProfilePictureUrl.isNotEmpty()) {
        decodeBase64ToBitmap(userProfilePictureUrl)
    } else {
        null
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        if (profileBitmap != null) {
            // Exibe a imagem de perfil com base no Bitmap decodificado
            AsyncImage(
                model = profileBitmap,
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Black, CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            // Exibe uma imagem padrão quando o Bitmap não está disponível
            AsyncImage(
                model = R.drawable.blank_profile_pic,
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Black, CircleShape)
            )
        }


        Text(
            text = userName,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "@$userName",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 4.dp)
        )

    }

}
