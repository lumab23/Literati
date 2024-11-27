package com.aula.literatiapp.presentation.common.sharedComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.presentation.ui.theme.getTextColor

@Composable
fun CategorySection(
    title: String,
    categories: List<String>,
    onCategoryClick: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        categories.forEachIndexed { index, category ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategoryClick(category) }
                    .padding(vertical = 5.dp)
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = if (category == "Excluir Conta") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            if (index < categories.size - 1) {
                HorizontalDivider()
            }

        }

    }

}