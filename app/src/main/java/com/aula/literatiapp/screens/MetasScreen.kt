package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.components.BackNavigationDashboard
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.ButtonComponent
import com.aula.literatiapp.components.ChallengeBox
import com.aula.literatiapp.components.NoChallenges
import com.aula.literatiapp.navigation.Screen

@Composable
fun MetasScreen(navController: NavController) {

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
    ){ paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(0.dp),
        ){
            Column {
                ChallengeBox(modifier = Modifier)
                Spacer(modifier = Modifier.height(180.dp))
                NoChallenges(navController = navController)
                Spacer(modifier = Modifier.height(80.dp))
                ButtonComponent(value = "Adicionar Meta", route = Screen.MetasScreen.route, navController = navController)
            }
        }
    }

}