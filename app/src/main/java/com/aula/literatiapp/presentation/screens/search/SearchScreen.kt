package com.aula.literatiapp.presentation.screens.search

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection
import com.aula.literatiapp.presentation.common.sharedComponents.MainDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.SearchBarComponent

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
            MainDashboard(modifier = Modifier, value = stringResource(id = R.string.search), fontSize = 20.sp)
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
                    value = stringResource(id = R.string.pesquise_por),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                CategorySection(
                    title = stringResource(id = R.string.pesquise_por),
                    categories = categories,
                    onCategoryClick = { selectedCategory ->
                        when (selectedCategory) {
                                "Data de lançamento" -> {
                                    navController.navigate("release_date_screen")
                                }
                                "Gêneros" -> {
                                    navController.navigate("genres_screen")
                                }
                                "Mais popular" -> {
                                    navController.navigate("book_list_screen" + "?type=popular")
                                }
                                "Menor avaliado" -> {
                                    navController.navigate("book_list_screen" + "?type=lowRated")
                                }
                            }
                    }
                )
            }
        }
    }
}