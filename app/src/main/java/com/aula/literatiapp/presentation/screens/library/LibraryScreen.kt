package com.aula.literatiapp.presentation.screens.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.MainDashboard
import com.aula.literatiapp.presentation.screens.library.components.ChallengeSection
import com.aula.literatiapp.presentation.screens.library.components.MyBooksBoxComponent

@Composable
fun LibraryScreen(navController: NavController) {

    val bookImages = remember {
        ""
    }

    Scaffold( // seções tipo div
        topBar = {
            //pegar menor dashboard (componente)
            MainDashboard(
                modifier = Modifier,
                value = stringResource(R.string.bookshelv), fontSize = 20.sp
            )
        },
        //pegar bottomNavigation (componente)
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            //MyBooksBoxComponent(book = bookImages, navController = navController)
            Spacer(modifier = Modifier.height(20.dp))
            ChallengeSection(navController = navController)
        }
    }

}