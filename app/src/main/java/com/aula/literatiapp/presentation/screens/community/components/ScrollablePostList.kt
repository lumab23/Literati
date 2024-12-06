package com.aula.literatiapp.presentation.screens.community.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.CommunityPost

@Composable
fun ScrollablePostList(
    parentCommunityId: String,
    communityId: String,
    postList: List<CommunityPost>,
    isMember: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {

        items(postList) { post ->
            CommunityPostCard(
                parentCommunityId = parentCommunityId,
                communityId = communityId,
                post = post,
                isMember = isMember,
                navController = navController
            )
        }
    }
}
