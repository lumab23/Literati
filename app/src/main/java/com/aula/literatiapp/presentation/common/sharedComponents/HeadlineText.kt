package com.aula.literatiapp.presentation.common.sharedComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.presentation.ui.theme.getTextColor

@Composable
fun HeadlineText(
    value: String
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.ExtraBold,
        color = getTextColor(),
        textAlign = TextAlign.Center
    )

}

@Preview
@Composable
fun HeadlineTextPreview() {
    HeadlineText(value = "Bem Vindo de Volta!")
}