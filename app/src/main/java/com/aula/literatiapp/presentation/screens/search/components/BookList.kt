package com.aula.literatiapp.presentation.screens.search.components

import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.navigation.Screen

@Composable
fun BookList(
    books: List<Book>,
    navController: NavController
) {

    var context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(books) { book ->
            BookListItem(
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
                    //Toast.makeText(context,"${book.toString()}", Toast.LENGTH_SHORT).show()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
        }
    }
}