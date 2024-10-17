package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.components.BackNavigationDashboard
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.ReviewComponent
import com.aula.literatiapp.model.Book

@Composable
fun ReviewsScreen(navController: NavController) {

    val booksList = remember {
        listOf(
            Book(
                title = "The Handmaid's Tale",
                authors = listOf("Margaret Atwood"),
                publisher = "Penguin Random House",
                description = "Offred is a Handmaid in the Republic of Gilead...",
                imageUrl = "",
                tags = "Dystopian",
                genres = listOf("Clássicos", "Literatura", "Fantasia", "Ficção Científica"),
                id = "1",
                volumeInfo = "3 edição",
                pages = "400",
                review = "Muito bom!!"
            ),
            Book(
                title = "The Handmaid's Tale",
                authors = listOf("Margaret Atwood"),
                publisher = "Penguin Random House",
                description = "Offred is a Handmaid in the Republic of Gilead...",
                imageUrl = "",
                tags = "Dystopian",
                genres = listOf("Clássicos", "Literatura", "Fantasia", "Ficção Científica"),
                id = "1",
                volumeInfo = "3 edição",
                pages = "400",
                review = "Muito bom!!"
            ),
        )
    }

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.reviews), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            items(booksList.size) { index ->
                ReviewComponent(book = booksList[index], navController = navController)

                // Exibe o divisor abaixo de cada review, exceto após o último item
                if (index < booksList.size - 1) {
                    HorizontalDivider()
                }
            }
        }

    }
}