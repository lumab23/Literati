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
fun BooksListScreen(navController: NavController, type: String) {
    val books = when (type) {
        "popular" -> listOf(
            Book(
                id = "1",
                title = "Book 1",
                authors = listOf("Author A"),
                publisher = "Publisher X",
                publishedDate = "2021",
                description = "A great book",
                pageCount = 320,
                categories = listOf("Fiction"),
                averageRating = 4.5,
                ratingsCount = 100,
                language = "en",
                thumbnail = "https://example.com/image1.jpg",
                previewLink = "https://example.com/book1",
                userReview = "Loved it!"
            )
        )
        "highRated" -> listOf(
            Book(
                id = "1",
                title = "Book 1",
                authors = listOf("Author A"),
                publisher = "Publisher X",
                publishedDate = "2021",
                description = "A great book",
                pageCount = 320,
                categories = listOf("Fiction"),
                averageRating = 4.5,
                ratingsCount = 100,
                language = "en",
                thumbnail = "https://example.com/image1.jpg",
                previewLink = "https://example.com/book1",
                userReview = "Loved it!"
            )
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
