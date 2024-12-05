package com.aula.literatiapp.presentation.screens.reviews.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.StarRatingBar
import com.aula.literatiapp.presentation.screens.reviews.viewModels.ReviewViewModel
import com.aula.literatiapp.presentation.ui.theme.getTextColor
import com.aula.literatiapp.presentation.ui.theme.onPrimaryContainerLight
@Composable
fun MakeReviewScreen(
    bookId: String,
    userId: String,
    viewModel: ReviewViewModel,
    onCancel: () -> Unit,
    onReviewSubmitted: () -> Unit // Chamado após a review ser enviada com sucesso
) {
    var reviewText by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0f) }
    val maxReviewLength = 500

    // Estado de envio (mostra um botão desabilitado enquanto envia)
    val isSubmitting by viewModel.isSubmitting.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Row: Botões Cancelar e Enviar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { onCancel() }) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = getTextColor()
                )
            }

            Button(
                onClick = {
                    viewModel.postReview(
                        bookId = bookId,
                        userId = userId,
                        rating = rating.toDouble(),
                        comment = reviewText
                    )
                    onReviewSubmitted() // Chamado quando o envio é concluído
                },
                enabled = !isSubmitting && reviewText.isNotEmpty() && rating > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = onPrimaryContainerLight
                )
            ) {
                Text(
                    text = if (isSubmitting) stringResource(id = R.string.sending) else stringResource(
                        id = R.string.enviar_review
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Avaliação (estrelas)
        Text(text = stringResource(id = R.string.rate_book))
        Spacer(modifier = Modifier.height(8.dp))

        StarRatingBar(
            rating = rating,
            onRatingChanged = { newRating -> rating = newRating }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Imagem de perfil e campo de texto
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "https://i.pinimg.com/enabled_hi/564x/35/d8/3d/35d83d2e796d5ae4558396ba4adf2cc8.jpg"),
                contentDescription = "profile picture",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = reviewText,
                onValueChange = { text ->
                    if (text.length <= maxReviewLength) reviewText = text
                },
                placeholder = { Text(text = stringResource(id = R.string.reviewPlaceholder)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Contagem de caracteres
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "${reviewText.length}/$maxReviewLength",
                color = if (reviewText.length > maxReviewLength) Color.Red else Color.Gray
            )
        }
    }
}
