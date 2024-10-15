package com.aula.literatiapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val gradientBrush = Brush.horizontalGradient(
    listOf(
        Color(0xFF5BDF92),
        Color(0xFF8BE95B)
    )
)

val darkGreen = Color(0xFF5BDF92)
val lightGreen = Color(0xFF8BE95B)

val primary = Color(0xFF59A679)
val secondary = Color(0xFF586249)
val tertiary = Color(0xFF4C662B)

val primaryContainer = Color(0xFF59A679)
val secondaryContainer = Color(0xFFdce7c8)
val tertiaryContainer = Color(0xFFcdeda3)

// surface colors
val surfaceDim = Color(0xFFdadbd0)
val surface = Color(0xFFf9faef)
val surfaceBright = Color(0xFFf9faef)

// text color
val textColor = Color(0xFF000000)

// error colors
val errorTextColor = Color(0xFFba1a1a)
val errorTextColorContainer = Color(0xFFffdad6)