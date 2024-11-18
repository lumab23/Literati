package com.aula.literatiapp.presentation.screens.bookDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.ui.theme.getTextColor

@Composable
fun BookDetailsContent(book: Book) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Publicadores: " + book.publisher,
            fontSize = 12.sp,
            color = getTextColor(),
            modifier = Modifier.padding(4.dp)
        )
        HorizontalDivider()
        Text(
            text = "Data de publicação: " + book.publishedDate,
            fontSize = 12.sp,
            color = getTextColor(),
            modifier = Modifier.padding(4.dp)
        )
        HorizontalDivider()
        Text(
            text = "Quantidade de páginas: " + book.pageCount,
            fontSize = 12.sp,
            color = getTextColor(),
            modifier = Modifier.padding(4.dp)
        )
    }
}