package com.aula.literatiapp.presentation.screens.tags

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BookCard
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookColumn
import com.aula.literatiapp.presentation.common.sharedViewModels.TagsViewModel

@Composable
fun SpecificTagScreen(
    tag: String,
    navController: NavController,
    viewModel: TagsViewModel = viewModel()
) {

    val booksByTag by viewModel.booksByTag.collectAsState()
    val books = viewModel.getBooksForTag(tag)

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = tag,
                navController = navController
            )
        }
    ) { paddingValues ->
        if (books.isNotEmpty()) {
            ScrollableBookColumn(
                bookList = books,
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Nenhum livro encontrado para a tag '$tag'.")
            }
        }
    }
}
