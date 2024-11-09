package com.aula.literatiapp.presentation.screens.library.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.ProfileImageComponent
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight
import com.aula.literatiapp.presentation.ui.theme.onSurfaceLight
import com.aula.literatiapp.presentation.ui.theme.surfaceContainerLight

@Composable
fun ChallengeBox(modifier: Modifier, meta: String) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainerLight
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.desafio),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            // Espaçamento entre o título e o conteúdo
            Spacer(modifier = Modifier.height(16.dp))

            // Segundo Row
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp), // Espaçamento entre a imagem e a coluna
                verticalAlignment = Alignment.CenterVertically // Alinha os elementos ao centro verticalmente
            ) {

                // Imagem de perfil
                ProfileImageComponent(
                    painter = rememberAsyncImagePainter(model = "https://i.pinimg.com/enabled_hi/564x/35/d8/3d/35d83d2e796d5ae4558396ba4adf2cc8.jpg"),
                    onProfileClick = { },
                    navController = rememberNavController(),
                    modifier = Modifier.alignBy { it.measuredHeight } // Alinha pela altura da segunda coluna
                )

                // Texto e Barra de Progresso
                Column(
                    modifier = Modifier.alignBy { it.measuredHeight } // Alinha pela altura da imagem
                ) {
                    Text(
                        text = meta,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(8.dp)
                    )

                    CustomProgressBar(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(20.dp),
                        width = 200.dp,
                        backgroundColor = onSurfaceLight,
                        foregroundColor = gradientBrushLight,
                        percent = 0,
                        isShownText = true
                    )
                }
            }
        }
    }
}