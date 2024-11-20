package com.aula.literatiapp.presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection
import com.aula.literatiapp.presentation.screens.search.viewModels.SearchViewModel

@Composable
fun ReleaseDateScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {

    val decades = listOf(
        "2020s" to Pair(2020, 2029),
        "2010s" to Pair(2010, 2019),
        "2000s" to Pair(2000, 2009)
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.decada), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.TopCenter)
            ) {

                CategorySection(
                    "",
                    categories = decades.map { it.first },
                    onCategoryClick = { selectedDecade ->
                        val years = decades.find { it.first == selectedDecade }?.second
                        if (years != null) {
                            searchViewModel.getBooksByDateRange(years.first, years.second)
                            navController.navigate("decade_screen/${selectedDecade}")
                        }
                    }
                )
            }

        }
    }
}
