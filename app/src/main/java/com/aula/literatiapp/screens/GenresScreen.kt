package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BackNavigationDashboard
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.CategorySection
import com.aula.literatiapp.components.ScrollableBookColumn
import com.aula.literatiapp.navigation.Screen

@Composable
fun GenresScreen(navController: NavController) {

    val genres = listOf(
        "Romance",
        "Não-Ficção",
        "Poesia",
        "Ficção Científica",
        "Terror",
        "Biografia",
        "Mistério",
        "Fantasia",
        "Clássicos",
        "Young Adult"
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.gêneros), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.TopCenter)
            ) {

                CategorySection(
                    title = "",
                    categories = genres,
                    onCategoryClick = { selectedGenre ->
                        when (selectedGenre) {
                            "Romance" -> {
                                navController.navigate(Screen.RomanceScreen.route)
                            }
                            "Não-Ficção" -> {
                                navController.navigate(Screen.NaoFiccaoScreen.route)
                            }
                            "Poesia" -> {
                                navController.navigate(Screen.PoesiaScreen.route)
                            }
                            "Ficção Científica" -> {
                                navController.navigate(Screen.FiccaoCientificaScreen.route)
                            }
                            "Terror" -> {
                                navController.navigate(Screen.TerrorScreen.route)
                            }
                            "Biografia" -> {
                                navController.navigate(Screen.BiografiaScreen.route)
                            }
                            "Mistério" -> {
                                navController.navigate(Screen.MisterioScreen.route)
                            }
                            "Fantasia" -> {
                                navController.navigate(Screen.FantasiaScreen.route)
                            }
                            "Clássicos" -> {
                                navController.navigate(Screen.ClassicosScreen.route)
                            }
                            "Young Adult" -> {
                                navController.navigate(Screen.YoungAdultScreen.route)
                            }
                        }
                    }
                )

            }

        }

    }
}

@Composable
fun RomanceScreen(navController: NavController) {
    val books = listOf(
        R.drawable.booklovers
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Romance", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}


@Composable
fun NaoFiccaoScreen(navController: NavController) {
    val books = listOf(
        R.drawable.bride
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Não-Ficção", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}


@Composable
fun PoesiaScreen(navController: NavController) {
    val books = listOf(
        R.drawable.carrie
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Poesia", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}


@Composable
fun FiccaoCientificaScreen(navController: NavController) {
    val books = listOf(
        R.drawable.frankenstein
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Ficção Científica", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}


@Composable
fun TerrorScreen(navController: NavController) {
    val books = listOf(
        R.drawable.tbosas
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Terror", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}


@Composable
fun BiografiaScreen(navController: NavController) {
    val books = listOf(
        R.drawable.jane_eyre
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Biografia", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}


@Composable
fun MisterioScreen(navController: NavController) {
    val books = listOf(
        R.drawable.it
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Mistério", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}


@Composable
fun FantasiaScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Fantasia aqui
    val books = listOf(
        R.drawable.it,
        R.drawable.themidnightlibrary,
        R.drawable.tbosas,
        R.drawable.thereturnoftheking,
        R.drawable.hp_gobletoffiere,
        R.drawable.thehobbit,
        R.drawable.thecruelprince,
        R.drawable.theinvisiblelifeofaddielarue,
        R.drawable.thewickedking,
        R.drawable.hp_prisonerofazkaban,
        R.drawable.hp_orderofphoenix,
        R.drawable.thequeenofnothing,
        R.drawable.hp_deathlyhallows,
        R.drawable.acourtofthornsandrose
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Fantasia", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp) // Apply padding directly
        )
    }
}


@Composable
fun ClassicosScreen(navController: NavController) {
    val books = listOf(
        R.drawable.pride_and_prejudice
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Clássicos", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}


@Composable
fun YoungAdultScreen(navController: NavController) {
    val books = listOf(
        R.drawable.ikissedsharawheeler
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Young Adult", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}

