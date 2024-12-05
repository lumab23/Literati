import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aula.literatiapp.presentation.screens.reviews.viewModels.Review

@Composable
fun ReviewComponent(
    bookId: String,
    review: Review,
    navController: NavController
) {
    // Criação do card básico com uma borda
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
            .clickable {
                // Exemplo: navegação para detalhes do livro, se implementado
                navController.navigate("book_details_screen/$bookId")
            }
    ) {
        // Layout da Review (título do livro, nota, comentário, etc.)
        Column(modifier = Modifier.fillMaxWidth()) {
            // ID do Livro (pode ser substituído por título em um futuro fetch)
            Text(
                text = "Livro ID: $bookId",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Nota do livro
            Text(
                text = "Nota: ${review.rating} / 5",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold),
                color = Color(0xFF00796B), // Verde para destacar
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Comentário do usuário
            Text(
                text = "Comentário: ${review.comment}",
                style = TextStyle(fontSize = 14.sp),
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Autor do comentário (ID do usuário)
            Text(
                text = "Autor: ${review.userId}",
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light),
                color = Color.Gray
            )
        }
    }
}
