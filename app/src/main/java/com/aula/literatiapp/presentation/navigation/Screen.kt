package com.aula.literatiapp.presentation.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login")
    object SignUp : Screen("signUp")
    object Home : Screen("home_screen")
    object ForgotPassword : Screen("forgotPassword")
    object Community : Screen("community")
    object Settings : Screen("settings_screen")
    object SpecificCommunity : Screen("specificCommunity/{categoryId}") {
        fun createRoute(categoryId: String) = "specificCommunity/$categoryId"
    }
    object Profile : Screen("profile_screen")
    object SearchScreen : Screen("search_screen")
    object BookScreen : Screen("book_screen")
    object GenresScreen : Screen("genres_screen")
    object SpecificGenreScreen : Screen("genre_screen/{genreName}") {
        fun createRoute(genreName: String) = "genre_screen/$genreName"
    }

    object ReleaseDateScreen : Screen("release_date_screen")
    object DecadeScreen : Screen("decade_screen/{decadeName}") {
        fun createRoute(decadeName: String) = "decade_screen/$decadeName"
    }
    object GeminiChatScreen : Screen("gemini_chat")
    object AltEmailScreen : Screen("updateEmail_screen")
    object AltPasswordScreen : Screen("updatePassword_screen")
    object AltUsernameScreen : Screen("updateUser_screen")
    object EnableNotificationsScreen : Screen("enableNotifications_screen")
    object AccessibilityScreen : Screen("accessibility_screen")
    object UploadProfilePictureScreen : Screen("uploadProfilePicture_screen")

    object LibraryScreen : Screen("library_screen")


}