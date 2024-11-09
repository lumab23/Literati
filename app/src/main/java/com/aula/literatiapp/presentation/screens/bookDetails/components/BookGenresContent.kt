package com.aula.literatiapp.presentation.screens.bookDetails.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.ui.theme.getTextColor
import com.aula.literatiapp.presentation.ui.theme.secondaryContainerLight

@Composable
fun BookGenresContent(book: Book) {
    // Lista de botões representando gêneros
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento vertical entre linhas
    ) {
        val genres = book.categories

        if (genres != null) {
            genres.chunked(3).forEach { rowGenres ->  // Agrupa 3 gêneros por linha
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento horizontal entre botões
                ) {
                    rowGenres.forEach { genre ->
                        Button(
                            onClick = { /* Ação ao clicar */ },
                            colors = ButtonDefaults.buttonColors(
                                Color.Transparent
                            ),
                            border = BorderStroke(0.5.dp, secondaryContainerLight),
                        ) {
                            Text(
                                text = genre,
                                color = getTextColor(),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}