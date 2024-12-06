package com.aula.literatiapp.presentation.screens.community.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close

@Composable
fun AdminsList(
    parentCommunityId: String,
    communityId: String,
    viewModel: CommunityViewModel = viewModel()
) {

    val admins by viewModel.admins.collectAsState(initial = emptyList())

    LaunchedEffect(communityId) {
        viewModel.getAdmins(parentCommunityId, communityId)
    }

    LazyColumn {
        items(admins) { admin ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = admin.username,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remove Admin",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                viewModel.removeAdmin(parentCommunityId, communityId, admin.id)
                            }
                    )
                }
            }
        }
    }
}