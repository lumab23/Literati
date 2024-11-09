package com.aula.literatiapp.presentation.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.presentation.common.sharedComponents.CustomSwitch
import com.aula.literatiapp.presentation.common.sharedComponents.DropDown
import com.aula.literatiapp.presentation.ui.theme.getTextColor

@Composable
fun CategorySectionAccessibility(
    categories: List<String>,
    onCategoryClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        categories.forEachIndexed { index, category ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.bodyMedium,
                        color = getTextColor(),
                        modifier = Modifier.weight(1f)
                    )

                    when (category) {
                        "Tema" -> {
                            CustomSwitch()
                        }
                        "Tamanho da fonte" -> {
                            DropDown()
                        }
                        "Contraste" -> {
                            CustomSwitch()
                        }
                    }
                }
            }


            if (index < categories.size - 1) {
                Spacer(modifier = Modifier.height(2.dp))
                HorizontalDivider()
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}