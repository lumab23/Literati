package com.aula.literatiapp.presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookColumn
import com.aula.literatiapp.presentation.screens.search.viewModels.SearchViewModel

@Composable
fun BooksListScreen(navController: NavController, type: String, searchViewModel: SearchViewModel) {

    val books by searchViewModel.bookList.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()

    LaunchedEffect(type) {
        if (type == "popular") {
            searchViewModel.getPopularBooks()
        }
    }

    val title = if (type == "popular") "Mais Popular" else "Livros"

    Scaffold(
        topBar = { BackNavigationDashboard(value = title, navController = navController) },
        bottomBar = { BottomNavigation(modifier = Modifier, navController = navController) }
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            ScrollableBookColumn(
                bookList = books,
                navController = navController,
                modifier = Modifier.padding(paddingValues).padding(16.dp)
            )
        }
    }
}
