package com.aula.literatiapp.presentation.screens.library.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R

@Composable
fun ChallengeSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalDivider()

        // Conteúdo do ChallengeComponent
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("challenge_screen") }
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.metas),
                    fontWeight = FontWeight.Normal
                )

                IconButton(onClick = {navController.navigate("challenge_screen")}) {
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "right arrow")
                }
            }
        }

        HorizontalDivider()
    }
}