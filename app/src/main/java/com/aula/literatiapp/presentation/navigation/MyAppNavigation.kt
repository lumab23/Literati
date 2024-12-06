package com.aula.literatiapp.presentation.navigation

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.aula.literatiapp.presentation.common.sharedViewModels.TagsViewModel
import com.aula.literatiapp.presentation.screens.bookDetails.BookScreen
import com.aula.literatiapp.presentation.screens.chat.ChatScreen
import com.aula.literatiapp.presentation.screens.chat.Conversation
import com.aula.literatiapp.presentation.screens.community.CommunityList
import com.aula.literatiapp.presentation.screens.community.CommunityScreen
import com.aula.literatiapp.presentation.screens.community.CreateCommunityScreen
import com.aula.literatiapp.presentation.screens.community.MakePostScreen
import com.aula.literatiapp.presentation.screens.community.ModerationScreen
import com.aula.literatiapp.presentation.screens.community.SpecificCommunityInfo
import com.aula.literatiapp.presentation.screens.community.SpecificCommunityScreen
import com.aula.literatiapp.presentation.screens.gemini.GeminiChatScreen
import com.aula.literatiapp.presentation.screens.gemini.viewModels.GeminiChatViewModel
import com.aula.literatiapp.presentation.screens.home.HomeScreen
import com.aula.literatiapp.presentation.screens.library.LibraryScreen
import com.aula.literatiapp.presentation.screens.profile.ProfileScreen
import com.aula.literatiapp.presentation.screens.profile.viewModels.ProfileViewModel
import com.aula.literatiapp.presentation.screens.registration.LoginScreen
import com.aula.literatiapp.presentation.screens.registration.SignUpScreen
import com.aula.literatiapp.presentation.screens.registration.viewModels.LoginViewModel
import com.aula.literatiapp.presentation.screens.registration.viewModels.SignUpViewModel
import com.aula.literatiapp.presentation.screens.search.BooksListScreen
import com.aula.literatiapp.presentation.screens.search.DecadeScreen
import com.aula.literatiapp.presentation.screens.search.GenresScreen
import com.aula.literatiapp.presentation.screens.search.ReleaseDateScreen
import com.aula.literatiapp.presentation.screens.search.SearchScreen
import com.aula.literatiapp.presentation.screens.search.SpecificGenreScreen
import com.aula.literatiapp.presentation.screens.search.viewModels.SearchViewModel
import com.aula.literatiapp.presentation.screens.settings.AccessibilityScreen
import com.aula.literatiapp.presentation.screens.settings.AltEmailScreen
import com.aula.literatiapp.presentation.screens.settings.AltPasswordScreen
import com.aula.literatiapp.presentation.screens.settings.AltUserScreen
import com.aula.literatiapp.presentation.screens.settings.SettingsScreen
import com.aula.literatiapp.presentation.screens.settings.UploadProfilePicture
import com.aula.literatiapp.presentation.screens.settings.viewModels.SettingsViewModel
import com.aula.literatiapp.presentation.screens.tags.SpecificTagScreen
import com.aula.literatiapp.presentation.screens.tags.TagsScreen

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val searchViewModel: SearchViewModel = viewModel()
    val settingsViewModel: SettingsViewModel = viewModel()
    val geminiChatViewModel: GeminiChatViewModel = viewModel()
    val tagsViewModel: TagsViewModel = viewModel()
    val profileViewModel: ProfileViewModel = viewModel()


    NavHost(navController = navController, startDestination = "login") {
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
        composable(Screen.GenresScreen.route) {
            GenresScreen(navController)
        }

        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }

        composable("gemini_chat") {
            Log.d("AppNavHost", "Navigating to Gemini Chat screen.")
            GeminiChatScreen(navController = navController, viewModel = geminiChatViewModel)
        }

        composable("release_date_screen") {
            ReleaseDateScreen(navController = navController, searchViewModel = searchViewModel)
        }

        composable("decade_screen/{decadeName}") { backStackEntry ->
            val decadeName = backStackEntry.arguments?.getString("decadeName") ?: "Unknown"
            val books = searchViewModel.bookList.collectAsState().value
            DecadeScreen(navController = navController, decadeName = decadeName)
        }

        composable("book_list_screen?type={type}",
            arguments = listOf(navArgument("type") { type = NavType.StringType }))
        { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "popular"
            BooksListScreen(navController = navController, type = type, searchViewModel = searchViewModel)
        }

        composable("genres_screen") {
            GenresScreen(navController = navController)
        }

        composable("genre_screen/{genreName}") { backStackEntry ->
            val genreName = backStackEntry.arguments?.getString("genreName") ?: ""
            SpecificGenreScreen(
                navController = navController,
                genreName = genreName,
                searchViewModel = searchViewModel
            )
        }

        composable("updateEmail_screen") {
            AltEmailScreen(navController = navController, settingsViewModel = settingsViewModel)
        }

        composable("updatePassword_screen") {
            AltPasswordScreen(navController = navController, settingsViewModel = settingsViewModel)
        }

        composable("updateUser_screen") {
            AltUserScreen(navController = navController, settingsViewModel = settingsViewModel)
        }

        composable("accessibility_screen") {
            AccessibilityScreen(navController = navController)
        }

        composable("uploadProfilePicture_screen") {
            UploadProfilePicture(
                navController = navController,
                settingsViewModel = settingsViewModel,
                hostState = remember { SnackbarHostState() }
            )
        }


        composable("library_screen") {
            LibraryScreen(
                navController = navController,
                tagsViewModel = tagsViewModel,
            )
        }

        composable(Screen.TagsScreen.route) {
            TagsScreen(navController = navController)
        }

        composable("specific_tag_screen/{tag}") { backStackEntry ->
            val tag = backStackEntry.arguments?.getString("tag") ?: ""
            SpecificTagScreen(
                tag = tag,
                navController = navController
            )

        }


        composable("chat/{channelId}&{channelName}", arguments = listOf(
            navArgument("channelId") {
                type = NavType.StringType
            },
            navArgument("channelName") {
                type = NavType.StringType
            }
        )) {
            val channelId = it.arguments?.getString("channelId") ?: ""
            val channelName = it.arguments?.getString("channelName") ?: ""
            ChatScreen(navController = navController)
        }

        composable(Screen.ConversationScreen.route,
            arguments = listOf(navArgument("channelId") { type = NavType.StringType })
        ) { backStackEntry ->
            val channelId = backStackEntry.arguments?.getString("channelId") ?: ""
            Conversation(navController = navController, channelId = channelId)
        }




        composable(
            Screen.CreateCommunityScreen.route,
            arguments = listOf(navArgument("parentCommunityId") { type = NavType.StringType })
        ) { backStackEntry ->
            val parentCommunityId = backStackEntry.arguments?.getString("parentCommunityId")
            CreateCommunityScreen(navController, parentCommunityId)
        }

        composable(
            Screen.CommunityList.route,
            arguments = listOf(navArgument("parentCommunityId") { type = NavType.StringType })
        ) { backStackEntry ->
            val parentCommunityId = backStackEntry.arguments?.getString("parentCommunityId")
            CommunityList(navController = navController, parentCommunityId = parentCommunityId)
        }

        composable(Screen.CommunityScreen.route) {
            CommunityScreen(navController)
        }

        composable(
            Screen.SpecificCommunityScreen.route,
            arguments = listOf(
                navArgument("parentCommunityId") { type = NavType.StringType },
                navArgument("communityId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val parentCommunityId = backStackEntry.arguments?.getString("parentCommunityId")
            val communityId = backStackEntry.arguments?.getString("communityId")
            if (parentCommunityId != null && communityId != null) {
                SpecificCommunityScreen(
                    parentCommunityId = parentCommunityId,
                    communityId = communityId,
                    navController = navController
                )
            }
        }

        composable(
            Screen.SpecificCommunityInfo.route,
            arguments = listOf(
                navArgument("parentCommunityId") { type = NavType.StringType },
                navArgument("communityId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val parentCommunityId = backStackEntry.arguments?.getString("parentCommunityId")
            val communityId = backStackEntry.arguments?.getString("communityId")
            if (parentCommunityId != null && communityId != null) {
                SpecificCommunityInfo(
                    parentCommunityId = parentCommunityId,
                    communityId = communityId,
                    navController = navController
                )
            }
        }
        composable(
            Screen.ModerationScreen.route,
            arguments = listOf(
                navArgument("parentCommunityId") { type = NavType.StringType },
                navArgument("communityId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val parentCommunityId = backStackEntry.arguments?.getString("parentCommunityId")
            val communityId = backStackEntry.arguments?.getString("communityId")

            if (parentCommunityId != null && communityId != null) {
                ModerationScreen(
                    parentCommunityId = parentCommunityId,
                    communityId = communityId,
                    navController = navController
                )
            }
        }
        composable(
            route = Screen.MakePostScreen.route,
            arguments = listOf(
                navArgument("parentCommunityId") { type = NavType.StringType },
                navArgument("communityId") { type = NavType.StringType },
                navArgument("parentPostId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val parentCommunityId = backStackEntry.arguments?.getString("parentCommunityId")!!
            val communityId = backStackEntry.arguments?.getString("communityId")!!
            val parentPostId = backStackEntry.arguments?.getString("parentPostId")

            MakePostScreen(
                parentCommunityId = parentCommunityId,
                communityId = communityId,
                parentPostId = parentPostId,
                navController = navController,
                onCancel = { navController.popBackStack() },
                onPost = { content ->
                    println("Post content: $content")
                    navController.popBackStack()
                }
            )
        }


        composable("profile_screen/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            ProfileScreen(userId = userId, navController = navController)
        }


        composable(Screen.Profile.route) {
            ProfileScreen(
                userId = it.arguments?.getString("userId") ?: "",
                navController = navController
            )
        }





    }
}