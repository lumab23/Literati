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
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookColumn
import com.aula.literatiapp.presentation.screens.search.viewModels.SearchViewModel

@Composable
fun SpecificGenreScreen(
    navController: NavController,
    genreName: String,
    searchViewModel: SearchViewModel
) {

    val books by searchViewModel.bookList.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()

    LaunchedEffect(genreName) {
        searchViewModel.getBooksByGenre(genreName)
    }

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = genreName, navController = navController)
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
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
