package com.aula.literatiapp.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.ui.theme.AppTheme
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight

@Composable
fun ProfileScreenDashboard(navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navController.navigate("settings_screen")
                },
            imageVector = Icons.Default.Settings,
            contentDescription = "settings",
            tint = MaterialTheme.colorScheme.onSurface
        )

        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .clickable {
                        navController.navigate("chat")
                    },
                imageVector = Icons.Default.ChatBubbleOutline,
                contentDescription = "chat",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

    }

}


@PreviewLightDark
@Composable
fun ProfileScreenDashboardLightPreview() {
    AppTheme() {
        ProfileScreenDashboard(navController = NavController(LocalContext.current))
    }
}

