package com.aula.literatiapp.presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
fun GenreScreen(
    navController: NavController,
    genreName: String,
    bookList: List<Int> // Replace `Int` with `Book` or another data type once the API is integrated
) {
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
            ScrollableBookColumn(
                bookList = bookList,
                navController = navController,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}
