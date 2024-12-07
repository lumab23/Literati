package com.aula.literatiapp.presentation.screens.tags

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection
import com.aula.literatiapp.presentation.screens.tags.components.TagsSection

@Composable
fun TagsScreen(
    navController: NavController
) {
    val predefinedTags = listOf(
        "Quero Ler",
        "Abandonei",
        "Estou lendo",
        "Quero Trocar",
        "Completo",
        "Favoritos"
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.tags), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            predefinedTags.forEach { tag ->
                TagsSection(
                    categories = listOf(tag),
                    onClick = {
                        navController.navigate("specific_tag_screen/$tag")
                    }
                )
            }
        }
    }
}
