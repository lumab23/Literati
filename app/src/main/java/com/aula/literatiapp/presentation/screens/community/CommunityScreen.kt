package com.aula.literatiapp.presentation.screens.community

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.presentation.common.sharedComponents.MainDashboard
import com.aula.literatiapp.presentation.screens.community.components.ScrollableCommunityColumn
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.SearchBarComponent
import com.aula.literatiapp.presentation.screens.community.components.CommunitiesControl
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight

@Composable
fun CommunityScreen(navController: NavController) {

    Scaffold(
        topBar = {
            MainDashboard(modifier = Modifier, value = stringResource(id = R.string.comunidades), fontSize = 20.sp)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {

            CommunitiesControl(navController)
        }
    }
}
