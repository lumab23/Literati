package com.aula.literatiapp.presentation.screens.community

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight
import java.util.Date

@Composable
fun SpecificCommunityScreen(
    community: Community,
    navController: NavController,
    viewModel: CommunityViewModel
) {
    val isMember by viewModel.isMember.collectAsState() // Update to reflect changes from mutableState
    val isAdmin by viewModel.isAdmin.collectAsState()
    val posts by viewModel.posts.collectAsState()

    // Call functions to check status only once, when the composable is first launched
    LaunchedEffect(Unit) {
        viewModel.checkMembershipStatus(community.id)
        viewModel.checkAdminStatus(community.id)
        viewModel.loadPosts(community.id)
    }

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
                Text(
                    text = community.description,
                    style = MaterialTheme.typography.bodyMedium
                )

                if (!isMember) {
                    Text("Join to view the posts and interact!", color = Color.Gray)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray.copy(alpha = 0.5F))
                    ) {
                        Button(onClick = { viewModel.joinCommunity(community.id) }) {
                            Text("Join Community")
                        }
                    }
                } else {
                    ScrollablePostList(
                        postList = posts,
                        isMember = isMember,
                        navController = navController
                    )
                }
            }

            if (isAdmin) {
                IconButton(onClick = { /* Navigate to edit screen */ }) {
                    Icon(Icons.Filled.Settings, contentDescription = "Edit Community")
                }
            }
        }
    }
}



