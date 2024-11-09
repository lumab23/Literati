package com.aula.literatiapp.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)