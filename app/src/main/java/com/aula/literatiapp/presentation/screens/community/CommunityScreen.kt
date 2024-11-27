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
import androidx.compose.ui.unit.sp
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.presentation.common.sharedComponents.MainDashboard
import com.aula.literatiapp.presentation.screens.community.components.ScrollableCommunityColumn
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.SearchBarComponent
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight

@Composable
fun CommunityScreen(navController: NavController) {
    val comunidades = listOf(
        Community(
            id = "1",
            name = "Fantasia",
            description = "Para aqueles que gostam de fantasia",
            imageUrl = "https://i.pinimg.com/564x/4e/be/d6/4ebed6d42634cb724ce5bcfbc95bbf41.jpg",
            specificCommunityImageUrl = "https://i.pinimg.com/564x/4e/be/d6/4ebed6d42634cb724ce5bcfbc95bbf41.jpg",
            specificCommunityName = "Fantasia Fans",
            categories = listOf("Fantasia", "Magia", "Aventura")
        ),
        Community(
            id = "2",
            name = "Romance",
            description = "Para aqueles que gostam de romance",
            imageUrl = "https://i.pinimg.com/564x/5a/9b/43/5a9b439115de910523c3474a0a728f78.jpg",
            specificCommunityImageUrl = "https://i.pinimg.com/564x/5a/9b/43/5a9b439115de910523c3474a0a728f78.jpg",
            specificCommunityName = "Romance Readers",
            categories = listOf("Romance", "Drama", "Ficção")
        ),
        Community(
            id = "3",
            name = "Ficção Científica",
            description = "Para aqueles que gostam de Ficção Científica",
            imageUrl = "https://i.pinimg.com/564x/05/10/b2/0510b2703f70284c8ea255fb9f77ee28.jpg",
            specificCommunityImageUrl = "https://i.pinimg.com/564x/05/10/b2/0510b2703f70284c8ea255fb9f77ee28.jpg",
            specificCommunityName = "Sci-Fi Society",
            categories = listOf("Ficção Científica", "Tecnologia", "Exploração")
        ),
        Community(
            id = "4",
            name = "Comics",
            description = "Para aqueles que gostam de livros Comics",
            imageUrl = "https://i.pinimg.com/736x/c3/0e/20/c30e2025aeaf8bc13ca35182db791b7f.jpg",
            specificCommunityImageUrl = "https://i.pinimg.com/736x/c3/0e/20/c30e2025aeaf8bc13ca35182db791b7f.jpg",
            specificCommunityName = "Comic Book Club",
            categories = listOf("Comics", "Super-Heróis", "Ação")
        )
    )

    Scaffold(
        topBar = {
            MainDashboard(modifier = Modifier, value = stringResource(id = R.string.comunidades), fontSize = 20.sp)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                SearchBarComponent(
                    query = "",
                    onQueryChange = {},
                    onSearch = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )

                ScrollableCommunityColumn(
                    communityList = comunidades,
                    navController = navController,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .weight(1f)
                )
            }

            FloatingActionButton(
                onClick = {
                    navController.navigate("make_a_post")
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(60.dp),
                containerColor = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = gradientBrushLight),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Create Post"
                    )
                }
            }

        }




    }
}
