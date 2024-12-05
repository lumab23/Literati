package com.aula.literatiapp.presentation.common.sharedComponents

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.Book

@Composable
fun ScrollableBookRow(
    bookList: List<Book>,
    navController: NavController
) {
    val context = LocalContext.current

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.padding(5.dp)
    ) {
        items(bookList.size) { index ->
            BookCard(
                book = bookList[index],
                onBookClick = {
                    // Salva os detalhes do livro no SharedPreferences
                    val book = bookList[index]
                    val editor = context.getSharedPreferences("arquivo", MODE_PRIVATE).edit()
                    editor.putString("id", book.id)
                    editor.putString("title", book.title)
                    editor.putString("authors", book.authors.toString())
                    editor.putString("publisher", book.publisher)
                    editor.putString("description", book.description)
                    editor.putString("imageLink", book.imageLinks?.thumbnail ?: "")
                    editor.putString("categories", book.categories.joinToString(","))
                    editor.putString("pageCount", book.pageCount.toString())
                    editor.putString("publishedDate", book.publishedDate)
                    editor.putString("language", book.language)
                    editor.putString("previewLink", book.previewLink)
                    editor.putString("averageRating", book.averageRating?.toString() ?: "N/A")
                    editor.putString("ratingsCount", book.ratingsCount?.toString() ?: "0")
                    editor.apply()

                    // Navega para a tela de detalhes do livro
                    navController.navigate("book_screen")
                },
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            )
        }
    }
}
