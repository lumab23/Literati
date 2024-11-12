package com.aula.literatiapp.presentation.screens.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.common.sharedComponents.BookCard

@Composable
fun BookListItem(
    book: Book,
    onBookClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        BookCard(
            book = book,
            onBookClick = { onBookClick() },
            modifier = Modifier
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = book.authors.joinToString(","),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = book.publishedDate?.substringBefore("-") ?: "",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )

        }

    }
}