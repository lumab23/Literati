package com.aula.literatiapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.presentation.screens.registration.LoginScreen
import com.aula.literatiapp.presentation.screens.registration.SignUpScreen
import com.aula.literatiapp.presentation.screens.registration.viewModels.LoginViewModel
import com.aula.literatiapp.presentation.screens.registration.viewModels.SignUpViewModel

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable(Screen.Login.route) {
            LoginScreen(modifier, navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(modifier, navController)
        }
    }
}