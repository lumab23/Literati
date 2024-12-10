package com.aula.literatiapp.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.common.sharedComponents.BookCard
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight

@Composable
fun KeepReadingBox(
     book: Book,
     navController: NavController
) {

    Box(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(20.dp))
            .shadow(5.dp, shape = RoundedCornerShape(20.dp))
            .background(Color.Gray)
            .clickable {
                navController.navigate("book_screen")
            }
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            BookCard(
                book = book,
                onBookClick = { },
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

}
