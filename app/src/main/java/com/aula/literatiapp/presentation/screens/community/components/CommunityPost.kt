package com.aula.literatiapp.presentation.screens.community.components

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.CommunityPost
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel
import com.aula.literatiapp.presentation.screens.settings.viewModels.SettingsViewModel

@Composable
fun CommunityPostCard(
    parentCommunityId: String,
    communityId: String,
    post: CommunityPost,
    isMember: Boolean,
    viewModel: CommunityViewModel = viewModel(),
    navController: NavController
) {
    // Estados locais para likes e isLiked para este post específico
    var likes by remember { mutableStateOf(post.likesCount) }
    var isLiked by remember { mutableStateOf(false) }
    val comments by viewModel.comments.collectAsState(initial = emptyList())

    var settingsViewModel: SettingsViewModel = viewModel()
    var userProfilePicUrl by remember { mutableStateOf("") }

    // Carrega os dados iniciais para este post
    LaunchedEffect(post.id) {
        settingsViewModel.fetchUserData()
        viewModel.loadAllComments(parentCommunityId, communityId, post.id)
        viewModel.loadLikeCounts(parentCommunityId, communityId, post.id) { count ->
            likes = count // Atualiza o estado local de likes
        }
        viewModel.loadIsLiked(parentCommunityId, communityId, post.id) { liked ->
            isLiked = liked // Atualiza o estado local de isLiked
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.surface)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = settingsViewModel.userProfilePictureUrl.collectAsState().value.ifEmpty { R.drawable.blank_profile_pic },
                    contentDescription = "Avatar of ${post.userName}",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = post.userName, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = post.content)
                    Text(text = post.createdAt.toString(), fontSize = 12.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (isMember) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                viewModel.likePost(parentCommunityId, communityId, post.id) { newLikedState, newLikeCount ->
                                    isLiked = newLikedState
                                    likes = newLikeCount
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = if (isLiked) "Dislike" else "Like"
                            )
                        }
                        Text(text = "$likes curtidas")
                    }

                    IconButton(onClick = {
                        navController.navigate("create_post_screen/$parentCommunityId/$communityId?parentPostId=${post.id}")
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Comment,
                            contentDescription = "Comentar"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // todo: edit the comments design
                comments.forEach { comment ->
                    Text(
                        text = comment.content ?: "No comment",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}

