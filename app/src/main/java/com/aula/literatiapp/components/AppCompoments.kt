package com.aula.literatiapp.components

import android.app.AlertDialog
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.DoNotDisturbAlt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.aula.literatiapp.R
import com.aula.literatiapp.model.Book
import com.aula.literatiapp.model.Community
import com.aula.literatiapp.model.Notification
import com.aula.literatiapp.model.Post
import com.aula.literatiapp.navigation.Screen
import com.aula.literatiapp.ui.theme.darkGreen
import com.aula.literatiapp.ui.theme.gradientBrush
import com.aula.literatiapp.ui.theme.lightGreen
import com.aula.literatiapp.ui.theme.secondaryContainer
import com.aula.literatiapp.ui.theme.tertiaryContainer
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
    value: String,
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
                Box(modifier = Modifier.weight(1f)) {
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
        }
    )
}

@Composable
fun CommunityTextField(
    labelValue: String
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
                Box(modifier = Modifier.weight(1f)) {
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
        }
    )
}

@Composable
fun MyDescriptionFieldComponent(
    labelValue: String
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
            .height(150.dp)
            .background(
                color = Color.Gray.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.small
            ),
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
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
        }
    )
}



@Composable
fun MyPasswordFieldComponent(
    value: String,
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
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(modifier = Modifier.weight(1f)) {
                    if (passwordState.text.isEmpty()) {
                        Text(
                            text = labelValue,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
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
fun ButtonComponent(value: String, route: String, navController: NavController, modifier: Modifier) {
    val gradientBrush = Brush.horizontalGradient(
        listOf(
            Color(0xFF5BDF92),
            Color(0xFF8BE95B)
        )
    )
    val textColor = Color.White

    Button(
        onClick = { navController.navigate(route) },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 65.dp, max = 70.dp)
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(18.dp),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrush)
                .clip(RoundedCornerShape(18.dp))
        ) {
            Text(
                text = value,
                color = textColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}


@Composable
fun AlternativeOptionGoogle(
    onIconClick: (index: Int) -> Unit,
    value: String,
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

    val gradientBrush = Brush.horizontalGradient(
        listOf(
            Color(0xFF5BDF92),
            Color(0xFF8BE95B)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(brush = gradientBrush)  // Set the gradient background here
    ) {
        NavigationBar(
            containerColor = Color.Transparent,  // Set containerColor to transparent
            modifier = Modifier.fillMaxSize()    // Fill the Box
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
                selected = false,
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
}


@Composable
fun SearchBarComponent(
    texto: String,
    modifier: Modifier
) {

    var search by remember {
        mutableStateOf(TextFieldValue(""))
    }

    BasicTextField(
        value = search,
        onValueChange = { search = it },
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
                    tint = lightGreen
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.weight(1f)) {
                    if (search.text.isEmpty()) {
                        Text(text = texto, color = Color.Gray)
                    }
                    innerTextField()
                }
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
        Image(
            painter = painter,
            contentDescription = "null",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun BiggerBookCard(
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
            .size(width = 110.dp, height = 150.dp),
        onClick = { onBookClick() }
    ) {
        Image(
            painter = painter,
            contentDescription = "null",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
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
fun MainDashboard(value: String, fontSize: TextUnit) {
    val gradientBrush = Brush.horizontalGradient(
        listOf(
            Color(0xFF5BDF92),
            Color(0xFF8BE95B)
        )
    )

    Column(
        Modifier
            .height(80.dp)
            .background(brush = gradientBrush),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout() {
            val (text) = createRefs()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .height(60.dp)
                        .padding(vertical = 14.dp)
                        .padding(start = 8.dp)
                        .weight(0.7f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = value,
                        color = Color.White,
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun BackNavigationDashboard(value: String, navController: NavController) {
    val gradientBrush = Brush.horizontalGradient(
        listOf(
            Color(0xFF5BDF92),
            Color(0xFF8BE95B)
        )
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
            val (icon, text) = createRefs()

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(50.dp)
                    .constrainAs(icon) {
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

            // Texto centralizado no meio da tela
            Text(
                text = value,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(text) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookDashboard(navController: NavController) {
    var showBottomSheet by remember { mutableStateOf(false) }
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
                    onCategoryClick = {}
                )
                
            }
        }
    }
}


@Composable
fun CategorySection(
    title: String,
    categories: List<String>,
    onCategoryClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        categories.forEachIndexed { index, category ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategoryClick(category) }
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = category,
                    fontSize = 15.sp,
                    color = if (category == "Excluir Conta") colorResource(id = R.color.error_text_color) else colorResource(
                        id = R.color.text_color
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (index < categories.size - 1) {
                HorizontalDivider()
            }
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


@Composable
fun CommunityCard(comunidade: Community, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
            .height(200.dp)
    ) {
        // Box para a imagem como banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Faz a imagem ocupar a maior parte do espaço disponível
                .clip(RoundedCornerShape(16.dp))
        ) {
            val imageUri = comunidade.imagemUri

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUri)
                    .crossfade(true) // Optional: Adds a crossfade effect
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )


            // Nome e Descrição da Comunidade sobre a imagem
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart) // Alinha a coluna no fundo da caixa
            ) {
                Text(
                    text = comunidade.nome,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White // Mantém a cor branca para o texto
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = comunidade.descricao,
                    fontSize = 12.sp,
                    color = Color.White // Mantém a cor branca para o texto
                )
            }

        }
    }
}


@Composable
fun ScrollableCommunityColumn(
    communityList: List<Community>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        // Exibe cada comunidade na lista
        items(communityList) { comunidade ->
            CommunityCard(
                comunidade = comunidade,
                onClick = {
                    // Navega para a tela da comunidade específica
                    navController.navigate(Screen.CommunityList.route)
                }
            )
        }
    }
}

@Composable
fun CommunityImageSelection() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagem_padrao),
            contentDescription = "Imagem padrão da comunidade",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun ChallengeComponent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalDivider()

        // Conteúdo do ChallengeComponent
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.MetasScreen.route) }
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.metas),
                    fontWeight = FontWeight.Normal
                )

                IconButton(onClick = {navController.navigate(Screen.MetasScreen.route)}) {
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "right arrow")
                }
            }
        }

        HorizontalDivider()
    }
}


