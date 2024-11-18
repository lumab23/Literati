package com.aula.literatiapp.presentation.screens.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookColumn

@Composable
fun DecadeScreen(
    navController: NavController,
    decadeName: String,
    bookList: List<Book>
) {

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = decadeName, navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        ScrollableBookColumn(
            bookList = bookList,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
    }
}