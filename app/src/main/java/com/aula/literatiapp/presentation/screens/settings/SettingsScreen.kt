package com.aula.literatiapp.presentation.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(navController: NavController) {
    val settingsViewModel: SettingsViewModel = viewModel()
    val snackbarHostState = remember { SnackbarHostState() }
    var showDeleteDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        settingsViewModel.fetchUserData()
    }

    val userName = settingsViewModel.userName
    val userEmail = settingsViewModel.userEmail
    val userProfilePictureUrl = settingsViewModel.userProfilePictureUrl

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
            BackNavigationDashboard(
                value = stringResource(id = R.string.settings),
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
                        name = userName.toString(),
                        username = "@$userName",
                        email = userEmail.toString(),
                        profilePictureUrl = userProfilePictureUrl.toString()
                    ),
                    gradientBrush = gradientBrushLight,
                    textColor = getTextColor()
                )

                CategorySection(
                    title = "",
                    categories = settings,
                    onCategoryClick = { selectedSetting ->
                        when (selectedSetting) {
                            "Alterar email" -> {
                                navController.navigate("updateEmail_screen")
                            }
                            "Alterar senha" -> {
                                navController.navigate("updatePassword_screen")
                            }
                            "Alterar nome de usuário" -> {
                                navController.navigate("updateUser_screen")
                            }
                            "Notificações" -> {
                                navController.navigate("enableNotifications_screen")
                            }
                            "Acessibilidade" -> {
                                navController.navigate("accessibility_screen")
                            }
                            "Sair" -> {
                                settingsViewModel.signOut()
                                navController.navigate("login") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                            "Excluir Conta" -> {
                                showDeleteDialog = true
                            }
                        }
                    }
                )
            }
        }


        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text(text = "Excluir Conta") },
                text = { Text(text = "Tem certeza que deseja excluir sua conta? Esta ação não pode ser desfeita.") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDeleteDialog = false
                            settingsViewModel.deleteAccount(
                                password = "",
                                onComplete = { success, errorMessage ->
                                    if (success) {
                                        navController.navigate("signUp") {
                                            popUpTo("signUp") { inclusive = true }
                                        }
                                    } else {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = errorMessage ?: "Erro ao excluir a conta"
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    ) {
                        Text(text = "Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text(text = "Cancelar")
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(navController = NavController(LocalContext.current))
}