@Composable
fun EstadoMetas(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Nenhum livro lido ainda!",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = "Adicione uma meta para começar a acompanhar sua leitura.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )

        }
    }
}


@Composable
fun CustomProgressBar(
    modifier: Modifier,
    width: Dp,
    backgroundColor: Color,
    foregroundColor: Brush,
    percent: Int,
    isShownText: Boolean
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .width(width)
    ) {
        Box(
            modifier = modifier
                .background(foregroundColor)
                .width(width * percent / 100)
        )

        if (isShownText) {
            Text(
                text = "$percent %",
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun BookInfo(book: Book, navController: NavController) {

    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BiggerBookCard(
            painter = painterResource(id = R.drawable.thehandmaidstale),
            onBookClick = {  },
            navController = navController,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Textos do livro
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = book.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = book.authors?.joinToString(", ") ?: "Unknown Authors",
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )

            Text(
                text = book.description ?: "No Description Available",
                maxLines = if (isExpanded) Int.MAX_VALUE else 5,
                overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            if (!isExpanded) {
                Text(
                    text = " leia mais",
                    color = darkGreen,
                    modifier = Modifier.clickable { isExpanded = true } // Expande o texto ao clicar
                )
            }



        }
    }
}

@Composable
fun AvaliacaoComponent(navController: NavController) {
    var rating by remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        HorizontalDivider()
        Text(
            text = stringResource(id = R.string.avaliacoes),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            StarRatingBar(
                maxStars = 5,
                rating = rating,
                onRatingChanged = {
                    rating = it
                }
            )
        }

        Text(
            text = stringResource(id = R.string.av),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.ExtraLight,
            modifier = Modifier.padding(top = 8.dp)
        )

        HorizontalDivider()
    }
}

@Composable
fun BookScreenWithTabs(book: Book) {
    var selectedTab by remember { mutableStateOf("Detalhes") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Barra de navegação com tabs
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            TabButton(
                title = "Detalhes",
                selected = selectedTab == "Detalhes",
                onClick = { selectedTab = "Detalhes" }
            )
            TabButton(
                title = "Gêneros",
                selected = selectedTab == "Gêneros",
                onClick = { selectedTab = "Gêneros" }
            )
        }


        AnimatedContent(
            targetState = selectedTab,
            transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = Up
                ).togetherWith(
                    slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = Down
                    )
                )
            }
        ) { tab ->
            when (tab) {
                "Detalhes" -> BookDetailsContent(book = book)
                "Gêneros" -> BookGenresContent(book = book)
            }
        }
    }
}

