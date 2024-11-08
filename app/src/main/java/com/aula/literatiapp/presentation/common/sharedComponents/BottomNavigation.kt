package com.aula.literatiapp.presentation.common.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.navigation.Screen
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight

@Composable
fun BottomNavigation(modifier: Modifier, navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(brush = gradientBrushLight)
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier.fillMaxSize()
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Bookmarks,
                        contentDescription = null
                    )
                },
                label = null,
                selected = false,
                onClick = {
                    navController.navigate("library_screen")
                }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.People,
                        contentDescription = null
                    )
                },
                label = null,
                selected = false,
                onClick = {
                    navController.navigate("community_screen")
                }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = null,
                selected = false,
                onClick = {
                    navController.navigate("home_screen")
                }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                label = null,
                selected = false,
                onClick = {
                    navController.navigate("search_screen")
                }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.PermIdentity,
                        contentDescription = null
                    )
                },
                label = null,
                selected = false,
                onClick = {
                    navController.navigate("profile_screen")
                }
            )
        }
    }
}