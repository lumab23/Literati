package com.aula.literatiapp.presentation.common.sharedComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScrollableBookRow(
    bookList: List<String>,
    navController: NavController
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.padding(5.dp)
    ) {
        items(bookList.size) { index ->
            BookCard(
                imageUrl = bookList[index].toString(),
                onBookClick = {
                    navController.navigate("book_screen")
                },
                modifier = Modifier
                    .height(200.dp)
                    .padding(horizontal = 4.dp)
            )
        }
    }
}
