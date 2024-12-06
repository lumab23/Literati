package com.aula.literatiapp.presentation.screens.community.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun MemberList(
    parentCommunityId: String,
    communityId: String,
    viewModel: CommunityViewModel = viewModel()
) {

    val members by viewModel.members.collectAsState(initial = emptyList())


    LaunchedEffect(communityId) {
        viewModel.getNonAdminMembers(parentCommunityId, communityId)
    }

    LazyColumn {
        items(members) { member ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        viewModel.promoteToAdmin(parentCommunityId, communityId, member.id)
                    },
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = member.username,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Membro",
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
