package com.aula.literatiapp.presentation.common.sharedComponents

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.ui.theme.AppTheme

@Composable
fun BookCard(
    book: Book,
    onBookClick: (String) -> Unit,
    modifier: Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .size(width = 75.dp, height = 110.dp),
        onClick = { onBookClick(book.id+"") }
    ) {

        AsyncImage(
            model = book.imageLinks?.thumbnail ?: "https://i.pinimg.com/564x/6a/f1/ec/6af1ec6645410a41d5339508a83b86f9.jpg",
            contentDescription = "book image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
