package com.aula.literatiapp.presentation.screens.community.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.presentation.screens.bookDetails.components.BookDetailsContent
import com.aula.literatiapp.presentation.screens.bookDetails.components.BookGenresContent
import com.aula.literatiapp.presentation.screens.bookDetails.components.TabButton
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel

@Composable
fun CommunitiesControl(navController: NavController) {

    var selectedTab by remember { mutableStateOf("Comunidades Gerais") }

    var communityViewModel: CommunityViewModel = viewModel()
    val myCommunities by communityViewModel.myCommunities.collectAsState()

    val generalCommunities = listOf(
        Community(
            id = "1",
            name = "Fantasia",
            description = "Para aqueles que gostam de fantasia",
            imageUrl = "https://i.pinimg.com/564x/4e/be/d6/4ebed6d42634cb724ce5bcfbc95bbf41.jpg",
            categories = listOf("Fantasia", "Magia", "Aventura")
        ),
        Community(
            id = "2",
            name = "Romance",
            description = "Para aqueles que gostam de romance",
            imageUrl = "https://i.pinimg.com/564x/5a/9b/43/5a9b439115de910523c3474a0a728f78.jpg",
            categories = listOf("Romance", "Drama", "Ficção")
        ),
        Community(
            id = "3",
            name = "Ficção Científica",
            description = "Para aqueles que gostam de Ficção Científica",
            imageUrl = "https://i.pinimg.com/564x/05/10/b2/0510b2703f70284c8ea255fb9f77ee28.jpg",
            categories = listOf("Ficção Científica", "Tecnologia", "Exploração")
        ),
        Community(
            id = "4",
            name = "Comics",
            description = "Para aqueles que gostam de Comics",
            imageUrl = "https://i.pinimg.com/736x/c3/0e/20/c30e2025aeaf8bc13ca35182db791b7f.jpg",
            categories = listOf("Comics", "Super-Heróis", "Ação")
        ),
        Community(
            id = "5",
            name = "Mangá",
            description = "Para aqueles que gostam de mangás",
            imageUrl = "https://i.pinimg.com/736x/02/40/51/024051e085bd276342db47f57dd3cf55.jpg",
            categories = listOf("Comics")
        )
    )

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
                //.padding(bottom = 16.dp)
        ) {
            TabButton(
                title = "Comunidades Gerais",
                selected = selectedTab == "Comunidades Gerais",
                onClick = {
                    selectedTab = "Comunidades Gerais"
                },
                modifier = Modifier.weight(1f)
            )
            TabButton(
                title = "Minhas comunidades",
                selected = selectedTab == "Minhas comunidades",
                onClick = {
                    selectedTab = "Minhas comunidades"
                    communityViewModel.fetchUserCommunities()
                },
                modifier = Modifier.weight(1f)
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
                "Comunidades Gerais" -> {
                    ScrollableCommunityColumn(
                        communityList = generalCommunities,
                        navController = navController,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                "Minhas comunidades" -> {
                    ScrollableCommunityColumn(
                        communityList = myCommunities,
                        navController = navController,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

}