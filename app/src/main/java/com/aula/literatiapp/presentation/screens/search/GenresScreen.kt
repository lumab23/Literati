package com.aula.literatiapp.presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection

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
                    .padding(horizontal = 16.dp)
                    .align(Alignment.TopCenter)
            ) {
                CategorySection(
                    title = "",
                    categories = genres,
                    onCategoryClick = { selectedGenre ->

                        val bookList = when (selectedGenre) {

                            else -> {}
                        }
                        navController.navigate("genre_screen" + "/$selectedGenre") {
                            popUpTo("genre_screen") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
