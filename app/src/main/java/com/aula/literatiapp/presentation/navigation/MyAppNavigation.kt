package com.aula.literatiapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.domain.model.Message
import com.aula.literatiapp.presentation.screens.bookDetails.BookScreen
import com.aula.literatiapp.presentation.screens.chat.ChatScreen
import com.aula.literatiapp.presentation.screens.community.CommunityList
import com.aula.literatiapp.presentation.screens.community.CommunityScreen
import com.aula.literatiapp.presentation.screens.community.CreateCommunityScreen
import com.aula.literatiapp.presentation.screens.community.MakePostScreen
import com.aula.literatiapp.presentation.screens.community.SpecificCommunityScreen
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel
import com.aula.literatiapp.presentation.screens.home.HomeScreen
import com.aula.literatiapp.presentation.screens.library.ChallengeScreen
import com.aula.literatiapp.presentation.screens.library.LibraryScreen
import com.aula.literatiapp.presentation.screens.profile.ProfileScreen
import com.aula.literatiapp.presentation.screens.registration.LoginScreen
import com.aula.literatiapp.presentation.screens.registration.SignUpScreen
import com.aula.literatiapp.presentation.screens.reviews.ReviewsScreen
import com.aula.literatiapp.presentation.screens.search.DecadeScreen
import com.aula.literatiapp.presentation.screens.search.GenresScreen
import com.aula.literatiapp.presentation.screens.search.ReleaseDateScreen
import com.aula.literatiapp.presentation.screens.settings.AcessibilityScreen
import com.aula.literatiapp.presentation.screens.settings.AltEmailScreen
import com.aula.literatiapp.presentation.screens.settings.AltPasswordScreen
import com.aula.literatiapp.presentation.screens.settings.AltUserScreen
import com.aula.literatiapp.presentation.screens.settings.EnableNotifScreen
import com.aula.literatiapp.presentation.screens.settings.SettingsScreen
import com.aula.literatiapp.presentation.screens.tags.TagsScreen
import com.google.gson.Gson
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "login") {
        //navHost é onde as rotas são definidas
        composable(Screen.Login.route) {
            LoginScreen(modifier, navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(modifier, navController)
        }
        composable(Screen.BookScreen.route) {
            BookScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            Screen.ChatScreen.route + "/{userId}/{messages}", // Adicionando parâmetros na rota
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("messages") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Recuperando os parâmetros da navegação
            val userId = backStackEntry.arguments?.getString("userId")
            val messagesJson = backStackEntry.arguments?.getString("messages") ?: "[]"

            // Deserializando os dados de volta para o tipo adequado (listagem de mensagens)
            val messages = Gson().fromJson(messagesJson, Array<Message>::class.java).toList()

            // Chama a tela ChatScreen com os parâmetros
            ChatScreen(
                messages = messages,
                onMessageSend = { message -> /* Defina a lógica para envio de mensagens */ },
                navController = navController,
                user = userId
            )
        }
        composable(Screen.CommunityList.route) {
            CommunityList(navController)
        }
        composable(Screen.CommunityScreen.route) {
            CommunityScreen(navController)
        }
        composable(Screen.CreateCommunityScreen.route) {
            CreateCommunityScreen(navController)
        }
        composable(
            Screen.MakePostScreen.route + "/{profileImageUrl}", // Adicionando o parâmetro profileImageUrl
            arguments = listOf(
                navArgument("profileImageUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Recuperando o parâmetro da navegação
            val profileImageUrl = backStackEntry.arguments?.getString("profileImageUrl")

            // Chama a tela MakePostScreen com os parâmetros
            MakePostScreen(
                profileImageUrl = profileImageUrl,
                onCancel = { /* Defina a lógica para o cancelamento */ },
                onPost = { postContent ->
                    /* Lógica para realizar o post com o conteúdo postContent */
                }
            )
        }

        composable(
            Screen.SpecificCommunityScreen.route + "/{communityId}", // Adicionando o parâmetro communityId
            arguments = listOf(
                navArgument("communityId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Recuperando o parâmetro da navegação
            val communityId = backStackEntry.arguments?.getString("communityId")

            // Chama a tela SpecificCommunityScreen com os parâmetros

            SpecificCommunityScreen(
                community = Community(id = communityId ?: "", specificCommunityName = "", description = "", name = "", categories = emptyList()), // Aqui você pode carregar a community com base no communityId
                navController = navController,
                viewModel = CommunityViewModel() // Passando o ViewModel aqui também
            )
        }

        composable(Screen.ChallengeScreen.route) {
            ChallengeScreen(navController)
        }
        composable(Screen.LibraryScreen.route) {
            LibraryScreen(navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(Screen.ReviewsScreen.route){
            ReviewsScreen(navController)
        }
//        composable(
//            Screen.BookListScreen.route + "/{type}", // Adicionando o parâmetro 'type' na rota
//            arguments = listOf(
//                navArgument("type") { type = NavType.StringType }
//            )
//        ) { backStackEntry ->
//            // Recuperando o parâmetro 'type' da navegação
//            val type = backStackEntry.arguments?.getString("type") ?: ""
//
//            // Chama a tela BooksListScreen com o parâmetro 'type'
//            BookListScreen(
//                navController = navController,
//                type = type
//            )
//        }

        composable(
            Screen.DecadeScreen.route + "/{decadeName}/{bookList}", // Adicionando parâmetros na rota
            arguments = listOf(
                navArgument("decadeName") { type = NavType.StringType },
                navArgument("bookList") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Recuperando os parâmetros da navegação
            val decadeName = backStackEntry.arguments?.getString("decadeName") ?: ""
            val bookListJson = backStackEntry.arguments?.getString("bookList") ?: "[]"

            // Deserializando os dados de volta para o tipo adequado (lista de livros)
            val bookList = Gson().fromJson(bookListJson, Array<Book>::class.java).toList()

            // Chama a tela DecadeScreen com os parâmetros
            DecadeScreen(
                navController = navController,
                decadeName = decadeName,
                bookList = bookList
            )
        }

        composable(Screen.GenresScreen.route) {
            GenresScreen(navController)
        }
        composable(Screen.ReleaseDateScreen.route) {
            ReleaseDateScreen(navController)
        }

        composable(Screen.AcessibilityScreen.route) {
            AcessibilityScreen(navController)
        }
        composable(Screen.AltEmailScreen.route) {
            AltEmailScreen(navController)
        }
        composable(Screen.AltPasswordScreen.route) {
            AltPasswordScreen(navController)
        }
        composable(Screen.AltUserScreen.route) {
            AltUserScreen(navController)
        }
        composable(Screen.EnableNotifScreen.route) {
            EnableNotifScreen(navController)
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen(navController)
        }
        composable(Screen.TagsScreen.route) {
            TagsScreen(navController)
        }
    }
}

