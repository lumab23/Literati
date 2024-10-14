package com.aula.literatiapp.components

import android.view.Surface
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.defaultDecayAnimationSpec
import com.aula.literatiapp.model.Notification
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.R
import com.aula.literatiapp.R.drawable
import com.aula.literatiapp.model.NotificationDataProvider
import com.aula.literatiapp.navigation.Screen
import com.aula.literatiapp.screens.Bookshelv
import com.aula.literatiapp.ui.theme.textColor
import kotlinx.coroutines.delay

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
fun DashboardWithoutBack(value: String, navController: NavController, modifier: Modifier){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            //.shadow(8.dp, RoundedCornerShape(bottomStart = 35.dp, bottomEnd = 35.dp))
            //.clip(RoundedCornerShape(bottomStart = 35.dp, bottomEnd = 35.dp))
            .background(color = colorResource(id = R.color.tertiary_container)),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            //verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                ),
                color = textColor,
                textAlign = TextAlign.Center
            )
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

@Composable
fun UserListItem(notification: Notification){
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
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
                            painter = painterResource(drawable._984),
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
fun NotificationContent(){
    val notifications = remember { NotificationDataProvider.notificationList }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 4.dp)
    ) {
        items(
            items = notifications,
            itemContent = {
                UserListItem(notification = it)
            }
        )
    }


}

@Composable
fun UserImage(notification: Notification){
    Image(
        painter = painterResource(drawable.profile_picture),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(4.dp)
            .size(50.dp)
            .clip(RoundedCornerShape(corner = CornerSize(50.dp)))
    )
}

@Composable
fun ChallengeDashboard(navController: NavController, modifier: Modifier) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, // Garante que o texto e o ícone fiquem separados
            verticalAlignment = Alignment.CenterVertically, // Centraliza verticalmente os itens
            modifier = Modifier.fillMaxWidth()
        ) {
            // Coluna com o texto centralizado
            Column(
                modifier = Modifier.weight(1f), // Dá peso à coluna para centralizar o texto
                horizontalAlignment = Alignment.CenterHorizontally // Centraliza o texto na coluna
            ) {
                HorizontalDivider(thickness = 2.dp)
                Text(
                    text = "Metas",
                    modifier = Modifier.padding(30.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal
                    ),
                    color = textColor,
                    textAlign = TextAlign.Center
                )
                HorizontalDivider(thickness = 2.dp)
            }

            // Ícone à direita
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "See more",
                    modifier = Modifier.size(18.dp),
                    tint = textColor // Cor do ícone
                )
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
                imageVector = Icons.Filled.Save,
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .padding(16.dp)
            .shadow(10.dp, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp, topStart = 20.dp, topEnd = 20.dp))
            .background(color = colorResource(R.color.primary_container)),

    ){
        Column (modifier = Modifier
            .width(350.dp),
                //horizontalAlignment = Alignment.CenterHorizontally,
            ){
            Text(text = "DESAFIO",
                ///Color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(14.dp))

            Row {
                Image(
                    painter = painterResource(R.drawable.profile_picture),
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .size(50.dp)
                       )

                Column {
                    Text("0/0", modifier = Modifier
                        .padding(8.dp))
                    CustomProgressBar(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(20.dp),
                        width = 200.dp,
                        backgroundColor = Color.Gray,
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
fun BookScreenDashboard(navController: NavController, modifier: Modifier){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        )
    {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            Icon(imageVector = Icons.Default.ChevronLeft,
                contentDescription = "back",
                modifier = Modifier
                .size(38.dp))
            Row() {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "add information",
                    modifier = Modifier
                        .size(30.dp))
                Icon(imageVector = Icons.Default.Share,
                    contentDescription = "share",
                    modifier = Modifier
                        .size(30.dp))
            }
        }
    }
}

@Composable
fun BooksInformation(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        contentAlignment = Alignment.Center){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Image(painter = painterResource(drawable.itstartswithus),
                contentDescription = "book: it ends with us",
                modifier = Modifier
                    .size(250.dp)

                    .clip(RoundedCornerShape(12.dp))
            )
            Box(){
                Column {
                    Row(modifier = Modifier
                        .padding(22.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text(text = "It Starts With Us",
                                fontWeight = FontWeight.Bold)

                            Text(text = "Collen Hoover")
                            Text(text = "2023")
                        }
                        DropDownDemo()
                    }
                    Text(text = "O que os dois sentem não é amor à primeira vista, mas uma atração incontrolável. Em pouco tempo não conseguem mais resistir e se entregam ao desejo. Miles, no entanto, se recusa a abaixar as barreiras que construiu em volta de si mesmo e impõe duas regras: sem perguntas sobre o passado e sem esperanças para o futuro")
                }
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

@Preview(showBackground = true)
@Composable
fun DropDownDemoPreview() {
    BooksInformation()
}





