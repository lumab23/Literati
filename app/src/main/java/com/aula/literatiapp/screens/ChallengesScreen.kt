package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.ButtonComponenent
import com.aula.literatiapp.components.ChallengeBox
import com.aula.literatiapp.components.ChallengeDashboard
import com.aula.literatiapp.components.MenorDashboard
import com.aula.literatiapp.components.NoChallenges

@Composable
fun ChallengesScreen(navController: NavController, modifier: Modifier){
    Scaffold(
        topBar = {
            MenorDashboard(
                value = stringResource(R.string.challenges),
                navController = navController,
                modifier = Modifier
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
                ButtonComponenent(value = "Adicionar Meta", route = "", navController = navController)
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    name = "Bookshelv"
)
@Composable
fun ChallengesPreview() {
    val navController = rememberNavController()
    ChallengesScreen(navController = navController, modifier = Modifier)
}