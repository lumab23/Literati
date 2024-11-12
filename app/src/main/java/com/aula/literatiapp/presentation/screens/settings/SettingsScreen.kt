package com.aula.literatiapp.presentation.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.User
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection
import com.aula.literatiapp.presentation.screens.settings.components.EditProfileCard
import com.aula.literatiapp.presentation.screens.settings.viewModels.SettingsViewModel
import com.aula.literatiapp.presentation.ui.theme.getTextColor
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight

@Composable
fun SettingsScreen(navController: NavController) {

    val settingsViewModel: SettingsViewModel = viewModel()

    LaunchedEffect(Unit) {
        settingsViewModel.fetchUserData()
    }

    val userName = settingsViewModel.userName
    val userEmail = settingsViewModel.userEmail
    val userProfilePictureUrl = settingsViewModel.userProfilePictureUrl

    val user = User(
        id = "1",
        name = "Luma",
        username = "@mrdarcy",
        email = "",
        profilePictureUrl = "https://example.com/profile.jpg"
    )

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
            BackNavigationDashboard(value = stringResource(id = R.string.settings), navController = navController)
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


                EditProfileCard(
                    navController = navController,
                    user = User(
                        id = "1",
                        name = userName,
                        username = "@$userName",
                        email = userEmail,
                        profilePictureUrl = userProfilePictureUrl
                    ),
                    gradientBrush = gradientBrushLight,
                    textColor = getTextColor()
                )

                CategorySection(
                    title = "",
                    categories = settings,
                    onCategoryClick = {selectedSetting ->
                        when (selectedSetting) {
                            "Alterar email" -> {
                                navController.navigate("alterar_email")
                            }
                            "Alterar senha" -> {
                                navController.navigate("alterar_senha")
                            }
                            "Alterar nome de usuário" -> {
                                navController.navigate("alterar_username")
                            }
                            "Notificações" -> {
                                navController.navigate("enable_notif")
                            }
                            "Acessibilidade" -> {
                                navController.navigate("acessibility_screen")
                            }
                            // depois adicionar um pop up para confirmação
                            "Sair" -> {
                                settingsViewModel.signOut()
                                navController.navigate("login_screen")
                            }
                            // depois alterar para aparecer um pop up
                            "Excluir conta" -> {
                                settingsViewModel.deleteAccount()
                                navController.navigate("signup_screen")
                            }
                        }

                    }
                )

            }
        }

    }
}