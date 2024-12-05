import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.BackNavigationDashboard
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.screens.reviews.components.ReviewComponent
import com.aula.literatiapp.presentation.screens.reviews.viewModels.ReviewViewModel
import kotlinx.coroutines.launch

@Composable
fun ReviewsScreen(
    navController: NavController,
    userId: String, // ID do usuário cujas reviews queremos exibir
    viewModel: ReviewViewModel = viewModel() // ViewModel para gerenciar o estado
) {
    // Coleta o estado das reviews e o estado de carregamento
    val reviews = viewModel.userReviews.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    // Lembre-se de buscar as reviews no início
    val scope = rememberCoroutineScope()
    rememberCoroutineScope {
        scope.launch {
            viewModel.fetchUserReviews(userId)
        }
    }

    Scaffold(
        topBar = {
            BackNavigationDashboard(value = stringResource(id = R.string.reviews), navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        if (isLoading) {
            // Exibe um indicador de carregamento
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(paddingValues)
                    .align(Alignment.CenterHorizontally)
            )
        } else if (reviews.isEmpty()) {
            // Exibe uma mensagem caso o usuário não tenha reviews
            Text(
                text = "Nenhuma review encontrada para este usuário.",
                modifier = Modifier
                    .padding(paddingValues)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            // Exibe a lista de reviews
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(8.dp)
            ) {
                items(reviews.size) { index ->
                    ReviewComponent(bookId = reviews[index].bookId, review = reviews[index], navController = navController)
                    if (index < reviews.size - 1) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
