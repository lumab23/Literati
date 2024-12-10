package com.aula.literatiapp.presentation.screens.community

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.domain.model.User
import com.aula.literatiapp.presentation.ui.theme.*
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
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(community?.name ?: "") }
    var description by remember { mutableStateOf(community?.description ?: "") }

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

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Community Name", color = getTextColor()) },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Community Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { showAddAdminDialog = true },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = gradientBrushLight, shape = RoundedCornerShape(16.dp))
            ) {
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

            Text("Admins", style = MaterialTheme.typography.h6, modifier = Modifier.padding(top = 16.dp))
            LazyColumn {
                items(admins) { admin ->
                    MemberCard(
                        isAdmin = true,
                        member = admin,
                        onClick = {
                            viewModel.removeAdmin(parentCommunityId, communityId, admin.id)
                        }
                    )
                }
            }

            Text("Members", style = MaterialTheme.typography.h6, modifier = Modifier.padding(top = 16.dp))
            LazyColumn {
                items(members) { member ->
                    MemberCard(
                        isAdmin = false,
                        member = member,
                        onClick = {
                            viewModel.promoteToAdmin(parentCommunityId, communityId, member.id)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { showDeleteConfirmationDialog = true },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Delete Community", color = Color.White)
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

    if (showDeleteConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmationDialog = false },
            title = { Text("Delete Community") },
            text = { Text("Are you sure you want to delete this community? This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteCommunity(
                            parentCommunityId = parentCommunityId,
                            communityId = communityId,
                            onComplete = {
                                showDeleteConfirmationDialog = false
                                navController.navigate("community_list/${parentCommunityId}")
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("Delete", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmationDialog = false }) {
                    Text("Cancel")
                }
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
        title = { Text("Add New Admin", color = getTextColor()) },
        text = {
            Column {
                Text("Select a user to add as admin", fontWeight = FontWeight.Bold, color = getTextColor())

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
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier
                    .background(brush = gradientBrushLight, shape = RoundedCornerShape(12.dp))
            ) {
                Text("Delete", color = Color.White)
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ModerationScreenPreview() {
    AppTheme {
        ModerationScreen(
            communityId = "community123",
            parentCommunityId = "parentCommunity456",
            navController = rememberNavController(),
            viewModel = viewModel() // Use um ViewModel de visualização
        )
    }
}
