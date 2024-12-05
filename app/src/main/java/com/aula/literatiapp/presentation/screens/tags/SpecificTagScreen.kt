package com.aula.literatiapp.presentation.screens.tags

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BookCard
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
        if (booksInTag.isEmpty()) {
            Text(
                text = "Nenhum livro associado à tag \"$tag\".",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(16.dp)
            )
        } else {
            // Usando ScrollableBookColumn
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
