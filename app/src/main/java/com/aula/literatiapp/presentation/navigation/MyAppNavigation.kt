package com.aula.literatiapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aula.literatiapp.domain.model.User
import com.aula.literatiapp.presentation.screens.bookDetails.BookScreen
import com.aula.literatiapp.presentation.screens.bookDetails.viewModels.BookScreenViewModel
import com.aula.literatiapp.presentation.screens.home.HomeScreen
import com.aula.literatiapp.presentation.screens.profile.ProfileScreen
import com.aula.literatiapp.presentation.screens.registration.LoginScreen
import com.aula.literatiapp.presentation.screens.registration.SignUpScreen
import com.aula.literatiapp.presentation.screens.registration.viewModels.LoginViewModel
import com.aula.literatiapp.presentation.screens.registration.viewModels.SignUpViewModel
import com.aula.literatiapp.presentation.screens.search.SearchScreen
import com.aula.literatiapp.presentation.screens.search.viewModels.SearchViewModel

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val searchViewModel: SearchViewModel = viewModel()


    NavHost(navController = navController, startDestination = "search_screen") {
        composable(Screen.SearchScreen.route) {
            SearchScreen(navController, searchViewModel = searchViewModel)
        }
        composable(Screen.Login.route) {
            LoginScreen(modifier, navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(modifier, navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.BookScreen.route) {
            BookScreen(navController)
        }
    }
}