package com.aula.literatiapp.presentation.screens.library

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.domain.model.ImageLinks
import com.aula.literatiapp.domain.model.TabItem
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookColumn
import com.aula.literatiapp.presentation.ui.theme.getTextColor
import com.aula.literatiapp.presentation.ui.theme.primaryContainerLight


@Composable
fun MyBooksList(navController: NavController) {

    val tabItems = remember {
        listOf(
            TabItem(
                title = "Salvos",
                unselectedIcon = Icons.Outlined.Bookmarks,
                selectedIcon = Icons.Filled.Bookmarks
            ),
            TabItem(
                title = "Quero Trocar",
                unselectedIcon = Icons.Outlined.CurrencyExchange,
                selectedIcon = Icons.Filled.CurrencyExchange
            ) ,
            TabItem(
                title = "Favoritos",
                unselectedIcon = Icons.Outlined.FavoriteBorder,
                selectedIcon = Icons.Filled.Favorite
            )
        )
    }

    val books = listOf(
        Book(
            id = "1",
            title = "Book 1",
            authors = listOf("Author A"),
            publisher = "Publisher X",
            publishedDate = "2021",
            description = "A great book",
            pageCount = 320,
            categories = listOf("Fiction"),
            averageRating = 4.5,
            ratingsCount = 100,
            language = "en",
            imageLinks = ImageLinks(thumbnail = "https://example.com/handmaids-tale-thumbnail.jpg"),
            previewLink = "https://example.com/book1",
        )
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.meuslivros), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        var selectedTabIndex by remember {
            mutableStateOf(0)
        }

        val pagerState = rememberPagerState {
            tabItems.size
        }

        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }

        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress) {
                selectedTabIndex = pagerState.currentPage
            }
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                tabItems.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(
                                text = item.title
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedTabIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.title
                            )
                        },
                        selectedContentColor = primaryContainerLight,
                        unselectedContentColor = getTextColor()
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {index ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    when (tabItems[index] ){
                        tabItems[0] -> {ScrollableBookColumn(bookList = books, navController = navController)}
                        tabItems[1] -> {ScrollableBookColumn(bookList = books, navController = navController)}
                        tabItems[2] -> {ScrollableBookColumn(bookList = books, navController = navController)}
                    }
                }
            }

        }

    }
}
