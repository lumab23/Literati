package com.aula.literatiapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.components.SearchBarComponent
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.model.Community
import java.util.Locale.Category
import androidx.compose.ui.text.font.FontWeight
import com.aula.literatiapp.components.CommunityCard
import com.aula.literatiapp.navigation.Screen
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.aula.literatiapp.components.BackNavigationDashboard
import com.aula.literatiapp.components.CommunityImageSelection
import com.aula.literatiapp.components.MainDashboard
import com.aula.literatiapp.ui.theme.gradientBrush
import com.aula.literatiapp.ui.theme.lightGreen


@Composable
fun CommunityScreen(navController: NavController) {
    val comunidades = listOf(
        Community("Fantasia", "https://i.pinimg.com/564x/4e/be/d6/4ebed6d42634cb724ce5bcfbc95bbf41.jpg", "Para aqueles que gostam de fantasia", "Fantasia"),
        Community("Romance", "https://i.pinimg.com/564x/5a/9b/43/5a9b439115de910523c3474a0a728f78.jpg", "Para aqueles que gostam de romance", "Romance"),
        Community("Ficção Científica", "https://i.pinimg.com/564x/05/10/b2/0510b2703f70284c8ea255fb9f77ee28.jpg", "Para aqueles que gostam de Ficção Científica", "Ficção Científica"),
        Community("Comics", "https://i.pinimg.com/736x/c3/0e/20/c30e2025aeaf8bc13ca35182db791b7f.jpg", "Para aqueles que gostam de livros Comics", "Comics")
    )

    Scaffold(
        topBar = {
            MainDashboard(value = stringResource(id = R.string.comunidades), fontSize = 20.sp)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        // Box para sobrepor a SearchBar e a ScrollableCommunityColumn
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            // Coluna principal que mantém a SearchBar fixa no topo
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Barra de pesquisa fixa
                SearchBarComponent(
                    texto = stringResource(id = R.string.encontre_comunidades),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )

                // Espaçador para garantir espaço entre a SearchBar e a lista
                //Spacer(modifier = Modifier.height(16.dp)) // Ajuste a altura conforme necessário

                // Chama a função que exibe a lista de comunidades
                ScrollableCommunityColumn(
                    communityList = comunidades,
                    navController = navController,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .weight(1f) // Isso permite que a coluna ocupe o restante do espaço
                )
            }

            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CreateCommunityScreen.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .width(140.dp)
                    .height(60.dp),
                containerColor = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = gradientBrush)
                ) {
                    Text(
                        text = "Criar Comunidade",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}



@Composable
fun EspCommunityScreen(navController: NavController) {
    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = stringResource(R.string.nome_comunidade),
                navController = navController
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
            BackNavigationDashboard(
                value = stringResource(R.string.criar_comunidade),
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