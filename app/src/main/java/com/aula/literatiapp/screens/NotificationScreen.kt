package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.components.BackNavigationDashboard
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.NotificationComponent
import com.aula.literatiapp.components.ReviewComponent
import com.aula.literatiapp.model.Notification

@Composable
fun NotificationScreen(navController: NavController) {

    val notifInfo = remember {
        listOf(
            Notification(
                name = "Luma",
                action = " deu like em sua review de ",
                time = "12:00 AM",
                userImageUrl = "https://i.pinimg.com/564x/36/df/aa/36dfaaea22603036385b3bd016820e47.jpg",
                nomeLivroOuComunidade = "Percy Jackson."
            ),
            Notification(
                name = "Raquel",
                action = " deu like em seu post da comunidade ",
                time = "12:00 AM",
                userImageUrl = "https://i.pinimg.com/564x/13/98/3c/13983c6a0efd959b096d50019dba1cd2.jpg",
                nomeLivroOuComunidade = " de Amantes de Jane Austen."
            )
        )
    }

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.notif), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) {paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            items(notifInfo.size) { index ->
                NotificationComponent(notifInfo = notifInfo[index], navController = navController)

                // Exibe o divisor abaixo de cada review, exceto após o último item
                if (index < notifInfo.size - 1) {
                    HorizontalDivider()
                }
            }
        }

    }

}