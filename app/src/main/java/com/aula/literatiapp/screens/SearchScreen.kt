package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BackNavigationDashboard
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.CategorySection
import com.aula.literatiapp.components.MainDashboard
import com.aula.literatiapp.components.ScrollableBookColumn
import com.aula.literatiapp.components.SearchBarComponent
import com.aula.literatiapp.navigation.Screen

@Composable
fun SearchScreen(navController: NavController) {

    val categories = listOf(
        "Data de lançamento",
        "Gêneros",
        "Mais popular",
        "Menor avaliado"
    )

    Scaffold(
        topBar = {
            MainDashboard(value = stringResource(id = R.string.search), fontSize = 20.sp)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(modifier = Modifier
            .padding(paddingValues)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .align(Alignment.TopCenter)
            ) {
                SearchBarComponent(
                    texto = stringResource(id = R.string.pesquise_por),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                CategorySection(
                    stringResource(id = R.string.pesquise_por),
                    categories = categories,
                    onCategoryClick = { selectedCategory ->
                        when (selectedCategory) {
                            "Data de lançamento" -> {
                                navController.navigate(Screen.ReleaseDateScreen.route)
                            }
                            "Gêneros" -> {
                                navController.navigate(Screen.GenresScreen.route)
                            }
                            "Mais popular" -> {
                                navController.navigate(Screen.MaisPopular.route)
                            }
                            "Menor avaliado" -> {
                                navController.navigate(Screen.MenorAvaliado.route)
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MaisPopular(navController: NavController) {
    val books = listOf(
        R.drawable.booklovers,
        R.drawable.it,
        R.drawable.sixofcrows
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Mais Popular", navController = navController)
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
fun MenorAvaliado(navController: NavController) {
    val books = listOf(
        R.drawable.acourtofthornsandrose,
        R.drawable.itstartswithus,
        R.drawable.notinlove
    )
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Menor Avaliado", navController = navController)
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


@Preview(
    showSystemUi = true,
    name = "Search Screen"
)
@Composable
fun SearchScreenPreview() {
    val navController = rememberNavController()
    SearchScreen(navController = navController)
}
