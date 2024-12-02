package com.aula.literatiapp.presentation.screens.community

import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.domain.model.CommunityPost
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.screens.community.components.CommunityBackNavigationDashBoard
import com.aula.literatiapp.presentation.screens.community.components.ScrollablePostList
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight
import java.util.Date

// Fazer ele exibir informações da comunidade especifica
@Composable
fun SpecificCommunityScreen(
    navController: NavController,
    viewModel: CommunityViewModel = viewModel(),
    communityId: String,
    parentCommunityId: String
) {
    val isMember by viewModel.isMember.collectAsState()
    //val isAdmin by viewModel.isAdmin.collectAsState()
    val isAdmin = true
    val posts by viewModel.posts.collectAsState()
    val community by viewModel.community.collectAsState()

    // Create a local variable for 'community'
    val currentCommunity = community

    LaunchedEffect(Unit) {
        viewModel.getCommunityById(parentCommunityId, communityId)
        viewModel.checkUserStatus(parentCommunityId, communityId)
        viewModel.loadAllPosts(parentCommunityId, communityId)
    }

    Log.d("SpecificScreen Community Debug banco", community.toString())

    Scaffold(
        topBar = {
            CommunityBackNavigationDashBoard(
                value = currentCommunity?.name ?: "Loading...",
                navController = navController,
                isMember = isMember,
                isAdmin = isAdmin,
                parentCommunityId = parentCommunityId,
                communityId = communityId,
                onJoinClick = { viewModel.joinCommunity(parentCommunityId, communityId) },
                onModerationClick = {
                    navController.navigate("moderationScreen/$communityId")
                }
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
            if (currentCommunity == null) {
                // Show a loading state while the community is being fetched
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = currentCommunity.description,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    if (!isMember) {
                        Text("Join to view the posts and interact!", color = Color.Gray)

                    } else {
                        ScrollablePostList(
                            parentCommunityId = parentCommunityId,
                            communityId = communityId,
                            postList = posts,
                            isMember = isMember,
                            navController = navController
                        )
                    }
                }

                FloatingActionButton(
                    onClick = {
                        navController.navigate("create_post_screen/$parentCommunityId/$communityId?parentPostId=null")
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
}





