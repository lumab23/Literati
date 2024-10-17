package com.aula.literatiapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BackNavigationDashboard
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.navigation.Screen

// adicionar um onProfileClick()
@Composable
fun MessageComponent(navController: NavController) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clickable { navController.navigate(Screen.ChatScreen.route) }
            .height(50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
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

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.username),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text(
                        text = stringResource(id = R.string.time),
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 10.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = stringResource(id = R.string.message_preview))
            }
        }
    }
}

@Composable
fun MessageScreen(navController: NavController) {
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Mensagens", navController = navController)
        },
        bottomBar = { BottomNavigation(modifier = Modifier, navController = navController) }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(vertical = 20.dp)
        ) {
            item {
                MessageComponent(navController)
                HorizontalDivider()
            }
        }

    }
}
