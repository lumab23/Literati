package com.aula.literatiapp.presentation.screens.reviews.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.common.sharedComponents.BookCard
import com.aula.literatiapp.presentation.common.sharedComponents.StarRatingBar
import com.aula.literatiapp.presentation.ui.theme.getTextColor

@Composable
fun ReviewComponent(book: Book, navController: NavController) {

    var rating by remember { mutableStateOf(4f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                BookCard(
                    book = book,
                    onBookClick = { navController.navigate("book_screen") },
                    modifier = Modifier
                )
            }

            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = book.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        color = getTextColor()
                    )


                }

                Text(
                    text = book.authors?.joinToString(", ") ?: "Unknown Authors",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    color = getTextColor()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    color = getTextColor()
                )

            }
        }
    }
}