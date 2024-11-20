package com.aula.literatiapp.presentation.screens.gemini

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.screens.gemini.components.ChatInput
import com.aula.literatiapp.presentation.screens.gemini.viewModels.GeminiChatViewModel

@Composable
fun GeminiChatScreen(modifier: Modifier = Modifier, navController: NavController, viewModel: GeminiChatViewModel) {
    Scaffold(

        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.chatGemini), navController = navController)
        },
        bottomBar = {
            ChatInput(
                onMessageSend = {
                    viewModel.callIA(it)
                }
            )
        }

    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .imePadding()
        ) {

            Text(
                text = viewModel.resposta.value,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }

    }
}