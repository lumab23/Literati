package com.aula.literatiapp.presentation.screens.bookDetails.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.domain.model.Book
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up

@Composable
fun BookScreenWithTabs(book: Book) {
    var selectedTab by remember { mutableStateOf("Detalhes") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Barra de navegação com tabs
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            TabButton(
                title = "Detalhes",
                selected = selectedTab == "Detalhes",
                onClick = { selectedTab = "Detalhes" },
                modifier = Modifier
            )
            TabButton(
                title = "Gêneros",
                selected = selectedTab == "Gêneros",
                onClick = { selectedTab = "Gêneros" },
                modifier = Modifier
            )
        }


        AnimatedContent(
            targetState = selectedTab,
            transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = Up
                ).togetherWith(
                    slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = Down
                    )
                )
            }
        ) { tab ->
            when (tab) {
                "Detalhes" -> BookDetailsContent(book = book)
                "Gêneros" -> BookGenresContent(book = book)
            }
        }
    }
}