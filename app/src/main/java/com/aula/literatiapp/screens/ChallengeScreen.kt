package com.aula.literatiapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.ChallengeDashboard
import com.aula.literatiapp.components.DashboardWithoutBack
import com.aula.literatiapp.components.IconsBookshelv
import com.aula.literatiapp.components.MenorDashboard

@Composable
fun ChallengeScreen(modifier: Modifier, navController: NavController) {
    Scaffold( // seções tipo div
        topBar = {
            //pegar menor dashboard (componente)
            MenorDashboard(
                value = stringResource(R.string.challenge),
                navController = navController,
                modifier = Modifier
            )
        },
        //pegar bottomNavigation (componente)
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    )  { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(0.dp)

        ){
        }
    }
}

