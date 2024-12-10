package com.aula.literatiapp.presentation.screens.home.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.Book

@Composable
fun KeepReadingRow(
    books: List<Book>,
    navController: NavController
) {

    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        books.take(4).forEach { book ->
            KeepReadingBox(book = book, navController = navController)
        }

    }
}