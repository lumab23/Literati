package com.aula.literatiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.screens.LoginScreen
import com.aula.literatiapp.screens.SignUpScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SignUpScreen.route) {
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen()
        }
    }
}