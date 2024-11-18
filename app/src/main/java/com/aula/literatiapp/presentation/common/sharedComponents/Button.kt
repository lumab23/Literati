package com.aula.literatiapp.presentation.common.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight
import com.aula.literatiapp.presentation.ui.theme.onPrimaryLight

@Composable
fun ButtonComponent(
    value: String,
    onButtonClick: () -> Unit,
    modifier: Modifier
) {

    val textColor = MaterialTheme.colorScheme.onPrimary

    Button(
        onClick = { onButtonClick() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 65.dp, max = 70.dp)
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(18.dp),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrushLight)
                .clip(RoundedCornerShape(18.dp))
        ) {
            Text(
                text = value,
                color = textColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}