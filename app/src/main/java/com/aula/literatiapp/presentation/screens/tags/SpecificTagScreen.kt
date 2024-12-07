package com.aula.literatiapp.presentation.screens.tags

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookColumn
import com.aula.literatiapp.presentation.common.sharedViewModels.TagsViewModel


@Composable
fun SpecificTagScreen(
    tag: String,
    navController: NavController,
    tagsViewModel: TagsViewModel = viewModel()
) {
    val bookshelf by tagsViewModel.bookshelf.observeAsState(emptyList())
    val booksByTag by tagsViewModel.booksByTag.collectAsState()
    val isLoading by tagsViewModel.isLoading.collectAsState()

    // Filtrar os livros associados à tag clicada
    val booksInTag = bookshelf.filter { book ->
        booksByTag[tag]?.contains(book.id) == true
    }

    var context = LocalContext.current

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = tag,
                navController = navController
            )
        }
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 1.dp
                )
            }
        } else {
            if (booksInTag.isEmpty()) {
                Text(
                    text = "Nenhum livro adicionado a essa tag.",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(16.dp)
                )
            } else {
                ScrollableBookColumn(
                    bookList = booksInTag,
                    navController = navController,
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}
