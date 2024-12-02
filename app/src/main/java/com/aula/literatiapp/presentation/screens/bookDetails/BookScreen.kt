package com.aula.literatiapp.presentation.screens.bookDetails

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.domain.model.ImageLinks
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.screens.bookDetails.components.BookInfo
import com.aula.literatiapp.presentation.screens.bookDetails.components.BookScreenWithTabs
import com.aula.literatiapp.presentation.screens.bookDetails.components.MyBookDashboard
import com.aula.literatiapp.presentation.screens.bookDetails.components.RatingSection

@Composable
fun BookScreen(
    navController: NavController
) {
    var context = LocalContext.current
    var bookSp = context.getSharedPreferences("arquivo",MODE_PRIVATE)
    val book = remember {
        Book(
            id = bookSp.getString("id","").toString(),
            title = bookSp.getString("tittle","").toString(),
            authors = listOf(bookSp.getString("authors","").toString()),
            publisher = bookSp.getString("publisher","").toString(),
            description = bookSp.getString("description","").toString(),
            imageLinks = ImageLinks(thumbnail = bookSp.getString("imageLink", "").orEmpty()),
            categories = bookSp.getString("categories", "")?.split(",") ?: emptyList(),
            pageCount = bookSp.getString("pageCount","").toString(),
            publishedDate = bookSp.getString("publishedDate","").toString(),
            averageRating = bookSp.getString("averageRating", null)?.toDoubleOrNull(),
            ratingsCount = bookSp.getString("ratingsCount", null)?.toIntOrNull()
            )
    }

    Scaffold(
        topBar = {
            MyBookDashboard(navController = navController, bookId = book)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                BookInfo(book = book, navController = navController)
                Spacer(modifier = Modifier.height(15.dp))
                RatingSection(book = book, navController = navController)
                Spacer(modifier = Modifier.height(15.dp))
                BookScreenWithTabs(book = book)
            }
        }
    }
}

@PreviewLightDark
@Composable
fun BookScreenPreview() {
    val navController = rememberNavController()
    BookScreen(navController = navController)
}