package com.aula.literatiapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.components.NormalTextComponent
import com.aula.literatiapp.R
import com.aula.literatiapp.components.CheckboxComponent
import com.aula.literatiapp.components.HeadingTextComponent
import com.aula.literatiapp.components.MyPasswordFieldComponent
import com.aula.literatiapp.components.MyTextFieldComponent

@Composable
fun LoginScreen() {

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.welcome_back))

            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(labelValue = stringResource(id = R.string.email), leadingIcon = Icons.Default.Email)
            MyPasswordFieldComponent(labelValue = stringResource(id = R.string.password), leadingIcon = Icons.Default.Lock)



        }

    }
}


@Preview(
    showSystemUi = true,
    name = "Login Screen"
)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}