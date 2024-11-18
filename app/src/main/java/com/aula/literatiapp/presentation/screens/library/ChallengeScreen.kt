package com.aula.literatiapp.presentation.screens.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.screens.library.components.AlertDialogComponent
import com.aula.literatiapp.presentation.screens.library.components.ChallengeBox
import com.aula.literatiapp.presentation.screens.library.components.ChallengeState
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight

@Composable
fun ChallengeScreen(navController: NavController) {

    var showDialog by remember { mutableStateOf(false) }
    var meta by remember { mutableStateOf("0/0") }
    var dialogInput by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            BackNavigationDashboard(
                value = stringResource(R.string.challenges),
                navController = navController
            )
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Conteúdo rolável
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    ChallengeBox(modifier = Modifier, meta = meta)
                    Spacer(modifier = Modifier.height(180.dp))
                    ChallengeState()
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }

            // Botão fixado no canto inferior direito
            FloatingActionButton(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Fixado no canto inferior direito
                    .padding(16.dp)
                    .width(140.dp)
                    .height(60.dp),
                containerColor = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = gradientBrushLight)
                ) {
                    Text(
                        text = "Adicionar meta",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        if (showDialog) {
            AlertDialogComponent(
                onDismissRequest = { showDialog = false },
                onConfirmation = {
                    showDialog = false
                    if (dialogInput.isNotBlank()) {
                        meta = "0/${dialogInput}"
                    }
                },
                dialogInput = dialogInput,
                onValueChange = { dialogInput = it }
            )
        }
    }
}