package com.aula.literatiapp.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.aula.literatiapp.components.BackNavigationDashboard
import com.aula.literatiapp.components.CommunityImageSelection
import com.aula.literatiapp.components.ListOfCommunitiesComponent
import com.aula.literatiapp.components.MainDashboard
import com.aula.literatiapp.components.ReviewComponent
import com.aula.literatiapp.components.ScrollablePostList
import com.aula.literatiapp.model.Post
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
    val isMember = true

    val posts = listOf(
        Post("1", "@Alice", "https://example.com/avatar1.png", "Recomendação de leitura..."),
        Post("2", "@Bob", "https://example.com/avatar2.png", "Alguém recomenda um livro de fantasia?")
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = stringResource(R.string.janeAustencom),
                navController = navController
            )
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize() // Certifica-se de que a Box ocupe todo o espaço disponível
        ) {
            // Conteúdo principal da tela (posts)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                ScrollablePostList(posts, isMember, navController)
            }

            // Botão flutuante no canto inferior direito
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.MakePostScreen.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .width(60.dp)
                    .height(60.dp),
                containerColor = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = gradientBrush),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "post"
                    )
                }
            }
        }
    }
}

@Composable
fun MakePostScreen(
    onCancel: () -> Unit,
    onPost: (String) -> Unit
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
                    color = lightGreen
                )
            }

            Button(
                onClick = { onPost(postText) },
                enabled = postText.isNotEmpty() && postText.length <= maxPostLength,
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightGreen
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
            Image(
                painter = rememberAsyncImagePainter(model = "https://i.pinimg.com/enabled_hi/564x/35/d8/3d/35d83d2e796d5ae4558396ba4adf2cc8.jpg"),
                contentDescription = "profile picture",
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
                placeholder = {Text(text = stringResource(id = R.string.postPlaceholder))},
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

            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Criar Comunidade")
            }
        }
    }
}

@Composable
fun CommunityList(navController: NavController) {

    val communities = remember {
        listOf(
            Community(
                nome = "Amantes de Jane Austen",
                imagemUri = "https://i.pinimg.com/564x/47/b5/47/47b547ad30201ad69099c2cb6faff682.jpg",
                descricao = "Comunidade para todos que amam as obras de Jane Austen!",
                categoria = "Romance"
            ),
            Community(
                nome = "Percy Jackson",
                imagemUri = "https://i.pinimg.com/564x/51/62/fc/5162fcdd104398741191ad9dfd123c12.jpg",
                descricao = "Para fãs de Percy Jackson!",
                categoria = "Aventura"
            )
        )
    }
    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.comunidaderomance), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            items(communities.size) { index ->
                ListOfCommunitiesComponent(community = communities[index], navController = navController)

                // Exibe o divisor abaixo de cada review, exceto após o último item
                if (index < communities.size - 1) {
                    HorizontalDivider()
                }
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