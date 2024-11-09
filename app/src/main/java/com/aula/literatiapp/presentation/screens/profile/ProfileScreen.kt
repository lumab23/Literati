package com.aula.literatiapp.presentation.screens.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookRow
import com.aula.literatiapp.presentation.screens.profile.components.TopBar

@Composable
fun ProfileScreen(navController: NavController) {
    val favoritos = listOf(
        ""
    )

    val lendo = listOf(
        ""
    )

    val troca = listOf(
        ""
    )

    val listas = listOf(
        "Reviews",
        "Tags"
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(5.dp)
        ) {
            // Cabeçalho do perfil
            item {
                TopBar(navController = navController)
                HorizontalDivider()
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Seção de favoritos
            item {
                Text(
                    text = stringResource(id = R.string.favoritos),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                ScrollableBookRow(bookList = favoritos, navController = navController)
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(15.dp))
            }

            // Seção de lendo
            item {
                Text(
                    text = stringResource(id = R.string.recentes),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                ScrollableBookRow(bookList = lendo, navController = navController)
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(15.dp))
            }

            // Seção de troca
            item {
                Text(
                    text = stringResource(id = R.string.troca),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                ScrollableBookRow(bookList = troca, navController = navController)
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider()
            }

            item {
                CategorySection(
                    title = "",
                    categories = listas,
                    onCategoryClick = { selectedSection ->
                        when (selectedSection) {
                            "Reviews" -> {
                                navController.navigate("reviews_screen")
                            }
                            "Tags" -> {
                                navController.navigate("tags_screen")
                            }
                        }

                    }
                )
            }


        }
    }
}