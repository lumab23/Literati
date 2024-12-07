package com.aula.literatiapp.presentation.screens.community.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight

// TODO: edit the design (change the moderation button of position)
@Composable
fun CommunityBackNavigationDashBoard(
    value: String,
    navController: NavController,
    parentCommunityId: String,
    communityId: String,
    viewModel: CommunityViewModel = viewModel(),
    onJoinClick: () -> Unit = { viewModel.joinCommunity(parentCommunityId, communityId) },
    onModerationClick: () -> Unit = {
        navController.navigate("moderation_screen/${parentCommunityId}/${communityId}")
    },
    onNavigateAway: () -> Unit = {
        navController.navigate("specific_community_info/${parentCommunityId}/${communityId}")
    }
) {

    // todo: if it goes wrong change it to true
    val isMemberState = viewModel.isMember.collectAsState(initial =  false)
    val isAdminState = viewModel.isAdmin.collectAsState(initial = false)


    val isMember = isMemberState.value
    val isAdmin = isAdminState.value


    LaunchedEffect(Unit) {
        viewModel.checkUserStatus(parentCommunityId, communityId)
    }

    Column(
        Modifier
            .height(80.dp)
            .background(brush = gradientBrushLight)
            .padding(horizontal = 8.dp)
            .clickable {
                onNavigateAway()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .height(70.dp)
        ) {
            val (icon, text, joinButton, moderationButton) = createRefs()

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(50.dp)
                    .constrainAs(icon) {
                        start.linkTo(parent.start, margin = 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "back",
                    tint = Color.White
                )
            }

            Text(
                text = value,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(text) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )

            if (!isMember) {
                Button(
                    onClick = { onJoinClick() },
                    modifier = Modifier
                        .constrainAs(joinButton) {
                            end.linkTo(parent.end, margin = 16.dp)
                            bottom.linkTo(parent.bottom)
                        }
                ) {
                    Text("Join Community")
                }
            }else if(isMember && !isAdmin){
                Button(
                    onClick = { viewModel.leaveCommunity(parentCommunityId, communityId) },
                    modifier = Modifier
                        .constrainAs(joinButton) {
                            end.linkTo(parent.end, margin = 16.dp)
                            bottom.linkTo(parent.bottom)
                        }
                ) {
                    Text("Leave community")
                }
            }else if(isAdmin){
                Button(
                    onClick = { onModerationClick() },
                    modifier = Modifier
                        .constrainAs(moderationButton) {
                            end.linkTo(parent.end, margin = 16.dp)
                            top.linkTo(parent.top)
                        }
                ) {
                    Text("Moderation")
                }
            }
        }
    }
}


