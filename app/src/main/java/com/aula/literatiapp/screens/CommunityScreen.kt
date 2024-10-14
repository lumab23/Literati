package com.aula.literatiapp.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.components.SearchBarComponent
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.Dashboard
import com.aula.literatiapp.model.Community
import java.util.Locale.Category
import androidx.compose.ui.text.font.FontWeight
import com.aula.literatiapp.components.CommunityCard
import com.aula.literatiapp.navigation.Screen
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.components.CommunityTextField
import com.aula.literatiapp.components.MyDescriptionFieldComponent
import com.aula.literatiapp.components.MyTextFieldComponent
import com.aula.literatiapp.components.ScrollableCommunityColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import com.aula.literatiapp.components.CommunityImageSelection


@Composable
fun CommunityScreen(navController: NavController) {

    val comunidades = listOf(
        Community("Fantasia","teste", "Para aqueles que gostam de fantasia", "Fantasia"),
        Community("Romance","teste", "Para aqueles que gostam de romance", "Romance"),
        Community("Killua", "teste", "Para aqueles que gostam do Killua", "Mangá"),
        Community("HunterXHunter", "teste", "Para aqueles que gostam de HunterXHunter", "Mangá")
    )

    Scaffold(
        topBar = {
            SearchBarComponent(
                texto = "Encontre comunidades",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp)
            )
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            // Chama a função que exibe a lista de comunidades
            ScrollableCommunityColumn(
                communityList = comunidades,
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .align(Alignment.TopCenter)
            )

            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CreateCommunityScreen.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text(text = "Criar Comunidade")
            }
        }
    }
}


@Composable
fun EspCommunityScreen(navController: NavController) {
    Scaffold(
        topBar = {
            Dashboard(
                value = stringResource(R.string.nome_comunidade),
                modifier = Modifier
            )
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding()
        ) {  }

    }
}

@Composable
fun CreateCommunityScreen(navController: NavController) {
    val newCommunity: Community
    val categories = listOf("Fantasia", "Romance", "Mangá", "Esportes", "Tecnologia")
    val selectedCategories = remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        topBar = {
            Dashboard(
                value = stringResource(R.string.criar_comunidade),
                modifier = Modifier
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
            // Campo de seleção de imagem (sem a lógica de seleção ainda
            CommunityImageSelection()

            // Campo de texto para o nome da comunidade
            CommunityTextField("Nome da comunidade")

            // Campo de texto para a descrição
            MyDescriptionFieldComponent("Descrição da comunidade")

            // Seção de categorias
            Text(text = "Categorias", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            // Lista de checkboxes de categorias
            Column {
                categories.forEach { category ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedCategories.value.contains(category),
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    selectedCategories.value = selectedCategories.value + category
                                } else {
                                    selectedCategories.value = selectedCategories.value - category
                                }
                            }
                        )
                        Text(text = category)
                    }
                }
            }

            // Botão para criar a comunidade
            Button(
                onClick = {
                    // Lógica para criar a comunidade
                    navController.popBackStack() // Volta para a tela anterior
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Criar Comunidade")
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    name = "Create Community Screen"
)
@Composable
fun CommunityScreenPreview() {
    val navController = rememberNavController()
    CreateCommunityScreen(navController = navController)
}
