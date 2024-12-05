package com.aula.literatiapp.presentation.screens.reviews.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.screens.reviews.viewModels.ReviewViewModel

@Composable
fun AddReviewScreen(
    navController: NavController,
    bookId: String,
    userId: String,
    viewModel: ReviewViewModel = hiltViewModel() // Certifique-se de configurar o Hilt
) {
    MakeReviewScreen(
        bookId = bookId,
        userId = userId,
        viewModel = viewModel,
        onCancel = { navController.popBackStack() },
        onReviewSubmitted = {
            // Após enviar a review, volte para a tela anterior
            navController.popBackStack()
        }
    )
}
