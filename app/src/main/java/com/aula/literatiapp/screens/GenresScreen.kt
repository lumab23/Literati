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
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.CategorySection
import com.aula.literatiapp.components.MenorDashboard
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
        "Classicos",
        "Young Adult"
    )

    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.gêneros), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
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
    // Adicione o conteúdo da categoria Romance aqui
    val books = listOf(
        R.drawable.booklovers

    )
    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.gêneros) , navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier .padding(paddingValues) )
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun NaoFiccaoScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Não-Ficção aqui
    val books = listOf(
        R.drawable.bride
    )

    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.gêneros), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier .padding(paddingValues) )
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun PoesiaScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Poesia aqui
    val books = listOf(
        R.drawable.carrie
    )

    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.gêneros), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun FiccaoCientificaScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Ficção Científica aqui
    val books = listOf(
        R.drawable.frankenstein

    )

    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.gêneros), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier .padding(paddingValues))
        ScrollableBookColumn(
            bookList =  books,
            navController = navController,
            modifier = Modifier
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
            MenorDashboard(value = stringResource(id = R.string.gêneros), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier .padding(paddingValues))
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun BiografiaScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Biografia aqui
    val books = listOf(
        R.drawable.jane_eyre,

    )
    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.gêneros), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier .padding(paddingValues))
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun MisterioScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Mistério aqui
    val books = listOf(
        R.drawable.it,
        R.drawable.mobydick
    )
    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.gêneros), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier .padding(paddingValues) )
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun FantasiaScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Fantasia aqui
    val books = listOf(
        R.drawable.it,
        R.drawable.americandirt
    )
    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.gêneros), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier .padding(paddingValues) )
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun ClassicosScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Clássicos aqui
    val books = listOf(
        R.drawable.americandirt
    )
    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.gêneros), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier .padding(paddingValues))
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun YoungAdultScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Young Adult aqui
    val books = listOf(
        R.drawable._984
    )
    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.gêneros), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier .padding(paddingValues))
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}
