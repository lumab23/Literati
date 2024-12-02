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
import com.aula.literatiapp.domain.model.Community

@Composable
fun ScrollableSpecificCommunityColumn(
    communityList: List<Community>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(communityList) { community ->
            CommunityCard(
                community = community,
                onClick = {
                    navController.navigate("specific_community/${community.parentCommunityId}/${community.id}")
                }
            )
        }
    }
}