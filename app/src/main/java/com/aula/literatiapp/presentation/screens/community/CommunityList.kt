package com.aula.literatiapp.presentation.screens.community

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.screens.community.components.SpecificCommunityListItem
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel
import androidx.compose.runtime.getValue
import com.aula.literatiapp.presentation.screens.community.components.ScrollableSpecificCommunityColumn

@Composable
fun CommunityList(
    navController: NavController,
    parentCommunityId: String?,
    viewModel: CommunityViewModel = viewModel()
) {
    val communities by viewModel.communities.collectAsState(initial = emptyList())
    val parentCommunityName by viewModel.parentCommunityName.collectAsState()

    LaunchedEffect(parentCommunityId) {
        if (parentCommunityId != null) {
            viewModel.loadParentCommunityName(parentCommunityId)
            viewModel.loadSpecificCommunities(parentCommunityId)
        }
    }

    Log.d("Community Debug", communities.toString())

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = parentCommunityName ?: stringResource(R.string.defaultTitle),
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
            // Use ScrollableSpecificCommunityColumn to display the communities
            ScrollableSpecificCommunityColumn(
                communityList = communities,
                navController = navController,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    navController.navigate("create_community_screen/${parentCommunityId}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Criar Comunidade")
            }
        }
    }
}

