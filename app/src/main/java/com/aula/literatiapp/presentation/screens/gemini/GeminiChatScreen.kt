package com.aula.literatiapp.presentation.screens.gemini

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.screens.gemini.components.ChatInput
import com.aula.literatiapp.presentation.screens.gemini.components.MessageList
import com.aula.literatiapp.presentation.screens.gemini.viewModels.GeminiChatViewModel

@Composable
fun GeminiChatScreen(modifier: Modifier = Modifier, navController: NavController, viewModel: GeminiChatViewModel) {

    Log.d("GeminiChatScreen", "GeminiChatScreen composable is called.")

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.chatGemini), navController = navController)
        },
        bottomBar = {
            ChatInput(
                onMessageSend = {
                    viewModel.callIA(it)
                },
                onClearChat = {
                    viewModel.clearChatHistoy()
                }
            )
        }

    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            MessageList(
                messages = viewModel.messages,
                modifier = Modifier
                    .weight(1f)
            )

        }

    }
}