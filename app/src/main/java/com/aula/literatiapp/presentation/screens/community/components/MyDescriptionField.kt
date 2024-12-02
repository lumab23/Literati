package com.aula.literatiapp.presentation.screens.community.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyDescriptionField(
    labelValue: String,
    value: String, // Pass the current value from the parent
    onValueChange: (String) -> Unit // Callback to propagate changes
) {
    BasicTextField(
        value = value, // Bind the current value here
        onValueChange = onValueChange, // Propagate the updated value to the parent
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(150.dp)
            .background(
                color = Color.Gray.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.small
            ),
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) { // Show placeholder if value is empty
                        Text(
                            text = labelValue,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}
