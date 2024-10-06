package com.aula.literatiapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.CategorySection
import com.aula.literatiapp.components.MenorDashboard
import com.aula.literatiapp.components.ScrollableBookColumn
import com.aula.literatiapp.navigation.Screen

@Composable
fun ReleaseDateScreen(navController: NavController) {

    val decade = listOf(
        "A Vir",
        "2020s",
        "2010s",
        "2000s",
        "1990s",
        "1980s"
    )

    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.decada), navController = navController, modifier = Modifier)
                 },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .align(Alignment.TopCenter) //
            ) {

                CategorySection(
                    "",
                    categories = decade,
                    onCategoryClick = { selectedDecade ->
                        when (selectedDecade) {
                            "A Vir" -> {
                                navController.navigate(Screen.AVirScreen.route)
                            }
                            "2020s" -> {
                                navController.navigate(Screen.Screen2020s.route)
                            }
                            "2010s" -> {
                                navController.navigate(Screen.Screen2010s.route)
                            }
                            "2000s" -> {
                                navController.navigate(Screen.Screen2000s.route)
                            }
                            "1990s" -> {
                                navController.navigate(Screen.Screen1990s.route)
                            }
                            "1980s" -> {
                                navController.navigate(Screen.Screen1980s.route)
                            }
                        }

                    }
                )
            }

        }
    }

}

@Composable
fun AVirScreen(navController: NavController) {
    // Conteúdo da tela "A Vir"
    val books = listOf(
        R.drawable._984
    )
    Scaffold(
        topBar ={
            MenorDashboard(value = stringResource(id = R.string.decada), navCrontoller = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ){ paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)
        ScrollableBookColumn(
            bookList = books,
            navController = navController,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun Screen2020s(navController: NavController) {
    // Conteúdo da tela "2020s"
    val books = listOf(
        R.drawable.americandirt,
        R.drawable.babel,
        R.drawable.loveonthebrain,
        R.drawable.bride,
        R.drawable.atempestoftea,
        R.drawable.dialaforaunties,
        R.drawable.funnystory,
        R.drawable.honeygirl,
        R.drawable.ikissedsharawheeler,
        R.drawable.infiveyears,
        R.drawable.justforthesummer,
        R.drawable.loveonthebrain,
        R.drawable.notinlove,
        R.drawable.tbosas,
        R.drawable.thefamiliar,
        R.drawable.theinvisiblelifeofaddielarue,
        R.drawable.themidnightlibrary,
        R.drawable.theotherblackgirl
    )

    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.decada), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            ScrollableBookColumn(
                bookList = books,
                navController = navController,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun Screen2010s(navController: NavController) {
    val books = listOf(
    R.drawable.theotherblackgirl
    )

    Scaffold (
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.decada), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            ScrollableBookColumn(
                bookList = books,
                navController = navController,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun Screen2000s(navController: NavController) {
    val books = listOf(
        R.drawable.it
    )

    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.decada), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            ScrollableBookColumn(
                bookList = books,
                navController = navController,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun Screen1990s(navController: NavController) {

    val books = listOf(
        R.drawable.babel
    )

    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.decada), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            ScrollableBookColumn(
                bookList = books,
                navController = navController,
                modifier = Modifier
                    .padding(116.dp)
            )
        }

    }
}

@Composable
fun Screen1980s(navController: NavController) {
    // Conteúdo da tela "1980s"
    val books = listOf(
        R.drawable.tbosas
    )

    Scaffold(
        topBar = {
            MenorDashboard(value = stringResource(id = R.string.decada), navController = navController, modifier = Modifier)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            ScrollableBookColumn(
                bookList = books,
                navController = navController,
                modifier = Modifier
                    .padding(116.dp)
            )
        }

    }
}


@Preview(
    showSystemUi = true,
    name = "Release Date Screen"
)
@Composable
fun ReleaseDateScreenPreview() {
    val navController = rememberNavController()
    ReleaseDateScreen(navController = navController)
}