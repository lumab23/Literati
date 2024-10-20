package com.aula.literatiapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.R
import com.aula.literatiapp.components.AvaliacaoComponent
import com.aula.literatiapp.components.BiggerBookCard
import com.aula.literatiapp.components.BookInfo
import com.aula.literatiapp.components.BookScreenWithTabs
import com.aula.literatiapp.components.CategorySection
import com.aula.literatiapp.components.StarRatingBar
import com.aula.literatiapp.model.Book
import com.aula.literatiapp.ui.theme.gradientBrush
import com.aula.literatiapp.ui.theme.lightGreen

@Composable
fun BookScreen(navController: NavController) {

    val book = remember {
        Book(
            title = "The Handmaid's Tale",
            authors = listOf("Margaret Atwood"),
            publisher = "Penguin Random House",
            description = "Offred is a Handmaid in the Republic of Gilead. She may leave the home of the Commander and his wife once a day to walk to food markets whose signs are now pictures instead of words because women are no longer allowed to read. She must lie on her back once a month and pray that the Commander makes her pregnant, because in an age of declining births, Offred and the other Handmaids are valued only if their ovaries are viable. Offred can remember the years before, when she lived and made love with her husband, Luke; when she played with and protected her daughter; when she had a job, money of her own, and access to knowledge. But all of that is gone now…\n" +
                    "\n" +
                    "Funny, unexpected, horrifying, and altogether convincing, The Handmaid's Tale is at once scathing satire, dire warning, and tour de force.",
            imageUrl = "",
            tags = "Dystopian",
            genres = listOf("Clássicos", "Literatura", "Fantasia", "Ficção Científica"),
            id = "1",
            volumeInfo = "3 edição",
            pages = "400",
            review = "Muito bom!!"
        )
    }

    Scaffold(
        topBar = {
            MyBookDashboard(navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                item {
                    BookInfo(book = book, navController = navController)
                    Spacer(modifier = Modifier.height(15.dp))
                    AvaliacaoComponent(navController = navController)
                    Spacer(modifier = Modifier.height(15.dp))
                    BookScreenWithTabs(book = book)
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookDashboard(navController: NavController) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var showTagDialog by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    Column(
        Modifier
            .height(80.dp)
            .background(brush = gradientBrush),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .height(70.dp)
        ) {
            val (backIcon, moreIcon) = createRefs()

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(50.dp)
                    .constrainAs(backIcon) {
                        start.linkTo(parent.start, margin = 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "back",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = { showBottomSheet = true },
                modifier = Modifier
                    .size(50.dp)
                    .constrainAs(moreIcon) {
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "back",
                    tint = Color.White
                )
            }

        }

        val categories = listOf("Review", "Adicione uma tag", "Compartilhar")

        // bottom sheet
        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {

                CategorySection(
                    title = "",
                    categories = categories,
                    onCategoryClick = { category ->
                        when (category) {
                            "Review" -> {
                                navController.navigate("make_review_screen")
                            }
                            "Adicione uma tag" -> {
                                showBottomSheet = false
                                showTagDialog = true
                            }
                        }
                    }
                )

            }
        }

        if (showTagDialog) {
            SelectTagDialog(
                onDismissRequest = { showTagDialog = false },
                onTagSelected = { tag ->
                    println("Tag selecionada: $tag")
                    showTagDialog = false
                }
            )
        }
    }
}

@Composable
fun MakeReviewScreen(
    onCancel: () -> Unit,
    onSubmitReview: (String, Float) -> Unit
) {
    var reviewText by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0f) }
    val maxReviewLength = 500

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top row with Cancel and Submit buttons
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
                onClick = { onSubmitReview(reviewText, rating) },
                enabled = reviewText.isNotEmpty() && reviewText.length <= maxReviewLength && rating > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightGreen
                )
            ) {
                Text(
                    text = stringResource(id = R.string.enviar_review)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Rating (Estrelas)
        Text(text = stringResource(id = R.string.rate_book))
        Spacer(modifier = Modifier.height(8.dp))

        StarRatingBar(
            rating = rating,
            onRatingChanged = { newRating -> rating = newRating }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row with Profile Image and Review TextField
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
                value = reviewText,
                onValueChange = { text ->
                    if (text.length <= maxReviewLength) reviewText = text
                },
                placeholder = { Text(text = stringResource(id = R.string.reviewPlaceholder)) },
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

        // Character count
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "${reviewText.length}/$maxReviewLength",
                color = if (reviewText.length > maxReviewLength) Color.Red else Color.Gray
            )
        }
    }
}

@Composable
fun AddTagDialog(
    onDismissRequest: () -> Unit,
    onTagAdded: (List<String>) -> Unit
) {
    var newTag by remember { mutableStateOf("") }
    var selectedTags by remember { mutableStateOf(listOf<String>()) }

    // Tags predefinidas
    val predefinedTags = listOf("Quero ler", "Abandonei", "Estou lendo", "Quero trocar")

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Adicionar Tag", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))

                // Exibir tags predefinidas para seleção
                Text(text = "Selecione uma tag:")
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow {
                    items(predefinedTags) { tag ->
                        SelectableChip(
                            text = tag,
                            isSelected = selectedTags.contains(tag),
                            onSelected = { isSelected ->
                                selectedTags = if (isSelected) {
                                    selectedTags + tag
                                } else {
                                    selectedTags - tag
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo para adicionar nova tag
                OutlinedTextField(
                    value = newTag,
                    onValueChange = { newTag = it },
                    label = { Text("Nova Tag") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    if (newTag.isNotEmpty()) {
                        selectedTags = selectedTags + newTag
                        newTag = "" // Limpar campo de texto
                    }
                }) {
                    Text("Adicionar Tag")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Exibir as tags já selecionadas
                Text(text = "Tags selecionadas:")
                LazyRow {
                    items(selectedTags) { tag ->
                        Chip(tag)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botão para salvar e fechar o diálogo
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancelar")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    TextButton(onClick = {
                        onTagAdded(selectedTags)
                        onDismissRequest()
                    }) {
                        Text("Salvar")
                    }
                }
            }
        }
    }
}

@Composable
fun SelectableChip(
    text: String,
    isSelected: Boolean,
    onSelected: (Boolean) -> Unit
) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
    val contentColor = if (isSelected) Color.White else Color.Black

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onSelected(!isSelected) }
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = contentColor
        )
    }
}

@Composable
fun Chip(text: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = Color.White
        )
    }
}

@Composable
fun SelectTagDialog(
    onDismissRequest: () -> Unit,
    onTagSelected: (String) -> Unit
) {
    // Lista de tags predefinidas
    val predefinedTags = listOf("Quero ler", "Abandonei", "Estou lendo", "Quero trocar", "Completo")
    var selectedTag by remember { mutableStateOf("") }  // Guardar a tag selecionada

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Selecione uma tag", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                // Exibir as tags predefinidas
                LazyColumn {
                    items(predefinedTags) { tag ->
                        SelectableChip(
                            text = tag,
                            isSelected = tag == selectedTag,
                            onSelected = { isSelected ->
                                selectedTag = if (isSelected) tag else ""
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botões de ação
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancelar")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    TextButton(onClick = {
                        if (selectedTag.isNotEmpty()) {
                            onTagSelected(selectedTag)
                            onDismissRequest()
                        }
                    }) {
                        Text("Salvar")
                    }
                }
            }
        }
    }
}





@Preview(
    showSystemUi = true,
    name = "Bookshelv"
)
@Composable
fun BooksScreen() {
    val navController = rememberNavController()
    BookScreen(navController = navController)
}