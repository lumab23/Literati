package com.aula.literatiapp.presentation.common.sharedComponents

import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
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
    modifier: Modifier = Modifier,
) {

    var context = LocalContext.current

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

                            var editor = context.getSharedPreferences("arquivo",MODE_PRIVATE).edit()
                            editor.putString( "id","${book.id}")
                            editor.putString( "tittle","${book.title}")
                            editor.putString( "authors","${book.authors}")
                            editor.putString( "publisher","${book.publisher}")
                            editor.putString( "description","${book.description}")
                            editor.putString("imageLink", book.imageLinks?.thumbnail ?: "")
                            editor.putString("categories", book.categories.joinToString(","))
                            editor.putString( "pageCount","${book.pageCount}")
                            editor.putString( "publishedDate","${book.publishedDate}")
                            editor.putString( "language","${book.language}")
                            editor.putString( "previewLink","${book.previewLink}")
                            editor.putString("averageRating", book.averageRating?.toString() ?: "N/A")
                            editor.putString("ratingsCount", book.ratingsCount?.toString() ?: "0")
                            editor.putString("categories","${book.categories}")
                            editor.apply()
                            navController.navigate("book_screen")
                            Toast.makeText(context,"${book.toString()}", Toast.LENGTH_SHORT).show()
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
