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
import androidx.compose.material.icons.filled.Mail
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
fun AltUserScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel
) {

    var newUsername by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = stringResource(id = R.string.edit_username),
                navController = navController
            )
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
                value = newUsername,
                onValueChange = { newUsername = it },
                placeholder = stringResource(id = R.string.new_username),
                leadingIcon = Icons.Default.Mail,
                isPassword = false,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonComponent(
                value = "Confirmar",
                onButtonClick = {
                    coroutineScope.launch {
                        settingsViewModel.updateUserName(
                            newName = newUsername,
                            onComplete = { isSuccess ->
                                if (isSuccess) {
                                    navController.navigate("settings_screen")
                                } else {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Failed to update username")
                                    }
                                }
                            }
                        )
                    }
                },
                modifier = Modifier.wrapContentWidth()
            )
        }

    }
}
