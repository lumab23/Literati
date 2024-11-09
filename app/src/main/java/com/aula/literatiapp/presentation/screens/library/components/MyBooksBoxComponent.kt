package com.aula.literatiapp.presentation.screens.library.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.common.sharedComponents.BookCard
import com.aula.literatiapp.presentation.ui.theme.surfaceDimLight

@Composable
fun MyBooksBoxComponent(book: String, navController: NavController) {

    Card(
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = surfaceDimLight
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Go to all the books",
                    modifier = Modifier
                        .clickable {
                            navController.navigate("my_library_list")
                        }
                )

            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                repeat(3) {
                    BookCard(
                        // TODO: Passar a imagem do livro da api (os últimos livros adicionados pelo user)
                        imageUrl = "image",
                        onBookClick = { navController.navigate("book_screen") },
                        modifier = Modifier
                    )
                }

            }
        }
    }

}