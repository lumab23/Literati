package com.aula.literatiapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.HeadingTextComponent

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(28.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.forgot_password))
        }
    }
}