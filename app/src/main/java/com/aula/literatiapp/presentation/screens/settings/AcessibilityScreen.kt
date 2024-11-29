package com.aula.literatiapp.presentation.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.screens.settings.components.CategorySectionAccessibility

@Composable
fun AccessibilityScreen(navController: NavController) {

    var expanded by remember { mutableStateOf(false) }
    val fontSizes = listOf(12, 14, 16, 18, 20, 22, 24, 26, 28, 30)
    val op = listOf(
        "Contraste",
        "Tamanho da fonte",
        "Tema"
    )

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = "Acessibilidade", navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(paddingValues)
        ){
            CategorySectionAccessibility(
                categories = op,
                onCategoryClick = {}
            )
        }
    }
}