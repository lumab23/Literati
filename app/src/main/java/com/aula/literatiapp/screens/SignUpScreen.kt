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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.AlternativeOptionAccount
import com.aula.literatiapp.components.AlternativeOptionGoogle
import com.aula.literatiapp.components.ButtonComponenent
import com.aula.literatiapp.components.HeadingTextComponent
import com.aula.literatiapp.components.MyPasswordFieldComponent
import com.aula.literatiapp.components.MyTextFieldComponent
import com.aula.literatiapp.components.NormalTextComponent
import com.aula.literatiapp.navigation.Screen

@Composable
fun SignUpScreen(navController: NavController) {

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
            HeadingTextComponent(value = stringResource(id = R.string.create_acc))

            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(labelValue = stringResource(id = R.string.nome), leadingIcon = Icons.Default.Person)
            MyTextFieldComponent(labelValue = stringResource(id = R.string.email), leadingIcon = Icons.Default.Email)
            MyPasswordFieldComponent(labelValue = stringResource(id = R.string.password), leadingIcon = Icons.Default.Lock)
            MyPasswordFieldComponent(labelValue = stringResource(id = R.string.password_again), leadingIcon = Icons.Default.Lock)

            ButtonComponenent(value = stringResource(id = R.string.entrar), route = Screen.LoginScreen.route, navController = navController)

            Spacer(modifier = Modifier.height(20.dp))

            AlternativeOptionGoogle(
                onIconClick = { /* TODO: Implementar Login com Google */ },
                value = stringResource(id = R.string.alternatives),
                //route = Screen.LoginScreen.route,
                painter = painterResource(id = R.drawable.google_logo),
                //navController = navController
            )

            Spacer(modifier = Modifier.height(16.dp))

            AlternativeOptionAccount(
                //onLinkClick = { navController.navigate(Screen.LoginScreen.route) },
                value = stringResource(id = R.string.login_alt),
                linkText = stringResource(id = R.string.link_login),
                route = Screen.LoginScreen.route,
                navController = navController,
                modifier = Modifier
            )

        }

    }

}

@Preview (
    showSystemUi = true,
    name = "SignUp Screen"
)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}