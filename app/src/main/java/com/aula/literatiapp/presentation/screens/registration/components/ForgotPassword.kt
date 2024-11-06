package com.aula.literatiapp.presentation.screens.registration.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.ui.theme.getTextColor

@Composable
fun ForgotPasswordLink() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.forgot_password),
            style = MaterialTheme.typography.bodySmall,
            color = getTextColor(),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {  }
        )
    }
}