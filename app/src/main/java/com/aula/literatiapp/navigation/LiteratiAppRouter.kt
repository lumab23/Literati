package com.aula.literatiapp.navigation


sealed class Screen(val route: String) {
    data object SignUpScreen : Screen("signup_screen")
    data object LoginScreen : Screen("login_screen")
    data object ForgotPasswordScreen: Screen("forgot_password")
    data object MainScreen: Screen("main_screen")
    data object BookScreen: Screen("book_screen")
    data object SearchScreen: Screen("search_screen")
    data object MyBooksScreen: Screen("my_books_screen")
    data object CommunityScreen: Screen("community_screen")
    data object ProfileScreen: Screen("profile_screen")
    data object ReleaseDateScreen: Screen("release_date_screen")
    data object GenresScreen: Screen("genres_screen")

    // generos
    data object RomanceScreen : Screen("romance_screen")
    data object NaoFiccaoScreen : Screen("nao_ficcao_screen")
    data object PoesiaScreen : Screen("poesia_screen")
    data object FiccaoCientificaScreen : Screen("ficcao_cientifica_screen")
    data object TerrorScreen : Screen("terror_screen")
    data object BiografiaScreen : Screen("biografia_screen")
    data object MisterioScreen : Screen("misterio_screen")
    data object FantasiaScreen : Screen("fantasia_screen")
    data object ClassicosScreen : Screen("classicos_screen")
    data object YoungAdultScreen : Screen("young_adult_screen")

    // decades
    data object AVirScreen : Screen("avir_screen")
    data object Screen2020s : Screen("2020s_screen")
    data object Screen2010s : Screen("2010s_screen")
    data object Screen2000s : Screen("2000s_screen")
    data object Screen1990s : Screen("1990s_screen")
    data object Screen1980s : Screen("1980s_screen")

    // comunidade
    data object EspCommunityScreen: Screen("community_especifica")
    data object CreateCommunityScreen: Screen("create_community")

}

