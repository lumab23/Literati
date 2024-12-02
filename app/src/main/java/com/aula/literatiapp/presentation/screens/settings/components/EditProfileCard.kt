package com.aula.literatiapp.presentation.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.User
import com.aula.literatiapp.presentation.ui.theme.getTextColor
import com.aula.literatiapp.presentation.ui.theme.surfaceContainerLight
import com.aula.literatiapp.presentation.ui.theme.surfaceDimLight

@Composable
fun EditProfileCard(
    navController: NavController,
    user: User,
    gradientBrush: Brush,
    textColor: androidx.compose.ui.graphics.Color
) {

    val defaultImage = "default_image.jpg"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainerLight
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        if (user.profilePictureUrl.isEmpty() || user.profilePictureUrl == defaultImage){
                            R.drawable.default_image
                        } else {
                            user.profilePictureUrl
                        })
                    .crossfade(true)
                    .build(),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(13.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = user.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = getTextColor()
                )
                //Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.username,
                    fontSize = 14.sp,
                    color = getTextColor()
                )

            }
        }
    }
}