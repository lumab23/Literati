package com.aula.literatiapp.presentation.screens.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.MainDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookRow

@Composable
fun HomeScreen(navController: NavController) {
    val stephenKingBooksRec = listOf(
        ""
    )

    val fantasy = listOf(
        ""
    )

    val romance = listOf(
        ""
    )

    val classics = listOf(
        ""
    )

    Scaffold(
        topBar = {
            MainDashboard(value = stringResource(id = R.string.name), modifier = Modifier, fontSize = 28.sp)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.recomendacao),
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                ScrollableBookRow(bookList = stephenKingBooksRec, navController = navController)
                Spacer(modifier = Modifier.height(40.dp))
            }

            item {
                Text(
                    text = stringResource(id = R.string.trend_fantasy),
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                ScrollableBookRow(bookList = fantasy, navController = navController)
                Spacer(modifier = Modifier.height(40.dp))
            }

            item {
                Text(
                    text = stringResource(id = R.string.trend_romance),
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                ScrollableBookRow(bookList = romance, navController = navController)
                Spacer(modifier = Modifier.height(40.dp))
            }

            item {
                Text(
                    text = stringResource(id = R.string.trend_classics),
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                ScrollableBookRow(bookList = classics, navController = navController)
            }
        }
    }
}