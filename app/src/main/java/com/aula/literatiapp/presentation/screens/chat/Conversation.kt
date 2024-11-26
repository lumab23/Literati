package com.aula.literatiapp.presentation.screens.chat

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import android.net.Uri

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.aula.literatiapp.presentation.screens.chat.components.ChatMessages
import com.aula.literatiapp.presentation.screens.chat.components.ContentSlectionDialog
import android.os.Environment
import androidx.core.content.FileProvider
import com.aula.literatiapp.Manifest
import com.aula.literatiapp.presentation.screens.chat.viewModels.ConversationViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun Conversation(navController: NavController, channelId: String) {

    Scaffold(
        containerColor = Color.Black
    ) {
        val chooserDialog = remember {
            mutableStateOf(false)
        }
        val cameraImageUri = remember {
            mutableStateOf<Uri?>(null)
        }
        val camerImageLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
        ) {sucess ->
            if(sucess){
                cameraImageUri.value?.let {

                }
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            val viewModel: ConversationViewModel = hiltViewModel()
            LaunchedEffect (key1 = true){
                viewModel.listenForMessages(channelId)
            }

            val messages = viewModel.messages.collectAsState()

            ChatMessages(
                messages = messages.value,
                onSendMessage = { message ->
                    viewModel.sendMessage(channelId, message)
                },
                onImageClicked = {
                    chooserDialog.value = true
                }
            )
        }

        fun createImageUri(): android.net.Uri {
            val context = navController.context
            val timeStamp = SimpleDateFormat("yyyyMMMdd_HHmmss", Locale.getDefault()).format(Date())

            // Diretório onde o arquivo será salvo
            val storageDir = ContextCompat.getExternalFilesDirs(
                context,
                Environment.DIRECTORY_PICTURES
            ).firstOrNull() ?: throw IllegalStateException("Storage directory not found")

            // Criação do arquivo de imagem
            val imageFile = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)

            // Retorna o Uri usando o FileProvider
            return FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                imageFile
            ).also { uri ->
                // Atualiza o valor do cameraImageUri com o URI criado
                cameraImageUri.value = uri // Isso permanece sendo android.net.Uri
            }
        }
        val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            if(isGranted){
                camerImageLauncher.launch(createImageUri())
            }
        }
        if (chooserDialog.value) {
            ContentSlectionDialog(onCameraSelected = {
                chooserDialog.value = false
                if (navController.context.checkSelfPermission("android.permission.CAMERA") == PackageManager.PERMISSION_GRANTED) {
                    camerImageLauncher.launch(createImageUri())
                } else {
                    permissionLauncher.launch("android.permission.CAMERA")
                }
            }, onGallerySelected = {
                chooserDialog.value = false
                })
        }
    }
}