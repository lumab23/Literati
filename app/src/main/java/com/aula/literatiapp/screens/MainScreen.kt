package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.R
import com.aula.literatiapp.components.MainDashboard
import com.aula.literatiapp.components.ScrollableBookRow
import com.aula.literatiapp.components.SectionName

@Composable
fun MainScreen(navController: NavController) {
    val stephenKingBooksRec = listOf(
        R.drawable.it,
        R.drawable.carrie,
        R.drawable.thebody,
        R.drawable.theoutsider,
        R.drawable.thegunslinger
    )

    val fantasy = listOf(
        R.drawable.thereturnoftheking,
        R.drawable.hp_gobletoffiere,
        R.drawable.thehobbit,
        R.drawable.thetwotowers,
        R.drawable.sixofcrows,
        R.drawable.acourtofthornsandrose
    )

    val romance = listOf(
        R.drawable.beachread,
        R.drawable.booklovers,
        R.drawable.anofferfromangentleman,
        R.drawable.tshoeh,
        R.drawable.thesongofachilles,
        R.drawable.hernameinthesky,
        R.drawable.thenotebook,
        R.drawable.thecruelprince
    )

    val classics = listOf(
        R.drawable.frankenstein,
        R.drawable.emma,
        R.drawable._984,
        R.drawable.mobydick,
        R.drawable.thepictureofdoriangrey,
        R.drawable.romeoandjuliet,
        R.drawable.jane_eyre,
        R.drawable.pride_and_prejudice
    )

    Scaffold(
        topBar = {
            MainDashboard(value = stringResource(id = R.string.literati), fontSize = 28.sp)
            //SearchBarComponent(modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                SectionName(value = stringResource(id = R.string.recomendacao_baseada))
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                ScrollableBookRow(bookList = stephenKingBooksRec, navController = navController)
                Spacer(modifier = Modifier.height(40.dp))
            }

            item {
                SectionName(value = stringResource(id = R.string.trend_fantasy))
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                ScrollableBookRow(bookList = fantasy, navController = navController)
                Spacer(modifier = Modifier.height(40.dp))
            }

            item {
                SectionName(value = stringResource(id = R.string.trend_romance))
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                ScrollableBookRow(bookList = romance, navController = navController)
                Spacer(modifier = Modifier.height(40.dp))
            }

            item {
                SectionName(value = stringResource(id = R.string.trend_classicos))
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                ScrollableBookRow(bookList = classics, navController = navController)
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    name = "Main Screen"
)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}
