package com.aula.literatiapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.components.MenorDashboard
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.CategorySection
import com.aula.literatiapp.navigation.Screen


@Composable
fun SettingsScreen(navController: NavController) {
    val settings = listOf(
        "Alterar email e senha",
        "Alterar nome de usuário",
        "Notificações",
        "Idiomas",
        "Acessibilidade",
        "Sair",
        "Excluir Conta"
    )

    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.config), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .align(Alignment.TopCenter)
            ) {

                CategorySection(
                    title = "",
                    categories = settings,
                    onCategoryClick = {selectedSetting ->
                        when (selectedSetting) {
                            "Alterar email e senha" -> {
                                navController.navigate(Screen.AltEmailSenhaScreen.route)
                            }
                            "Alternar nome de usuário" -> {
                                navController.navigate(Screen.AltUserScreen.route)
                            }
                            "Notificações" -> {
                                navController.navigate(Screen.EnableNotifScreen.route)
                            }
                            "Idiomas" -> {
                                navController.navigate(Screen.IdiomasScreen.route)
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
fun AltEmailSenhaScreen(navController: NavController) {

}

@Composable
fun AltUserScreen(navController: NavController) {

}

@Composable
fun EnableNotifScreen(navController: NavController) {

}

@Composable
fun IdiomasScreen(navController: NavController) {

}

@Composable
fun AcessibilityScreen(navController: NavController) {

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