package com.aula.literatiapp.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.ButtonComponent
import com.aula.literatiapp.presentation.common.sharedComponents.TextField
import com.aula.literatiapp.presentation.screens.settings.viewModels.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun AltPasswordScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel
) {

    var senhaAtual by remember { mutableStateOf("") }
    var novaSenha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.edit_password), navController = navController)
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
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
            TextField(
                value = senhaAtual,
                onValueChange = { senhaAtual = it },
                placeholder = stringResource(id = R.string.senha_atual),
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordToggle = { passwordVisible = !passwordVisible },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = novaSenha,
                onValueChange = { novaSenha = it },
                placeholder = stringResource(id = R.string.senha_atual),
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordToggle = { passwordVisible = !passwordVisible },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = confirmarSenha,
                onValueChange = { confirmarSenha = it },
                placeholder = stringResource(id = R.string.senha_atual),
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordToggle = { passwordVisible = !passwordVisible },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))


            ButtonComponent(
                value = "Confirmar",
                onButtonClick = {
                    if (novaSenha == confirmarSenha) {
                        coroutineScope.launch {
                            settingsViewModel.updateUserPassword(
                                newPassword = novaSenha,
                                onComplete = { isSuccess ->
                                    if (isSuccess) {
                                        navController.navigate("settings_screen")
                                    } else {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Error updating passwords")
                                        }
                                    }
                                }
                            )
                        }
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Passwords do not match")
                        }
                    }
                },
                modifier = Modifier.wrapContentWidth()
            )

        }
    }
}
