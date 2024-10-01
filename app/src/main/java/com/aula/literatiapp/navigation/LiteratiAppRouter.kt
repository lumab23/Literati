package com.aula.literatiapp.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(val route: String) {
    data object SignUpScreen : Screen("signup_screen")
    data object LoginScreen : Screen("login_screen")

}

