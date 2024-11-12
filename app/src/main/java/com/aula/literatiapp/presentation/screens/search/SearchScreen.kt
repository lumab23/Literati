package com.aula.literatiapp.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.BookItem
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection
import com.aula.literatiapp.presentation.common.sharedComponents.MainDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.SearchBarComponent
import com.aula.literatiapp.presentation.screens.search.components.BookList
import com.aula.literatiapp.presentation.screens.search.viewModels.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {

    val bookList by searchViewModel.bookList.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()
    val searchQuery by searchViewModel.searchQuery.collectAsState()

    val categories = listOf(
        "Data de lançamento",
        "Gêneros",
        "Mais popular",
        "Menor avaliado"
    )

    Scaffold(
        topBar = {
            MainDashboard(
                modifier = Modifier,
                value = stringResource(id = R.string.search),
                fontSize = 20.sp
            )
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .align(Alignment.TopCenter)
            ) {

                SearchBarComponent(
                    query = searchQuery,
                    onQueryChange = { query ->
                        searchViewModel.updateSearchQuery(query)
                    },
                    onSearch = { },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (searchQuery.isEmpty() && !isLoading) {
                    CategorySection(
                        title = stringResource(id = R.string.pesquise_por),
                        categories = categories,
                        onCategoryClick = { selectedCategory ->
                            when (selectedCategory) {
                                "Data de lançamento" -> navController.navigate("release_date_screen")
                                "Gêneros" -> navController.navigate("genres_screen")
                                "Mais popular" -> navController.navigate("book_list_screen?type=popular")
                                "Menor avaliado" -> navController.navigate("book_list_screen?type=lowRated")
                            }
                        }
                    )
                }

                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 3.dp,
                            strokeCap = StrokeCap.Round
                        )
                    }
                } else if (searchQuery.isNotEmpty() && bookList.isNotEmpty()) {
                    BookList(books = bookList, navController = navController)
                }

            }

        }
    }
}

