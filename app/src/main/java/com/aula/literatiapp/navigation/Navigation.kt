package com.aula.literatiapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.model.Community
import com.aula.literatiapp.screens.AVirScreen
import com.aula.literatiapp.screens.BiografiaScreen
import com.aula.literatiapp.screens.BookScreen
import com.aula.literatiapp.screens.ClassicosScreen
import com.aula.literatiapp.screens.CommunityScreen
import com.aula.literatiapp.screens.CreateCommunityScreen
import com.aula.literatiapp.screens.EspCommunityScreen
import com.aula.literatiapp.screens.FantasiaScreen
import com.aula.literatiapp.screens.FiccaoCientificaScreen
import com.aula.literatiapp.screens.ForgotPasswordScreen
import com.aula.literatiapp.screens.GenresScreen
import com.aula.literatiapp.screens.LoginScreen
import com.aula.literatiapp.screens.MainScreen
import com.aula.literatiapp.screens.MisterioScreen
import com.aula.literatiapp.screens.MyBooksScreen
import com.aula.literatiapp.screens.NaoFiccaoScreen
import com.aula.literatiapp.screens.PoesiaScreen
import com.aula.literatiapp.screens.ProfileScreen
import com.aula.literatiapp.screens.ReleaseDateScreen
import com.aula.literatiapp.screens.RomanceScreen
import com.aula.literatiapp.screens.Screen1980s
import com.aula.literatiapp.screens.Screen1990s
import com.aula.literatiapp.screens.Screen2000s
import com.aula.literatiapp.screens.Screen2010s
import com.aula.literatiapp.screens.Screen2020s
import com.aula.literatiapp.screens.SearchScreen
import com.aula.literatiapp.screens.SignUpScreen
import com.aula.literatiapp.screens.TerrorScreen
import com.aula.literatiapp.screens.YoungAdultScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SignUpScreen.route) {
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(navController = navController)
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        
        composable(route = Screen.BookScreen.route) { 
            BookScreen(navController = navController)
        }
        
        composable(route = Screen.SearchScreen.route) { 
            SearchScreen(navController = navController)
        }

        composable(route = Screen.CommunityScreen.route) {
            CommunityScreen(navController = navController)
        }

        composable(route = Screen.MyBooksScreen.route) {
            MyBooksScreen(navController = navController)
        }

        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = Screen.ReleaseDateScreen.route) {
            ReleaseDateScreen(navController = navController)
        }

        composable(route = Screen.GenresScreen.route) {
            GenresScreen(navController = navController)
        }

        composable(route = Screen.RomanceScreen.route) {
            RomanceScreen(navController = navController)
        }

        composable(route = Screen.NaoFiccaoScreen.route) {
            NaoFiccaoScreen(navController = navController)
        }

        composable(route = Screen.PoesiaScreen.route) {
            PoesiaScreen(navController = navController)
        }

        composable(route = Screen.FiccaoCientificaScreen.route) {
            FiccaoCientificaScreen(navController = navController)
        }

        composable(route = Screen.TerrorScreen.route) {
            TerrorScreen(navController = navController)
        }

        composable(route = Screen.BiografiaScreen.route) {
            BiografiaScreen(navController = navController)
        }

        composable(route = Screen.MisterioScreen.route) {
            MisterioScreen(navController = navController)
        }

        composable(route = Screen.FantasiaScreen.route) {
            FantasiaScreen(navController = navController)
        }

        composable(route = Screen.ClassicosScreen.route) {
            ClassicosScreen(navController = navController)
        }

        composable(route = Screen.YoungAdultScreen.route) {
            YoungAdultScreen(navController = navController)
        }

        // décadas

        composable(route = Screen.AVirScreen.route) {
            AVirScreen(navController = navController)
        }

        composable(route = Screen.Screen2020s.route) {
            Screen2020s(navController = navController)
        }

        composable(route = Screen.Screen2010s.route) {
            Screen2010s(navController = navController)
        }

        composable(route = Screen.Screen2000s.route) {
            Screen2000s(navController = navController)
        }

        composable(route = Screen.Screen1990s.route) {
            Screen1990s(navController = navController)
        }

        composable(route = Screen.Screen1980s.route) {
            Screen1980s(navController = navController)
        }

        composable(route = Screen.EspCommunityScreen.route) {
            EspCommunityScreen(navController = navController)
        }

        composable(route = Screen.CreateCommunityScreen.route) {
            CreateCommunityScreen(navController = navController)
        }

    }
}