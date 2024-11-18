package com.aula.literatiapp.presentation.screens.bookDetails.components.tags

import android.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.presentation.ui.theme.backgroundLight
import com.aula.literatiapp.presentation.ui.theme.onSurfaceVariantLight
import com.aula.literatiapp.presentation.ui.theme.scrimLight

// TODO: Alterar cores
@Composable
fun SelectableChip(
    text: String,
    isSelected: Boolean,
    onSelected: (Boolean) -> Unit
) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else onSurfaceVariantLight
    val contentColor = if (isSelected) backgroundLight else scrimLight

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onSelected(!isSelected) }
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = contentColor
        )
    }
}