package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.R
import com.aula.literatiapp.components.AvaliacaoComponent
import com.aula.literatiapp.components.BiggerBookCard
import com.aula.literatiapp.components.BookInfo
import com.aula.literatiapp.components.BookScreenWithTabs
import com.aula.literatiapp.components.MyBookDashboard
import com.aula.literatiapp.model.Book

@Composable
fun BookScreen(navController: NavController) {

    val book = remember {
        Book(
            title = "The Handmaid's Tale",
            authors = listOf("Margaret Atwood"),
            publisher = "Penguin Random House",
            description = "Offred is a Handmaid in the Republic of Gilead. She may leave the home of the Commander and his wife once a day to walk to food markets whose signs are now pictures instead of words because women are no longer allowed to read. She must lie on her back once a month and pray that the Commander makes her pregnant, because in an age of declining births, Offred and the other Handmaids are valued only if their ovaries are viable. Offred can remember the years before, when she lived and made love with her husband, Luke; when she played with and protected her daughter; when she had a job, money of her own, and access to knowledge. But all of that is gone now…\n" +
                    "\n" +
                    "Funny, unexpected, horrifying, and altogether convincing, The Handmaid's Tale is at once scathing satire, dire warning, and tour de force.",
            imageUrl = "",
            tags = "Dystopian",
            genres = listOf("Clássicos", "Literatura", "Fantasia", "Ficção Científica"),
            id = "1",
            volumeInfo = "3 edição",
            pages = "400",
            review = "Muito bom!!"
        )
    }

    Scaffold(
        topBar = {
            MyBookDashboard(navController = navController)
        },
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                item {
                    BookInfo(book = book, navController = navController)
                    Spacer(modifier = Modifier.height(15.dp))
                    AvaliacaoComponent(navController = navController)
                    Spacer(modifier = Modifier.height(15.dp))
                    BookScreenWithTabs(book = book)
                }
        }
    }
}

@Preview(
    showSystemUi = true,
    name = "Bookshelv"
)
@Composable
fun BooksScreen() {
    val navController = rememberNavController()
    BookScreen(navController = navController)
}