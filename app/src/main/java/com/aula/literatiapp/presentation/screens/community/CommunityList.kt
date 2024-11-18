package com.aula.literatiapp.presentation.screens.community

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.screens.community.components.SpecificCommunityListItem

@Composable
fun CommunityList(navController: NavController) {

    val communities = remember {
        listOf(
            Community(
                id = "1",
                name = "Amantes de Jane Austen",
                imageUrl = "https://i.pinimg.com/564x/47/b5/47/47b547ad30201ad69099c2cb6faff682.jpg",
                description = "Comunidade para todos que amam as obras de Jane Austen!",
                specificCommunityName = "Romance",
                categories = listOf("Romance")
            ),
            Community(
                id = "2",
                name = "Percy Jackson",
                imageUrl = "https://i.pinimg.com/564x/51/62/fc/5162fcdd104398741191ad9dfd123c12.jpg",
                description = "Para fãs de Percy Jackson!",
                specificCommunityName = "Aventura",
                categories = listOf("Aventura")
            )
        )
    }

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = communities.first().name,
                navController = navController
            )
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                items(communities.size) { index ->
                    SpecificCommunityListItem(community = communities[index], navController = navController)

                    if (index < communities.size - 1) {
                        HorizontalDivider()
                    }
                }
            }

        }
    }
}
