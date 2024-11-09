package com.aula.literatiapp.presentation.screens.tags

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection

@Composable
fun TagsScreen(navController: NavController) {

    val categories = listOf(
        "Quero Ler",
        "Abandonei",
        "Estou lendo",
        "Quero Trocar",
        "Completo"
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.tags), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            item {
                CategorySection(
                    title = "",
                    categories = categories,
                    onCategoryClick = {}
                )
            }
        }

    }

}