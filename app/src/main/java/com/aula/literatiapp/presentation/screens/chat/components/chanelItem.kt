package com.aula.literatiapp.presentation.screens.chat.components

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.screens.profile.viewModels.ProfileViewModel
import com.google.firebase.firestore.auth.User


@Composable
fun ChannelItem(channelName: String, onClick: () -> Unit    ) {
    val profileViewModel: ProfileViewModel = viewModel()

    val user by profileViewModel.user.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.DarkGray),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        if(user != null){
            val teste = painterResource(R.drawable.google_logo)

            val painter = rememberAsyncImagePainter(user!!.profilePictureUrl)
            Image(
                painter = teste,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .padding(16.dp)
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow)
                    .clickable { onClick() },
                contentScale = ContentScale.Crop)

            Text(
                text = user!!.name,
                modifier = Modifier
                    .padding(8.dp),
                color = Color.White
            )
            }
        }
    }

@Preview
@Composable
private fun ChanelItem() {
    ChannelItem("teste"){

    }
}