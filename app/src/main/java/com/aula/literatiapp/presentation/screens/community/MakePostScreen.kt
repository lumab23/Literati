package com.aula.literatiapp.presentation.screens.community

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.CommunityPost
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel
import com.aula.literatiapp.presentation.screens.settings.viewModels.SettingsViewModel
import com.aula.literatiapp.presentation.ui.theme.onPrimaryContainerLight
import com.aula.literatiapp.presentation.ui.theme.onPrimaryLight
import java.util.Date

@Composable
fun MakePostScreen(
    parentCommunityId: String,
    communityId: String,
    parentPostId: String?,
    onCancel: () -> Unit,
    onPost: (CommunityPost) -> Unit,
    navController: NavController,
    viewModel: CommunityViewModel = viewModel()
) {

    val settingsViewModel: SettingsViewModel = viewModel()
    val profilePictureUrl by settingsViewModel.userProfilePictureUrl.collectAsState(initial = "")

    var post by remember {
        mutableStateOf(
            CommunityPost(
                id = "",
                userId = "userId", // Substitua pelo ID real do usuário
                userName = "UserName", // Substitua pelo nome real do usuário
                content = "",
                imageUrl = null,
                createdAt = Date(System.currentTimeMillis()),
                likesCount = 0,
                commentsCount = 0,
                likedBy = emptyMap()
            )
        )
    }

    val maxPostLength = 200
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                post = post.copy(imageUrl = uri.toString())
                Toast.makeText(context, "Imagem selecionada!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Nenhuma imagem selecionada.", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { onCancel() }) {
                Text(
                    text = stringResource(id = R.string.cancelar),
                    color = onPrimaryLight
                )
            }

            Button(
                onClick = {
                    if(parentPostId == null){
                        viewModel.createPost(post, parentCommunityId, communityId)
                        onPost(post)
                    }else{
                        viewModel.addComment(post, parentCommunityId, communityId, parentPostId)
                        onPost(post)
                    }
                },
                enabled = post.content.isNotEmpty() && post.content.length <= maxPostLength,
                colors = ButtonDefaults.buttonColors(
                    containerColor = onPrimaryContainerLight
                )
            ) {
                Text(text = stringResource(id = R.string.postar))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.Top) {
            AsyncImage(
                // TODO: Push the profile picture from the firebase storage
                model = profilePictureUrl.ifEmpty { R.drawable.blank_profile_pic },
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = post.content,
                onValueChange = { text ->
                    if (text.length <= maxPostLength) {
                        post = post.copy(content = text)
                    }
                },
                placeholder = { Text(text = stringResource(id = R.string.postPlaceholder)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { imagePickerLauncher.launch("image/*") }) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = "Adicionar Imagem",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = "${post.content.length}/$maxPostLength",
                color = if (post.content.length > maxPostLength) Color.Red else Color.Gray
            )
        }
    }
}

