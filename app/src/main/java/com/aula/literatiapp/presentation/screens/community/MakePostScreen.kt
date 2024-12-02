package com.aula.literatiapp.presentation.screens.community

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel
import com.aula.literatiapp.presentation.ui.theme.onPrimaryContainerLight
import com.aula.literatiapp.presentation.ui.theme.onPrimaryLight

@Composable
fun MakePostScreen(
    parentCommunityId: String,
    communityId: String,
    parentPostId: String?,
    onCancel: () -> Unit,
    onPost: (String) -> Unit,
    navController: NavController,
    viewModel: CommunityViewModel = viewModel()
) {
    var postText by remember { mutableStateOf("") }
    val maxPostLength = 200



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
                onClick = { onPost(postText) },
                enabled = postText.isNotEmpty() && postText.length <= maxPostLength,
                colors = ButtonDefaults.buttonColors(
                    containerColor = onPrimaryContainerLight
                )
            ) {
                Text(
                    text = stringResource(id = R.string.postar)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = viewModel.loadUserProfilePicture { profileImageUrl ->
                    if (profileImageUrl != null) {
                        // Use the URL, e.g., load into an image
                        println("Profile image URL: $profileImageUrl")
                    } else {
                        // Handle missing or error case
                        println("No profile image found or error occurred.")
                    } } ?: "https://example.com/default_profile_image.jpg",
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = postText,
                onValueChange = { text ->
                    if (text.length <= maxPostLength) postText = text
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
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "${postText.length}/$maxPostLength",
                color = if (postText.length > maxPostLength) Color.Red else Color.Gray
            )
        }
    }
}

