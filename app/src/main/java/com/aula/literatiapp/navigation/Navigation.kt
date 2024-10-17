package com.aula.literatiapp.navigation

import android.os.Message
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.screens.AVirScreen
import com.aula.literatiapp.screens.AcessibilityScreen
import com.aula.literatiapp.screens.AltEmailScreen
import com.aula.literatiapp.screens.AltSenhaScreen
import com.aula.literatiapp.screens.AltUserScreen
import com.aula.literatiapp.screens.BiografiaScreen
import com.aula.literatiapp.screens.BookScreen
import com.aula.literatiapp.screens.ChatScreen
import com.aula.literatiapp.screens.ClassicosScreen
import com.aula.literatiapp.screens.CommunityList
import com.aula.literatiapp.screens.CommunityScreen
import com.aula.literatiapp.screens.CreateCommunityScreen
import com.aula.literatiapp.screens.CreatePostScreen
import com.aula.literatiapp.screens.EnableNotifScreen
import com.aula.literatiapp.screens.EspCommunityScreen
import com.aula.literatiapp.screens.FantasiaScreen
import com.aula.literatiapp.screens.FiccaoCientificaScreen
import com.aula.literatiapp.screens.ForgotPasswordScreen
import com.aula.literatiapp.screens.GenresScreen
import com.aula.literatiapp.screens.LoginScreen
import com.aula.literatiapp.screens.MainScreen
import com.aula.literatiapp.screens.MaisPopular
import com.aula.literatiapp.screens.MenorAvaliado
import com.aula.literatiapp.screens.MessageScreen
import com.aula.literatiapp.screens.MetasScreen
import com.aula.literatiapp.screens.MisterioScreen
import com.aula.literatiapp.screens.MyBooksList
import com.aula.literatiapp.screens.MyBooksScreen
import com.aula.literatiapp.screens.NaoFiccaoScreen
import com.aula.literatiapp.screens.NotificationScreen
import com.aula.literatiapp.screens.PoesiaScreen
import com.aula.literatiapp.screens.ProfileScreen
import com.aula.literatiapp.screens.ReleaseDateScreen
import com.aula.literatiapp.screens.ReviewsScreen
import com.aula.literatiapp.screens.RomanceScreen
import com.aula.literatiapp.screens.Screen1980s
import com.aula.literatiapp.screens.Screen1990s
import com.aula.literatiapp.screens.Screen2000s
import com.aula.literatiapp.screens.Screen2010s
import com.aula.literatiapp.screens.Screen2020s
import com.aula.literatiapp.screens.SearchScreen
import com.aula.literatiapp.screens.SettingsScreen
import com.aula.literatiapp.screens.SignUpScreen
import com.aula.literatiapp.screens.TagsScreen
import com.aula.literatiapp.screens.TerrorScreen
import com.aula.literatiapp.screens.YoungAdultScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
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
        
        composable(route = Screen.SettingsScreen.route) { 
            SettingsScreen(navController = navController)
        }
        
        composable(route = Screen.AltEmailScreen.route) {
            AltEmailScreen(navController = navController)
        }

        composable(route = Screen.AltSenhaScreen.route) {
            AltSenhaScreen(navController = navController)
        }
        
        composable(route = Screen.AltUserScreen.route) { 
            AltUserScreen(navController = navController)
        }
        
        composable(route = Screen.EnableNotifScreen.route) { 
            EnableNotifScreen(navController = navController)
        }
        
        composable(route = Screen.AcessibilityScreen.route) {
            AcessibilityScreen(navController = navController)
        }


        composable(route = Screen.MessageScreen.route) {
            MessageScreen(navController = navController)
        }

        composable(route = Screen.ChatScreen.route) {
            // Example data: Replace with your actual data and logic
            val messages = listOf(
                com.aula.literatiapp.model.Message(
                    "User1",
                    "https://image.url",
                    "Hello!",
                    time = "10:30",
                    isSentByUser = false
                ),
                com.aula.literatiapp.model.Message(
                    "User2",
                    "https://image.url",
                    "Hi there!",
                    time = "10:32",
                    isSentByUser = true
                )
            )

            // Example message sending handler
            val onMessageSend: (String) -> Unit = { newMessage ->
                // Handle sending message
                println("Message sent: $newMessage")
            }

            ChatScreen(
                messages = messages,
                onMessageSend = onMessageSend,
                navController = navController
            )
        }

        composable(route = Screen.MaisPopular.route) {
            MaisPopular(navController = navController)
        }

        composable(route = Screen.MenorAvaliado.route) {
            MenorAvaliado(navController = navController)
        }

        composable(route = Screen.EspCommunityScreen.route) {
            EspCommunityScreen(navController = navController)
        }

        composable(route = Screen.CreateCommunityScreen.route) {
            CreateCommunityScreen(navController = navController)
        }
        
        composable(route = Screen.MetasScreen.route) {
            MetasScreen(navController = navController)
        }

        composable(route = Screen.ReviewsScreen.route) {
            ReviewsScreen(navController = navController)
        }
        
        composable(route = Screen.CommunityList.route) { 
            CommunityList(navController = navController)
        }
        
        composable(route = Screen.NotificationScreen.route) { 
            NotificationScreen(navController = navController)
        }
        
        composable(route = Screen.MyBooksList.route) { 
            MyBooksList(navController = navController)
        }
        
        composable(route = Screen.CreatePostScreen.route) { 
            CreatePostScreen(navController = navController)
        }

        composable(route = Screen.TagsScreen.route) {
            TagsScreen(navController = navController)
        }


    }
}