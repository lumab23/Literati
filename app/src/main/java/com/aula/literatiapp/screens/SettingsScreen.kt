package com.aula.literatiapp.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BackNavigationDashboard
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.ButtonComponent
import com.aula.literatiapp.components.CategorySection
import com.aula.literatiapp.components.CategorySectionAccessibility
import com.aula.literatiapp.components.EnableNotificationBox
import com.aula.literatiapp.components.MyPasswordFieldComponent
import com.aula.literatiapp.components.MyTextFieldComponent
import com.aula.literatiapp.navigation.Screen
import com.aula.literatiapp.ui.theme.gradientBrush
import com.aula.literatiapp.ui.theme.textColor


@Composable
fun SettingsScreen(navController: NavController) {
    val settings = listOf(
        "Alterar email",
        "Alterar senha",
        "Alterar nome de usuário",
        "Notificações",
        "Acessibilidade",
        "Sair",
        "Excluir Conta"
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Configurações", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.TopCenter)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                EditProfileCard(navController = navController)

                CategorySection(
                    title = "",
                    categories = settings,
                    onCategoryClick = {selectedSetting ->
                        when (selectedSetting) {
                            "Alterar email" -> {
                                navController.navigate(Screen.AltEmailScreen.route)
                            }
                            "Alterar senha" -> {
                                navController.navigate(Screen.AltSenhaScreen.route)
                            }
                            "Alterar nome de usuário" -> {
                                navController.navigate(Screen.AltUserScreen.route)
                            }
                            "Notificações" -> {
                                navController.navigate(Screen.EnableNotifScreen.route)
                            }
                            "Acessibilidade" -> {
                                navController.navigate(Screen.AcessibilityScreen.route)
                            }
                            // depois adicionar um pop up para confirmação
                            "Sair" -> {
                                navController.navigate(Screen.LoginScreen.route)
                            }
                            // depois alterar para aparecer um pop up
                            "Excluir conta" -> {
                                navController.navigate(Screen.SignUpScreen.route)
                            }
                        }

                    }
                )

            }
        }

    }
}

@Composable
fun EditProfileCard(navController: NavController) {

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
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(13.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Amanda",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                //Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "@mrdarcy",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .width(130.dp)
                        .height(40.dp),
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
                            text = "Edite Foto",
                            color = textColor,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AltEmailScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var senhaAtual by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Editar Email", navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            MyTextFieldComponent(value = email,labelValue = "Novo Email", leadingIcon = Icons.Default.Mail)
            Spacer(modifier = Modifier.height(16.dp))
            MyPasswordFieldComponent(value = senhaAtual, labelValue = "Senha Atual", leadingIcon = Icons.Default.Lock)
            Spacer(modifier = Modifier.height(16.dp))


            ButtonComponent(value = "Confirmar", route = Screen.SettingsScreen.route, navController = navController, modifier = Modifier)

        }
    }
}

@Composable
fun AltSenhaScreen(navController: NavController) {

    var senhaAtual by remember { mutableStateOf("") }
    val novaSenha by remember { mutableStateOf("") }
    val confirmarSenha by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Editar Email", navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            MyPasswordFieldComponent(value = senhaAtual, labelValue = "Senha Atual", leadingIcon = Icons.Default.Lock)
            Spacer(modifier = Modifier.height(16.dp))
            MyPasswordFieldComponent(value = novaSenha, labelValue = "Nova Senha", leadingIcon = Icons.Default.Lock)
            Spacer(modifier = Modifier.height(16.dp))
            MyPasswordFieldComponent(value = confirmarSenha, labelValue = "Confirmar Senha", leadingIcon = Icons.Default.Lock)
            Spacer(modifier = Modifier.height(16.dp))


            ButtonComponent(value = "Confirmar", route = Screen.SettingsScreen.route, navController = navController, modifier = Modifier)

        }
    }
}

@Composable
fun AltUserScreen(navController: NavController) {

    var newUsername by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Editar usuário", navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            MyTextFieldComponent(value = newUsername,labelValue = "Novo nome de usuário", leadingIcon = Icons.Default.Person)
            Spacer(modifier = Modifier.height(16.dp))

            ButtonComponent(value = "Confirmar", route = Screen.SettingsScreen.route, navController = navController, modifier = Modifier)

        }
    }

}

@Composable
fun EnableNotifScreen(navController: NavController) {

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Notificação", navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) {paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.TopCenter)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                EnableNotificationBox(value = "Ativar notificações")
            }
        }

    }

}

@Composable
fun AcessibilityScreen(navController: NavController) {

    var expanded by remember { mutableStateOf(false) }
    val fontSizes = listOf(12, 14, 16, 18, 20, 22, 24, 26, 28, 30)
    val op = listOf(
        "Contraste",
        "Tamanho da fonte",
        "Tema"
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Acessibilidade", navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(paddingValues)
        ){
            CategorySectionAccessibility(categories = op)
        }
    }
}

@Preview(
    showSystemUi = true,
    name = "SignUp Screen"
)
@Composable
fun SettingsScreenPreview() {
    val navController = rememberNavController()
    SettingsScreen(navController = navController)
}