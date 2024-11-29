package com.aula.literatiapp.presentation.screens.library

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.MainDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookColumn
import com.aula.literatiapp.presentation.common.sharedViewModels.TagsViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

@Composable
fun LibraryScreen(navController: NavController) {




    // puxar todos os livros do viewModel
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

            ScrollableBookColumn(
                bookList = emptyList(),
                navController = navController
            )

        }
    }

}