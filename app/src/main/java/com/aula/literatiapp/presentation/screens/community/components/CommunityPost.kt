package com.aula.literatiapp.presentation.screens.community.components

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
import coil.compose.AsyncImage
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.CommunityPost
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel
import java.util.Date

@Composable
fun CommunityPostCard(parentCommunityId: String, communityId: String, post: CommunityPost, isMember: Boolean, viewModel: CommunityViewModel = viewModel()) {
    var likes by remember { mutableStateOf(post.likesCount) }
    var isLiked by remember { mutableStateOf(false) }  // Initial liked state should be handled here or via a parameter
    var commentText by remember { mutableStateOf("") }
    val comments by viewModel.comments.collectAsState(initial = emptyList())

    LaunchedEffect(post.id) {
        viewModel.loadAllComments(parentCommunityId, communityId, post.id)
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
                    model = post.imageUrl,
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
                                isLiked = !isLiked
                                likes += if (isLiked) 1 else -1
                            }
                        ) {
                            Icon(
                                imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = if (isLiked) "Dislike" else "Like"
                            )
                        }
                        Text(text = "$likes curtidas")
                    }

                    IconButton(onClick = { /* Handle comment functionality here */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Comment,
                            contentDescription = "Comentar"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

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
