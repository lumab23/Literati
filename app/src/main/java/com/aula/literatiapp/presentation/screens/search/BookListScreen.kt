package com.aula.literatiapp.presentation.screens.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookColumn

@Composable
fun BooksListScreen(navController: NavController, type: String) {
    val books = when (type) {
        "popular" -> listOf(
            ""
        )
        "highRated" -> listOf(
            ""
        )
        else -> emptyList()
    }

    val title = if (type == "popular") "Mais Popular" else "Maior Avaliado"

    Scaffold(
        topBar = { BackNavigationDashboard(value = title, navController = navController) },
        bottomBar = { BottomNavigation(modifier = Modifier, navController = navController) }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier.padding(paddingValues).padding(16.dp)
        )
    }
}
