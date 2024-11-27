package com.aula.literatiapp.presentation.common.sharedComponents

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.Book

@Composable
fun ScrollableBookColumn(
    bookList: List<Book>,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(bookList.chunked(4)) { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                rowItems.forEach { book ->
                    BookCard(
                        book = book,
                        onBookClick = {
                            navController.navigate("book_screen")
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                repeat(4 - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
