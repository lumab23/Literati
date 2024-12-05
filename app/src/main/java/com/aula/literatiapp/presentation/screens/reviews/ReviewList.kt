import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.presentation.screens.reviews.viewModels.ReviewViewModel

@Composable
fun ReviewList(
    bookId: String,
    viewModel: ReviewViewModel
) {
    // Coletando o estado das reviews (Flow -> State)
    val reviews = viewModel.reviews.collectAsState(initial = emptyList()).value
    val isLoading = viewModel.isLoading.collectAsState(initial = true).value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Exibindo título
        Text(
            text = "Reviews do Livro",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        if (isLoading) {
            // Indicador de carregamento
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
            )
        } else if (reviews.isEmpty()) {
            // Mensagem de nenhuma review encontrada
            Text(
                text = "Nenhuma review encontrada.",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            // Exibindo a lista de reviews
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(reviews) { review ->
                    ReviewCard(review = review)
                }
            }
        }
    }
}
