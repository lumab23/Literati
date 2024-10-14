package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.min
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BookScreenDashboard
import com.aula.literatiapp.components.BooksInformation
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.MenorDashboard

@Composable
fun BookScreen(navController: NavController, modifier: Modifier) {
    Scaffold(
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
        ) {
            BookScreenDashboard(navController = navController, modifier = modifier)
            BooksInformation()
        }
    }
}

@Preview(
    showSystemUi = true,
    name = "Bookshelv"
)
@Composable
fun BooksScreen() {
    val navController = rememberNavController()
    BookScreen(navController = navController,modifier = Modifier)
}
