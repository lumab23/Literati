package com.aula.literatiapp.presentation.screens.library.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.aula.literatiapp.presentation.ui.theme.getTextColor
import com.aula.literatiapp.presentation.ui.theme.secondaryContainerLight

@Composable
fun CustomProgressBar(
    modifier: Modifier,
    width: Dp,
    backgroundColor: androidx.compose.ui.graphics.Color,
    foregroundColor: Brush,
    percent: Int,
    isShownText: Boolean
) {
    Box(
        modifier = modifier
            .background(secondaryContainerLight)
            .width(width)
    ) {
        Box(
            modifier = modifier
                .background(foregroundColor)
                .width(width * percent / 100)
        )

        if (isShownText) {
            Text(
                text = "$percent %",
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = getTextColor()
            )
        }
    }
}