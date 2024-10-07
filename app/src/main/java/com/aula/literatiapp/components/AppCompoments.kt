package com.aula.literatiapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.R
import com.aula.literatiapp.navigation.Screen
import com.aula.literatiapp.ui.theme.textColor

@Composable
fun NormalTextComponent(
    value: String
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp, // mudar para 18
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = textColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SmallTextComponent(
    value: String
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = textColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(
    value: String
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = textColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun MyTextFieldComponent(
    labelValue: String,
    leadingIcon: ImageVector
) {
    var textState by remember {
        mutableStateOf(TextFieldValue(""))
    }

    BasicTextField(
        value = textState,
        onValueChange = { textState = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp)
            .background(
                color = Color.Gray.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.small
            ),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                if (textState.text.isEmpty()) {
                    Text(
                        text = labelValue,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                innerTextField()
            }
        }
    )
}


@Composable
fun MyPasswordFieldComponent(
    labelValue: String,
    leadingIcon: ImageVector
) {
    var passwordState by remember {
        mutableStateOf(TextFieldValue(""))
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    BasicTextField(
        value = passwordState,
        onValueChange = { passwordState = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp)
            .background(
                color = Color.Gray.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.small
            ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                if (passwordState.text.isEmpty()) {
                    Text(
                        text = labelValue,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                innerTextField()
                Spacer(modifier = Modifier.width(80.dp))
                IconButton(onClick = {
                    passwordVisible.value = !passwordVisible.value
                }) {
                    Icon(
                        imageVector = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisible.value) "Hide password" else "Show password"
                    )
                }
            }
        }
    )
}



@Composable
fun ButtonComponenent(value: String, route: String, navController: NavController) {

    Button(
        onClick = { navController.navigate(route) },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.primary_container) // Use containerColor for background
        ),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .padding(16.dp)
    ) {
        Text(
            text = value,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
    }

}

@Composable
fun AlternativeOptionGoogle(
    onIconClick: (index: Int) -> Unit,
    value: String,
    //route: String,
    //navController: NavController,
    painter: Painter,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SmallTextComponent(value = value)
        
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painter,
                contentDescription = "google logo",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
            )
        }


    } 

}

@Composable
fun AlternativeOptionAccount(
    //onLinkClick: () -> Unit,
    value: String,
    linkText: String,
    route: String,
    navController: NavController,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = value, fontSize = 14.sp)
        Text(
            text = linkText,
            color = colorResource(id = R.color.text_color),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .clickable { navController.navigate(route) }
                .padding(start = 4.dp)
        )
    }
}

@Composable
fun ForgotPasswordLink(route: String, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Esqueceu sua senha?",
            fontSize = 14.sp,
            color = textColor,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { navController.navigate(route) }
        )
    }
}

@Composable
fun BottomNavigation(modifier: Modifier, navController: NavController) {
    NavigationBar(
        containerColor = colorResource(id = R.color.secondary_container),
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Bookmarks,
                    contentDescription = null
                )
            },
            label = null,
            selected = false,
            onClick = {
                navController.navigate(Screen.MyBooksScreen.route)
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.People,
                    contentDescription = null
                )
            },
            label = null,
            selected = false,
            onClick = {
                navController.navigate(Screen.CommunityScreen.route)
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = null,
            selected = true,
            onClick = {
                navController.navigate(Screen.MainScreen.route)
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            label = null,
            selected = false,
            onClick = {
                navController.navigate(Screen.SearchScreen.route)
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.PermIdentity,
                    contentDescription = null
                )
            },
            label = null,
            selected = false,
            onClick = {
                navController.navigate(Screen.ProfileScreen.route)
            }
        )

    }
}

@Composable
fun SearchBarComponent(
    modifier: Modifier
) {

    var searchBook by remember {
        mutableStateOf(TextFieldValue(""))
    }

    BasicTextField(
        value = searchBook,
        onValueChange = { searchBook = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp)
            .background(color = Color.Gray.copy(alpha = 0.2f), shape = MaterialTheme.shapes.small),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    Icons.Default.Search, 
                    contentDescription = "Search",
                    tint = colorResource(id = R.color.primary)
                )
                Spacer(modifier = Modifier.width(8.dp))
                if (searchBook.text.isEmpty()) {
                    Text(text = "Encontre livros, escritores...", color = Color.Gray)
                }
                innerTextField()
            }
        }

    )
}

@Composable
fun SectionName(value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {

        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )

    }
}

@Composable
fun BookCard(
    painter: Painter,
    onBookClick: () -> Unit,
    navController: NavController,
    modifier: Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 75.dp, height = 110.dp),
        onClick = { onBookClick() }
    ) {
        Image(painter = painter, contentDescription = "null", contentScale = ContentScale.Crop)
    }
}

@Composable
fun ScrollableBookRow(
    bookList: List<Int>,
    navController: NavController
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.padding(5.dp) // Add padding to the LazyRow if needed
    ) {
        items(bookList.size) { index ->
            BookCard(
                painter = painterResource(id = bookList[index]),
                onBookClick = {
                    // Navigate to the book screen
                    navController.navigate(Screen.BookScreen.route)
                },
                navController = navController,
                modifier = Modifier
                    .height(200.dp)
                    .padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun Dashboard(value: String, modifier: Modifier) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .shadow(8.dp, RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
            .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
            .background(color = colorResource(id = R.color.secondary_container)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            modifier = Modifier
                .padding(30.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            ),
            color = textColor,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun CategorySection(
    title: String,
    categories: List<String>,
    onCategoryClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        categories.forEachIndexed { index, category ->
            Text(
                text = category,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { onCategoryClick(category) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (index < categories.size - 1) {
                HorizontalDivider(
                    thickness = 2.dp,
                    color = colorResource(id = R.color.tertiary_container)
                )
            }
        }
    }
}

@Composable
fun MenorDashboard(value: String, navController: NavController, modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(8.dp, RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
            .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
            .background(color = colorResource(id = R.color.secondary_container)),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone alinhado à esquerda
            IconButton(onClick = { navController.popBackStack() }) {

                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Back",
                    tint = textColor, // Cor do ícone
                    modifier = Modifier
                        .padding(start = 16.dp) // Espaço à esquerda do ícone
                )

            }
            // Espaço flexível para centralizar o texto
            Spacer(modifier = Modifier.weight(1f))

            // Texto centralizado
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ),
                color = textColor,
                textAlign = TextAlign.Center
            )

            // Espaço flexível após o texto para balancear
            Spacer(modifier = Modifier.weight(1.3f))
        }
    }
}


@Composable
fun ScrollableBookColumn(
    bookList: List<Int>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        // Agrupamos os livros em linhas de 3
        items(bookList.chunked(4)) { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Iteramos sobre cada livro em uma linha de até 3 livros
                rowItems.forEach { bookResId ->
                    BookCard(
                        painter = painterResource(id = bookResId),
                        onBookClick = {
                            navController.navigate(Screen.BookScreen.route)
                        },
                        navController = navController,
                        modifier = Modifier
                            .weight(1f) // Ocupa o mesmo espaço para cada livro
                            .height(150.dp)
                    )
                }
                // Adiciona espaço vazio se a linha não tiver exatamente 3 itens
                repeat(4 - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}



@Composable
fun SectionNameMenor(value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp),
        horizontalArrangement = Arrangement.Start
    ) {

        Text(
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall
        )

    }
}