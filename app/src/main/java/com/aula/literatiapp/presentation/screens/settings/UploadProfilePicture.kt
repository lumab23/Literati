package com.aula.literatiapp.presentation.screens.settings

import android.graphics.ImageDecoder
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
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.bitmapToBase64
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
    //var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            val bitmap = ImageDecoder.decodeBitmap(source)
            val base64Image = bitmapToBase64(bitmap)  // Converter para Base64
            settingsViewModel.uploadBase64ProfilePicture(base64Image) // Enviar Base64 para o ViewModel
        }
    }

    LaunchedEffect(launcher) {
        settingsViewModel.fetchUserData()
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

                AsyncImage(
                    model = settingsViewModel.userProfilePictureUrl.collectAsState().value.ifEmpty { R.drawable.blank_profile_pic },
                    contentDescription = "Foto de Perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .width(100.dp)
                        .height(100.dp)
                        .clip(CircleShape)
                        .clickable {
                            launcher.launch("image/*")
                        }
                        .border(1.dp, Color.Black, CircleShape),
                    contentScale = ContentScale.Crop
                )

                Log.d("Image selection", imageUri.toString())

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        imageUri?.let { uri ->
                            val bitmap = ImageDecoder.createSource(context.contentResolver, uri).let {
                                ImageDecoder.decodeBitmap(it)
                            }
                            val base64Image = bitmapToBase64(bitmap)
                            settingsViewModel.uploadBase64ProfilePicture(base64Image)
                        }
                    },
                    enabled = imageUri != null
                ) {
                    Text(text = "Salvar Imagem")
                }


                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    settingsViewModel.removeProfilePicture { success, message ->
                        val snackBarMessage = if (success) {
                            "Foto de perfil removida com sucesso!"
                        } else {
                            "Erro ao remover: $message"
                        }

                        CoroutineScope(Dispatchers.Main).launch {
                            hostState.showSnackbar(snackBarMessage)
                        }
                    }
                }) {
                    Text(text = "Remover Foto de Perfil")
                }

            }
        }
    }
}

