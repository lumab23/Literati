package com.aula.literatiapp.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.User
import com.aula.literatiapp.presentation.common.sharedComponents.BottomNavigation
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection
import com.aula.literatiapp.presentation.common.sharedComponents.ScrollableBookRow
import com.aula.literatiapp.presentation.screens.profile.components.ProfileScreenDashboard
import com.aula.literatiapp.presentation.screens.profile.components.ProfileSection
import com.aula.literatiapp.presentation.screens.profile.viewModels.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    navController: NavController)
{

    val user by viewModel.user.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val recentReads by viewModel.recentReads.collectAsState()
    val swaps by viewModel.exchange.collectAsState()



    val listas = listOf(
        "Reviews",
        "Tags"
    )

    user.let {
        Scaffold(
            topBar = {
                ProfileScreenDashboard(navController = navController)
            },
            bottomBar = {
                BottomNavigation(modifier = Modifier, navController = navController)
            }
        ) { paddingValues ->

            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(5.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {

                item {
                    if (it != null) {
                        ProfileSection(user = it)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }

                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.favoritos),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    //ScrollableBookRow(bookList = favorites, navController = navController)
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(15.dp))
                }

                // Seção de lendo
                item {
                    Text(
                        text = stringResource(id = R.string.recentes),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    //ScrollableBookRow(bookList = recentReads, navController = navController)
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(15.dp))
                }

                // Seção de troca
                item {
                    Text(
                        text = stringResource(id = R.string.troca),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    //ScrollableBookRow(bookList = swaps, navController = navController)
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }

                item {
                    CategorySection(
                        title = "",
                        categories = listas,
                        onCategoryClick = { selectedSection ->
                            when (selectedSection) {
                                "Reviews" -> {
                                    navController.navigate("reviews_screen")
                                }

                                "Tags" -> {
                                    navController.navigate("tags_screen")
                                }
                            }

                        }
                    )
                }
            }
        }
    }
}


