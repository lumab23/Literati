package com.aula.literatiapp.screens

import android.graphics.Color
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
import com.aula.literatiapp.components.BackNavigationDashboard
import com.aula.literatiapp.model.TabItem
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.ScrollableBookColumn
import com.aula.literatiapp.ui.theme.lightGreen
import com.aula.literatiapp.ui.theme.textColor

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
        R.drawable.americandirt,
        R.drawable.babel,
        R.drawable.loveonthebrain,
        R.drawable.bride,
        R.drawable.atempestoftea,
        R.drawable.dialaforaunties,
        R.drawable.funnystory,
        R.drawable.honeygirl,
        R.drawable.ikissedsharawheeler,
        R.drawable.infiveyears,
        R.drawable.justforthesummer,
        R.drawable.loveonthebrain,
        R.drawable.notinlove,
        R.drawable.tbosas,
        R.drawable.thefamiliar,
        R.drawable.theinvisiblelifeofaddielarue,
        R.drawable.themidnightlibrary,
        R.drawable.theotherblackgirl
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
                containerColor = TabRowDefaults.primaryContainerColor
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
                        selectedContentColor = lightGreen,
                        unselectedContentColor = textColor
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
                    //ScrollableBookColumn(bookList = books, navController = navController)
                    //Text(text = tabItems[index].title)
                }
            }

        }

    }
}

