package com.aula.literatiapp.presentation.screens.community

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.presentation.screens.community.components.CommunityImageSelection
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.rememberUpdatedState
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.screens.community.components.CommunityTextField
import com.aula.literatiapp.presentation.screens.community.components.MyDescriptionField

// TODO: Adicionar botão para confirmar nova comunidade
// TODO: Adicionar seção para adicionar moderadores
@Composable
fun CreateCommunityScreen(navController: NavController) {
    var community by remember { mutableStateOf(
        Community(
            id = "1",
            name = "Romance",
            description = "para amantes de romance",
            imageUrl = null,
            specificCommunityImageUrl = null,
            specificCommunityName = "jane austen"
        )
    )}


    val communityCategories = listOf("Fantasia", "Romance", "Ficção Científica", "Comics", "Mangá", "Esportes", "Tecnologia")
    val selectedCategories = remember { mutableStateOf(setOf<String>()) }

    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                community = community.copy(specificCommunityImageUrl = uri.toString())
                Toast.makeText(context, "Image selected!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "No image selected", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = "Criar Comunidade",
                navController = navController
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CommunityImageSelection(
                community = community,
                onImageClick = {
                    imagePickerLauncher.launch("image/*")
                }
            )

            CommunityTextField(
                labelValue = "Nome da comunidade",
                value = community.specificCommunityName,
                onValueChange = { community = community.copy(specificCommunityName = it) }
            )

            MyDescriptionField(
                labelValue = "Descrição da comunidade",
                value = community.description,
                onValueChange = { community = community.copy(description = it) }
            )

            Text(text = "Categorias", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Column {
                communityCategories.forEach { category ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedCategories.value.contains(category),
                            onCheckedChange = { isChecked ->
                                selectedCategories.value = if (isChecked) {
                                    selectedCategories.value + category
                                } else {
                                    selectedCategories.value - category
                                }
                            }
                        )
                        Text(text = category)
                    }
                }

                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(text = "Criar Comunidade")
                }
            }
        }
    }
}

