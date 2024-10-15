package com.aula.literatiapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.ChallengeComponent
import com.aula.literatiapp.components.IconsBookshelv
import com.aula.literatiapp.components.MainDashboard
import com.aula.literatiapp.components.NoChallenges
import com.aula.literatiapp.components.ScrollableBookRow

@Composable
fun MyBooksScreen(navController: NavController) {

    val savedBooks = listOf(
        R.drawable.beachread,
        R.drawable.booklovers,
        R.drawable.anofferfromangentleman,
        R.drawable.tshoeh,
        R.drawable.thesongofachilles,
        R.drawable.hernameinthesky,
        R.drawable.thenotebook,
        R.drawable.thecruelprince,
        R.drawable.frankenstein,
        R.drawable.emma,
        R.drawable._984,
        R.drawable.mobydick,
        R.drawable.thepictureofdoriangrey,
        R.drawable.romeoandjuliet,
        R.drawable.jane_eyre,
        R.drawable.pride_and_prejudice
    )

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(0.dp),
        ){
            Column (){
                IconsBookshelv()
                Spacer(modifier = Modifier.height(32.dp))
                ScrollableBookRow(bookList = savedBooks, navController = navController )
            }
            Column {
                Spacer(modifier = Modifier.height(350.dp))
                ChallengeComponent(navController = navController)
                //Spacer(modifier = Modifier.height(450.dp))
                NoChallenges(navController = navController)
            }
        }
    }

}