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

@Composable
fun ReleaseDateScreen(navController: NavController) {

    val decades = listOf(
        "A Vir",
        "2020s",
        "2010s",
        "2000s",
        "1990s",
        "1980s"
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
                    categories = decades,
                    onCategoryClick = { selectedDecade ->
                        navController.navigate("decade_screen" + "/$selectedDecade") {
                            // Pop up to the top of the navigation stack to avoid multiple instances of the same screen
                            popUpTo("decade_screen") { inclusive = true }
                        }
                    }
                )
            }

        }
    }
}
