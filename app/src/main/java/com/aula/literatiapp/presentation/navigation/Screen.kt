package com.aula.literatiapp.presentation.navigation

sealed class Screen(val route: String) {

    //criando os objetos para a navegação de telas

    object Login : Screen("login")
    object SignUp : Screen("signUp")
    object Home : Screen("home")
    object ForgotPassword : Screen("forgotPassword")
    object Community : Screen("community")
    object SpecificCommunity : Screen("specificCommunity/{categoryId}")
    object BookScreen : Screen("bookScreen")
    object ChatScreen : Screen("chatScreen")
    object CommunityList : Screen("communityList")
    object CommunityScreen : Screen("communityScreen")
    object CreateCommunityScreen : Screen("createCommunityScreen")
    object MakePostScreen : Screen("makeAPostScreen")
    object SpecificCommunityScreen : Screen("specificCommunityScreen")
    object HomeScreen : Screen("homeScreen")
    object ChallengeScreen : Screen("challengeScreen")
    object LibraryScreen : Screen("libraryScreen")
    object ProfileScreen : Screen("profileScreen")
    object ReviewsScreen : Screen("reviewScreen")
    object BookListScreen : Screen("bookListScreen")
    object DecadeScreen : Screen("decadeScreen")
    object GenresScreen : Screen("genresScreen")
    object ReleaseDateScreen : Screen("releaseDateScreen")
    object SpecificGenreScreen : Screen("specificGenreScreen")
    object AcessibilityScreen : Screen("acessibilityScreen")
    object AltEmailScreen : Screen("altEmailScreen")
    object AltPasswordScreen : Screen("altPasswordScreen")
    object AltUserScreen : Screen("altUserScreen")
    object EnableNotifScreen : Screen("enableNotifScreen")
    object SettingsScreen : Screen("settingsScreen")
    object TagsScreen : Screen("tagsScreen")
}