@Composable
fun TabButton(title: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor = Color.White
    val borderColor = if (selected) Color.Black else Color.LightGray

    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BookDetailsContent(book: Book) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Publicadores: " + book.publisher,
            fontSize = 12.sp,
            color = textColor,
            modifier = Modifier.padding(4.dp)
        )
        HorizontalDivider()
        Text(
            text = "Volume: " + book.volumeInfo,
            fontSize = 12.sp,
            color = textColor,
            modifier = Modifier.padding(4.dp)
        )
        HorizontalDivider()
        Text(
            text = "Quantidade de páginas: " + book.pages,
            fontSize = 12.sp,
            color = textColor,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun BookGenresContent(book: Book) {
    // Lista de botões representando gêneros
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento vertical entre linhas
    ) {
        val genres = book.genres

        if (genres != null) {
            genres.chunked(3).forEach { rowGenres ->  // Agrupa 3 gêneros por linha
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento horizontal entre botões
                ) {
                    rowGenres.forEach { genre ->
                        Button(
                            onClick = { /* Ação ao clicar */ },
                            colors = ButtonDefaults.buttonColors(
                                Color.Transparent
                            ),
                            border = BorderStroke(0.5.dp, lightGreen),
                        ) {
                            Text(
                                text = genre,
                                color = textColor,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    //starSize: Dp = 12.dp
) {
    val density = LocalDensity.current.density
    val starSpacing = (0.5f * density).dp
    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Default.Star
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color(0x20FFFFFF)

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onRatingChanged(i.toFloat())
                        }
                    )
                    .size(15.dp)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }

        }

    }
}

// adicionar component para a review
// TODO: Deixar esse Box clicável
@Composable
fun ReviewComponent(book: Book, navController: NavController) {

    var rating by remember { mutableStateOf(4f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                BookCard(
                    painter = painterResource(id = R.drawable.thehandmaidstale),
                    onBookClick = { navController.navigate(Screen.BookScreen.route) },
                    navController = navController,
                    modifier = Modifier
                )
            }

            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = book.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        color = textColor
                    )

                    StarRatingBar(
                        maxStars = 5,
                        rating = rating,
                        onRatingChanged = {newRating ->
                            rating = newRating
                        }
                    )


                }

                Text(
                    text = book.authors?.joinToString(", ") ?: "Unknown Authors",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = book.review,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    color = textColor
                )

            }
        }
    }
}

@Composable
fun EspCommunityCard(
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
        Image(
            painter = painter,
            contentDescription = "null",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}

// TODO: Depois adicionar expansão a descrição da comunidade (ler mais)
@Composable
fun ListOfCommunitiesComponent(community: Community, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { navController.navigate(Screen.EspCommunityScreen.route) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                EspCommunityCard(
                    painter = rememberAsyncImagePainter(model = community.imagemUri),
                    onBookClick = { navController.navigate(Screen.EspCommunityScreen.route) },
                    navController = navController,
                    modifier = Modifier
                )
            }

            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Text(
                    text = community.nome,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    color = textColor
                )

                Text(
                    text = community.descricao,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    color = textColor
                )

            }
        }
    }
}

@Composable
fun ProfileImageComponent(
    painter: Painter,
    onProfileClick: () -> Unit,
    navController: NavController,
    modifier: Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .size(35.dp)
            .clip(CircleShape),
        onClick = { onProfileClick() }
    ) {
        Image(
            painter = painter,
            contentDescription = "null",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
    }
}

@Composable
fun NotificationComponent(notifInfo: Notification, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            ProfileImageComponent(
                painter = rememberAsyncImagePainter(model = notifInfo.userImageUrl),
                onProfileClick = { },
                navController = navController,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = notifInfo.name + notifInfo.action + notifInfo.nomeLivroOuComunidade,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun MyBooksBoxComponent(book: Book, navController: NavController) {

    Card(
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.surface)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Go to all the books",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.MyBooksList.route)
                        }
                )

            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                repeat(3) {
                    BookCard(
                        painter = rememberAsyncImagePainter(model = book.imageUrl),
                        onBookClick = { navController.navigate(Screen.BookScreen.route) },
                        navController = navController,
                        modifier = Modifier
                    )
                }

            }
        }
    }

}


