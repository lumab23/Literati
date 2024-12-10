package com.aula.literatiapp.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.MainDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookRow
import com.aula.literatiapp.presentation.common.sharedViewModels.TagsViewModel
import com.aula.literatiapp.presentation.screens.home.components.KeepReadingBox
import com.aula.literatiapp.presentation.screens.home.components.KeepReadingRow

@Composable
fun HomeScreen(
    navController: NavController,
    tagsViewModel: TagsViewModel = viewModel()
) {

    val booksByTag by tagsViewModel.booksByTag.collectAsState()
    val bookshelf by tagsViewModel.bookshelf.observeAsState(emptyList())

    val currentlyReading = bookshelf.filter { book ->
            booksByTag["Estou Lendo"]?.contains(book.id) == true
        }.sortedByDescending { it.addedAt }


    LaunchedEffect(Unit) {
        tagsViewModel.loadTags()
    }

    Scaffold(
        topBar = {
            MainDashboard(value = stringResource(id = R.string.name), modifier = Modifier, fontSize = 28.sp)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController)
        },
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {
                    Log.d("HomeScreen", "FloatingActionButton clicked, navigating to gemini_chat.")
                    navController.navigate("gemini_chat")
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_gemini_icon),
                    contentDescription = "gemini chat",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            item {
                Text(
                    text = stringResource(id = R.string.continue_reading),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                ScrollableBookRow(bookList = currentlyReading, navController = navController)
            }

        }
    }
}