package com.aula.literatiapp.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation

@Composable
fun MyBooksScreen(navController: NavController) {

    Scaffold(
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) {paddingValues ->

    }

}