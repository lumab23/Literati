package com.aula.literatiapp.components

import android.media.Rating
import android.net.Uri
import androidx.compose.animation.animateContentSize
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.DoNotDisturbAlt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aula.literatiapp.R
import com.aula.literatiapp.model.Book
import com.aula.literatiapp.model.Community
import com.aula.literatiapp.model.Notification
import com.aula.literatiapp.navigation.Screen
import com.aula.literatiapp.ui.theme.darkGreen
import com.aula.literatiapp.ui.theme.gradientBrush
import com.aula.literatiapp.ui.theme.lightGreen
import com.aula.literatiapp.ui.theme.secondaryContainer
import com.aula.literatiapp.ui.theme.surfaceDim
import com.aula.literatiapp.ui.theme.tertiaryContainer
import com.aula.literatiapp.ui.theme.textColor
import kotlinx.coroutines.selects.select

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
fun ButtonComponent(value: String, route: String, navController: NavController) {
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

@Composable
fun MyBookDashboard(navController: NavController) {
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
                onClick = {  },
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
fun EnableNotificationBox(
    value: String
) {

    var checked by remember { mutableStateOf(true) }

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
        }

        HorizontalDivider()

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
                    navController.navigate(Screen.EspCommunityScreen.route)
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
fun UserListItem(notification: Notification){
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .animateContentSize()
            .fillMaxWidth()
            .padding(3.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                expanded = !expanded
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        colors = CardDefaults.cardColors(
            //containerColor = Color.Gray.copy(alpha = 0.2f), //fundo do card
            //contentColor = Color.White
        )
    ){
        Row(Modifier.padding(4.dp)) {
            UserImage(notification = notification)
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ){
                Text(text = notification.name)
                if(expanded){
                    Row(modifier = Modifier.padding(horizontal = 2.dp, vertical = 6.dp))  {
                        Text(
                            text = "Curtiu sua resenha"
                        )
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "liked",
                            tint = Color.Red,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(text = "Ver detalhes",
                            style = TextStyle(
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    }
                }else{
                    Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(vertical = 6.dp)) {
                        Image(
                            painter = painterResource(R.drawable._984),
                            contentDescription = "Livro 1984",
                            Modifier.size(82.dp)
                        )
                        Column (modifier = Modifier.padding(vertical = 5.dp)){
                            Text(
                                text = "\"Esse livro abriu minha mente! Amei muito\""
                            )
                            Row {
                                Text(
                                    text = "Curtiu sua resenha",
                                    modifier = Modifier.padding(horizontal = 2.dp),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "liked",
                                    tint = Color.Red,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun UserImage(notification: Notification){
    Image(
        painter = painterResource(R.drawable.profile_picture),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(4.dp)
            .size(50.dp)
            .clip(RoundedCornerShape(corner = CornerSize(50.dp)))
    )
}

@Composable
fun ChallengeComponent(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(Screen.MetasScreen.route) }
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RectangleShape
            )
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
                fontSize = 16.sp
            )

            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "right arrow")
            }

        }
    }

}

@Composable
fun NoChallenges(navController: NavController){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(22.dp),
        contentAlignment = Alignment.Center
    ){
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = Icons.Default.DoNotDisturbAlt,
                contentDescription = "without challenges",
                modifier = Modifier
                    .size(80.dp))
            Text(
                text = "Sem metas por agora",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun IconsBookshelv(){
    var clicked by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .background(color = colorResource(R.color.secondary_container))
            .height(50.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = Icons.Filled.Bookmarks,
                contentDescription = "saved books",
                //implementar o clickable, quando clicar na bookshelv já acessa os livros salvos, ai o icon fica marcado
                //sinalizando que está na seção salvo
            )
            Icon(
                imageVector = Icons.Filled.AttachMoney,
                contentDescription = "wanna buy books"
            )
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "favorite books"
            )
            Icon(
                imageVector = Icons.Filled.CurrencyExchange,
                contentDescription = "changed books"
            )
        }
    }
}

@Composable
fun ChallengeBox(modifier: Modifier){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.surface)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.desafio),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(14.dp)
            )

            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(15.dp))

                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                        //.padding(13.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("0/0", modifier = Modifier
                        .padding(8.dp))
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
                        isShownText = true)
                }

            }
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

enum class BookTabs(
    val selectedTab: String,
    val unselectedTab: String,
    val text: String
) {

}

@Composable
fun BookDetailsComponent(

) {

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


@Composable
fun DropDownDemo() {
    val isDropDownExpanded = remember {
        mutableStateOf(true)
    }
    val itemPosition = remember {
        mutableStateOf(0)
    }
    val op = listOf(
        "Quero ler",
        "Abandonei",
        "Já li",
        "Troco"
    )
    Column(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.primary_container))
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    isDropDownExpanded.value = true
                }
            ) {
                Text(text = op[itemPosition.value],
                    modifier = Modifier
                        .padding(10.dp))
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "drop down"
                )
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                op.forEachIndexed { index, op ->
                    DropdownMenuItem(text = {
                        Text(text = op)
                    },
                        onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.value = index
                        })
                }
            }
        }

    }
}


