package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
}

@Composable
fun NaoFiccaoScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Não-Ficção aqui
}

@Composable
fun PoesiaScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Poesia aqui
}

@Composable
fun FiccaoCientificaScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Ficção Científica aqui
}

@Composable
fun TerrorScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Terror aqui
}

@Composable
fun BiografiaScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Biografia aqui
}

@Composable
fun MisterioScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Mistério aqui
}

@Composable
fun FantasiaScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Fantasia aqui
}

@Composable
fun ClassicosScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Clássicos aqui
}

@Composable
fun YoungAdultScreen(navController: NavController) {
    // Adicione o conteúdo da categoria Young Adult aqui
}
