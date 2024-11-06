package com.aula.literatiapp.presentation.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login")
    object SignUp : Screen("signUp")
    object Home : Screen("home")
    object ForgotPassword : Screen("forgotPassword")
}