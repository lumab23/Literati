package com.aula.literatiapp.presentation.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login")
    object SignUp : Screen("signUp")
    object Home : Screen("home")
    object ForgotPassword : Screen("forgotPassword")
    object Community : Screen("community")
    object SpecificCommunity : Screen("specificCommunity/{categoryId}") {
        fun createRoute(categoryId: String) = "specificCommunity/$categoryId"
    }
}