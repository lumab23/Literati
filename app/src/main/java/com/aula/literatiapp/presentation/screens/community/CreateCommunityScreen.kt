package com.aula.literatiapp.presentation.screens.community

import android.net.Uri
import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.screens.community.components.CommunityTextField
import com.aula.literatiapp.presentation.screens.community.components.MyDescriptionField
import com.aula.literatiapp.presentation.screens.community.viewModels.CommunityViewModel

// TODO: Adicionar botão para confirmar nova comunidade
// TODO: Adicionar seção para adicionar moderadores
@Composable
fun CreateCommunityScreen(navController: NavController, parentCommunityId: String?, viewModel: CommunityViewModel = viewModel()) {
    var community by remember {
        mutableStateOf(
            Community(
                id = "",
                name = "",
                description = "",
                imageUrl = null,
            )
        )
    }

    val communityCategories = listOf("Fantasia", "Romance", "Ficção Científica", "Comics", "Mangá", "Esportes")
    val selectedCategories = remember { mutableStateOf(setOf<String>()) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                community = community.copy(imageUrl = uri.toString())
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

            Log.d("Community Debug", community.imageUrl.toString())

            CommunityTextField(
                labelValue = "Nome da comunidade",
                value = community.name,
                onValueChange = { community = community.copy(name = it) }
            )
            Log.d("Community Debug",community.name.toString())

            MyDescriptionField(
                labelValue = "Descrição da comunidade",
                value = community.description,
                onValueChange = { community = community.copy(description = it) }
            )

            Log.d("Community Debug",community.description.toString())

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
                                    selectedCategories.value + category // Add the category
                                } else {
                                    selectedCategories.value - category // Remove the category
                                }
                                community = community.copy(categories = selectedCategories.value.toList())
                            }
                        )
                        Text(text = category)
                    }
                }

                Log.d("Community Debug",community.categories.toString())

                Button(
                    onClick = {
                        Log.d("Community Debug", community.toString())
                        viewModel.createCommunity(community, parentCommunityId.toString())
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