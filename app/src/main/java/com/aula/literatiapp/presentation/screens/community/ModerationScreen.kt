package com.aula.literatiapp.presentation.screens.community

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.domain.model.User
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.navigation.Screen
import com.aula.literatiapp.presentation.screens.community.components.MemberCard
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel

@Composable
fun ModerationScreen(
    communityId: String,
    parentCommunityId: String,
    navController: NavController,
    viewModel: CommunityViewModel = viewModel()
) {
    val community by viewModel.community.collectAsState()
    val admins by viewModel.admins.collectAsState()
    val members by viewModel.members.collectAsState()

    var showAddAdminDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(community?.name ?: "") }
    var description by remember { mutableStateOf(community?.description ?: "") }
    var imageUrl by remember { mutableStateOf(community?.imageUrl ?: "") }


    LaunchedEffect(communityId) {
        viewModel.getCommunityById(parentCommunityId, communityId)
        viewModel.getAdmins(parentCommunityId, communityId)
        viewModel.getNonAdminMembers(parentCommunityId, communityId)
    }

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = "Moderation",
                navController = navController
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            community?.imageUrl?.let { currentImageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(currentImageUrl),
                    contentDescription = "Community Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Community Name") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Community Description") },
                modifier = Modifier.fillMaxWidth()
            )


            Button(onClick = { showAddAdminDialog = true }) {
                Text("Add New Admin")
            }


            Button(onClick = {
                viewModel.updateCommunity(
                    parentCommunityId = parentCommunityId,
                    communityId = communityId,
                    name = name,
                    description = description
                )
            }) {
                Text("Save Changes")
            }
        }
    }


    if (showAddAdminDialog) {
        AddAdminDialog(
            members = members,
            navController = navController,
            onDismiss = { showAddAdminDialog = false },
            onAddAdmin = { userId ->
                viewModel.promoteToAdmin(parentCommunityId, communityId, userId)
                showAddAdminDialog = false
            }
        )
    }
}


@Composable
fun AddAdminDialog(
    members: List<User>,
    navController: NavController,
    onDismiss: () -> Unit,
    onAddAdmin: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Admin") },
        text = {
            Column {
                Text("Select a user to add as admin", fontWeight = FontWeight.Bold)

                LazyColumn {
                    items(members) { member ->
                        MemberCard(
                            isAdmin = false,
                            member = member,
                            onClick = {
                                onAddAdmin(member.id)
                            }
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


