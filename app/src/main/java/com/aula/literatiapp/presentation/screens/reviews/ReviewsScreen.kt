package com.aula.literatiapp.presentation.screens.reviews

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
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.domain.model.ImageLinks
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.screens.reviews.components.ReviewComponent

@Composable
fun ReviewsScreen(navController: NavController) {

    val booksList = remember {
        listOf(
            Book(
                id = "1",
                title = "The Handmaid's Tale",
                authors = listOf("Margaret Atwood"),
                publisher = "Penguin Random House",
                publishedDate = "1985",
                description = "Offred is a Handmaid in the Republic of Gilead...",
                pageCount = "400",
                categories = listOf("Clássicos", "Literatura", "Fantasia", "Ficção Científica"),
                averageRating = 4.5,
                ratingsCount = 2000,
                language = "en",
                imageLinks = ImageLinks(thumbnail = "https://example.com/handmaids-tale-thumbnail.jpg"),
                previewLink = "https://example.com/preview",
            ),
            Book(
                id = "2",
                title = "1984",
                authors = listOf("George Orwell"),
                publisher = "Penguin Books",
                publishedDate = "1949",
                description = "A dystopian social science fiction novel and cautionary tale...",
                pageCount = "328",
                categories = listOf("Dystopian", "Science Fiction"),
                averageRating = 4.7,
                ratingsCount = 3500,
                language = "en",
                imageLinks = ImageLinks(thumbnail = "https://example.com/handmaids-tale-thumbnail.jpg"),
                previewLink = "https://example.com/preview",
            )
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
                if (index < booksList.size - 1) {
                    HorizontalDivider()
                }
            }
        }

    }
}