@Composable
fun ChallengeBoxComponent(modifier: Modifier, meta: String) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.surface)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Primeiro Row
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.desafio),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            // Espaçamento entre o título e o conteúdo
            Spacer(modifier = Modifier.height(16.dp))

            // Segundo Row
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp), // Espaçamento entre a imagem e a coluna
                verticalAlignment = Alignment.CenterVertically // Alinha os elementos ao centro verticalmente
            ) {

                // Imagem de perfil
                ProfileImageComponent(
                    painter = rememberAsyncImagePainter(model = "https://i.pinimg.com/enabled_hi/564x/35/d8/3d/35d83d2e796d5ae4558396ba4adf2cc8.jpg"),
                    onProfileClick = { },
                    navController = rememberNavController(),
                    modifier = Modifier.alignBy { it.measuredHeight } // Alinha pela altura da segunda coluna
                )

                // Texto e Barra de Progresso
                Column(
                    modifier = Modifier.alignBy { it.measuredHeight } // Alinha pela altura da imagem
                ) {
                    Text(
                        text = meta,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(8.dp)
                    )

                    CustomProgressBar(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(20.dp),
                        width = 200.dp,
                        backgroundColor = Color.LightGray,
                        foregroundColor = Brush.horizontalGradient(
                            listOf(
                                Color(0xF0288D21),
                                Color(0xF055CA4D)
                            )
                        ),
                        percent = 0,
                        isShownText = true
                    )
                }
            }
        }
    }
}

@Composable
fun AlertDialogComponent(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogInput: String,
    onValueChange: (String) -> Unit
) {

    var inputValue by remember {
        mutableStateOf(dialogInput)
    }

    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Default.Book, contentDescription = "book icon")
        },
        title = {
            Text(
                text = stringResource(id = R.string.dialogTitle),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column {
                Spacer(modifier = Modifier.height(5.dp))
                TextField(
                    value = inputValue,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }) {
                            inputValue = newValue
                            onValueChange(newValue)
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.digitemeta)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTrailingIconColor = lightGreen
                    )
                )
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.confirm),
                    color = lightGreen
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissRequest()
            }) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = lightGreen
                )
            }
        },
    )

}

@Composable
fun EnableNotificationBox(
    value: String
) {

    Column {

        HorizontalDivider()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = value,
                    fontSize = 15.sp
                )

                Switch()
            }
        }

        HorizontalDivider()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown() {
    val context = LocalContext.current
    val tamanho = arrayOf(
        "10 px",
        "14 px",
        "16 px",
        "20 px"
    )
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedText by remember {
        mutableStateOf(tamanho[0])
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ExposedDropdownMenuBox(
            expanded = true,
            onExpandedChange = {
                expanded = !expanded
            }
        ){
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .width(110.dp)
                    .height(50.dp),
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)}
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false},
                modifier = Modifier.width(150.dp)
            ) {
                tamanho.forEachIndexed{ index, text ->
                    DropdownMenuItem(
                        text = {
                            Text(text = text)
                        },
                        onClick = {
                            selectedText = tamanho[index]
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
        //  Text(text = "Selecionado: $selectedText")
    }
}

@Composable
fun Switch() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        modifier = Modifier.scale(0.8f),
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = darkGreen,
            checkedTrackColor = tertiaryContainer,
            uncheckedThumbColor = lightGreen,
            uncheckedTrackColor = secondaryContainer
        )
    )

}

@Composable
fun CategorySectionAccessibility(
    categories: List<String>
    //onCategoryClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        categories.forEachIndexed { index, category ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = category,
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier.weight(1f) // Occupies available space
                    )

                    when (category) {
                        "Tema" -> {
                            Switch()
                        }
                        "Tamanho da fonte" -> {
                            DropDown()
                        }
                        "Contraste" -> {
                            Switch()
                        }
                    }
                }
            }


            if (index < categories.size - 1) {
                Spacer(modifier = Modifier.height(2.dp))
                HorizontalDivider()
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun CommunityPost(post: Post, isMember: Boolean) {
    var likes by remember { mutableStateOf(post.likes) }
    var isLiked by remember { mutableStateOf(post.isLiked) }
    var commentText by remember { mutableStateOf("") }
    val comments = remember { post.comments }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.surface)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Exibe o avatar e o conteúdo do post
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = post.avatarUrl,
                    contentDescription = "Avatar de ${post.author}",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = post.author, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(5.dp)) // Espaço entre o nome e a postagem
                    Text(text = post.content)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Ícones de Curtir e Comentar (para membros)
            if (isMember) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                isLiked = !isLiked
                                if (isLiked) likes++ else likes--
                            }
                        ) {
                            Icon(
                                imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Curtir"
                            )
                        }
                        Text(text = "$likes curtidas")
                    }

                    IconButton(onClick = { /* Lógica para comentar */ }) {
                        Icon(
                            imageVector = Icons.Default.Comment,
                            contentDescription = "Comentar"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Exibe os Comentários
                comments.forEach { comment ->
                    Text(text = comment, modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}

@Composable
fun ScrollablePostList(
    postList: List<Post>,
    isMember: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        // Exibe cada post na lista
        items(postList) { post ->
            CommunityPost(
                post = post,
                isMember = isMember,
            )
        }
    }
}

