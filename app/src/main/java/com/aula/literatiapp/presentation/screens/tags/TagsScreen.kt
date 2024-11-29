package com.aula.literatiapp.presentation.screens.tags

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection
import com.aula.literatiapp.presentation.common.sharedViewModels.TagsViewModel

@Composable
fun TagsScreen(
    navController: NavController,
    viewModel: TagsViewModel = viewModel()
) {
    val predefinedTags = listOf(
        "Quero Ler",
        "Abandonei",
        "Estou lendo",
        "Quero Trocar",
        "Completo",
        "Favoritos"
    )

    val booksByTag by viewModel.booksByTag.collectAsState()

    LaunchedEffect(predefinedTags) {
        viewModel.loadTags()
    }

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.tags), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(predefinedTags) { tag ->
                val count = booksByTag[tag]?.size ?: 0
                CategorySection(
                    title = "$tag ($count)",
                    categories = listOf(tag),
                    onCategoryClick = {
                        navController.navigate("tag_screen/$tag")
                    }
                )
            }
        }
    }
}
