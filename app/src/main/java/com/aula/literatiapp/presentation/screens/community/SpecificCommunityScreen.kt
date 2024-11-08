package com.aula.literatiapp.presentation.screens.community

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.domain.model.CommunityPost
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.screens.community.components.ScrollablePostList
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight
import java.util.Date

/* TODO: Realizar a verificação de ver se é um membro ou administrador,
    se for um administrador, na topo esquerdo vai ter a configuração de edição da comunidade

 */
@Composable
fun SpecificCommunityScreen(community: Community ,navController: NavController) {
    val isMember = true

    val posts = listOf(
        CommunityPost(
            id = "1",
            communityId = "123",
            userId = "AliceID",
            userName = "@Alice",
            content = "Recomendação de leitura...",
            imageUrl = "https://example.com/avatar1.png",
            createdAt = Date(),
            likesCount = 10,
            commentsCount = 3
        ),
        CommunityPost(
            id = "2",
            communityId = "123",
            userId = "BobID",
            userName = "@Bob",
            content = "Alguém recomenda um livro de fantasia?",
            imageUrl = "https://example.com/avatar2.png",
            createdAt = Date(),
            likesCount = 5,
            commentsCount = 1
        )
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = community.specificCommunityName,
                navController = navController
            )
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
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                ScrollablePostList(
                    postList = posts,
                    isMember = isMember,
                    navController = navController
                )
            }

        }
    }
}

