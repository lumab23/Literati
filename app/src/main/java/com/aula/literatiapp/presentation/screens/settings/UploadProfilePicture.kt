package com.aula.literatiapp.presentation.screens.settings

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.screens.settings.viewModels.SettingsViewModel
import com.aula.literatiapp.presentation.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UploadProfilePicture(
    navController: NavController,
    settingsViewModel: SettingsViewModel,
    hostState: SnackbarHostState
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Lançador para selecionar uma imagem
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = stringResource(R.string.upload_profile_picture),
                navController = navController
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        model = selectedImageUri?.toString()
                            ?: settingsViewModel.userProfilePictureUrl.collectAsState().value
                    ),
                    contentDescription = "Foto de Perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .width(100.dp)
                        .height(100.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Black, CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { launcher.launch("image/*") }) {
                    Text(text = "Selecionar Foto de Perfil")
                }

                Log.d("Image selection", selectedImageUri.toString())

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        selectedImageUri?.let { uri ->
                            val uriString = uri.toString()
                            settingsViewModel.updateUserProfilePictureInFirestore(
                                photoUrl = uriString,
                                onComplete = { success, message ->
                                    val snackBarMessage = if (success) {
                                        "Foto de perfil atualizada com sucesso"
                                    } else {
                                        "Erro: $message"
                                    }

                                    if (success) {
                                        navController.popBackStack()
                                    } else {
                                        CoroutineScope(Dispatchers.Main).launch {
                                            hostState.showSnackbar(snackBarMessage)
                                        }
                                    }
                                }
                            )
                        }
                    },
                    enabled = selectedImageUri != null
                ) {
                    Text(text = "Salvar Foto de Perfil")
                }
            }
        }
    }
}


@Preview
@Composable
fun UploadProfilePicturePreview() {
    AppTheme {
        UploadProfilePicture(
            navController = rememberNavController(),
            settingsViewModel = SettingsViewModel(),
            hostState = remember { SnackbarHostState() }
        )
    }
}