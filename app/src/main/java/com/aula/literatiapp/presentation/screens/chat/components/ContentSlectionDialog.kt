package com.aula.literatiapp.presentation.screens.chat.components

import android.graphics.Camera
import android.widget.AdapterView.OnItemSelectedListener
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContentSlectionDialog(onCameraSelected: () -> Unit, onGallerySelected: () -> Unit) {
    //pop up que deixa o usuário escolher entre gallery e câmera
    AlertDialog(onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = onCameraSelected) {
                Text(text = "Camera")
            }
        },
        dismissButton = {
            TextButton(onClick = onGallerySelected) {
                Text(text = "Gallery")
            }
        },
        title = { Text(text = "Select your source?") },
        text = { Text(text = "Would you like to pick an image from the gallery or use the") })
}

@Preview
@Composable
private fun ContentPrev() {
    ContentSlectionDialog(onCameraSelected = {}, onGallerySelected = {})
}