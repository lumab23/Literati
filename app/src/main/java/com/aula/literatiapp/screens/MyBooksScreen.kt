package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.ChallengeComponent
import com.aula.literatiapp.components.MainDashboard
import com.aula.literatiapp.components.MyBooksBoxComponent
import com.aula.literatiapp.model.Book

@Composable
fun MyBooksScreen(navController: NavController) {

    val bookImages = remember {
        Book(
            title = "The Handmaid's Tale",
            authors = listOf("Margaret Atwood"),
            publisher = "Penguin Random House",
            description = "Offred is a Handmaid in the Republic of Gilead. She may leave the home of the Commander and his wife once a day to walk to food markets whose signs are now pictures instead of words because women are no longer allowed to read. She must lie on her back once a month and pray that the Commander makes her pregnant, because in an age of declining births, Offred and the other Handmaids are valued only if their ovaries are viable. Offred can remember the years before, when she lived and made love with her husband, Luke; when she played with and protected her daughter; when she had a job, money of her own, and access to knowledge. But all of that is gone now…\n" +
                    "\n" +
                    "Funny, unexpected, horrifying, and altogether convincing, The Handmaid's Tale is at once scathing satire, dire warning, and tour de force.",
            imageUrl = "https://i.pinimg.com/enabled_hi/564x/8e/17/6a/8e176a8b0c8755ac425c1abe143cefed.jpg",
            tags = "Dystopian",
            genres = listOf("Clássicos", "Literatura", "Fantasia", "Ficção Científica"),
            id = "1",
            volumeInfo = "3 edição",
            pages = "400",
            review = "Muito bom!!"
        )
    }

    Scaffold( // seções tipo div
        topBar = {
            //pegar menor dashboard (componente)
            MainDashboard(
                value = stringResource(R.string.bookshelv), fontSize = 20.sp
            )
        },
        //pegar bottomNavigation (componente)
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            MyBooksBoxComponent(book = bookImages, navController = navController)
            Spacer(modifier = Modifier.height(20.dp))
            ChallengeComponent(navController = navController)
        }
    }

}