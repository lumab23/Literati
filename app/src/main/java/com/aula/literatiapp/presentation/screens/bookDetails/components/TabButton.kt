package com.aula.literatiapp.presentation.screens.bookDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.presentation.ui.theme.outlineVariantLight
import com.aula.literatiapp.presentation.ui.theme.scrimLight
import com.aula.literatiapp.presentation.ui.theme.surfaceBrightDark
import com.aula.literatiapp.presentation.ui.theme.surfaceBrightLight

@Composable
fun TabButton(title: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {
    val backgroundColor = surfaceBrightLight
    val borderColor = if (selected) scrimLight else outlineVariantLight

    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = title,
            color = scrimLight,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}