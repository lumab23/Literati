package com.aula.literatiapp.navigation

import android.window.OnBackAnimationCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalBackPressedDispatcher =
    staticCompositionLocalOf<OnBackPressedDispatcherOwner?> { null }

