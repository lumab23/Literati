package com.aula.literatiapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.R
import com.aula.literatiapp.components.BottomNavigation
import com.aula.literatiapp.components.ScrollableBookRow
import com.aula.literatiapp.components.SectionNameMenor

@Composable
fun ProfileScreen(navController: NavController) {
    val favoritos = listOf(
        R.drawable.beachread,
        R.drawable.pride_and_prejudice,
        R.drawable.tshoeh,
        R.drawable.tbosas,
        R.drawable.romeoandjuliet
    )

    val lendo = listOf(
        R.drawable.theshining,
        R.drawable.dialaforaunties,
        R.drawable.thecruelprince,
        R.drawable.thesongofachilles,
        R.drawable.dune_bookcover
    )

    val troca = listOf(
        R.drawable._984,
        R.drawable.anofferfromangentleman,
        R.drawable.itstartswithus,
        R.drawable.booklovers,
        R.drawable.it
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(modifier = Modifier, navController = navController)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(5.dp)
        ) {
            // Cabeçalho do perfil
            item {
                TopBar(navController = navController)

                // Divisória após o topo
                HorizontalDivider(
                    thickness = 2.dp,
                    color = colorResource(id = R.color.tertiary_container)
                )

                Spacer(modifier = Modifier.height(20.dp)) // Espaço entre a divisória e a próxima seção
            }

            // Seção de favoritos
            item {
                SectionNameMenor(value = stringResource(id = R.string.favoritos_profile))
                Spacer(modifier = Modifier.height(10.dp))
                ScrollableBookRow(bookList = favoritos, navController = navController)
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(
                    thickness = 2.dp,
                    color = colorResource(id = R.color.tertiary_container)
                )
                Spacer(modifier = Modifier.height(15.dp))
            }

            // Seção de lendo
            item {
                SectionNameMenor(value = stringResource(id = R.string.lendo))
                Spacer(modifier = Modifier.height(10.dp))
                ScrollableBookRow(bookList = lendo, navController = navController)
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(
                    thickness = 2.dp,
                    color = colorResource(id = R.color.tertiary_container)
                )
                Spacer(modifier = Modifier.height(15.dp))
            }

            // Seção de troca
            item {
                SectionNameMenor(value = stringResource(id = R.string.disp_troca))
                Spacer(modifier = Modifier.height(10.dp))
                ScrollableBookRow(bookList = troca, navController = navController)
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(
                    thickness = 2.dp,
                    color = colorResource(id = R.color.tertiary_container)
                )
                Spacer(modifier = Modifier.height(15.dp))
            }


        }
    }
}



@Composable
fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
        }

        // Add Box to position the profile picture in the middle
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .offset(y = 50.dp)
                .height(180.dp), // Center the image
            contentAlignment = Alignment.TopCenter // Align to the top center of the Box
        ) {
            // Use Column to stack the image and the Row below it
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Align elements horizontally in the center
                verticalArrangement = Arrangement.Center // Center the elements vertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture), // Replace with your image resource
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .size(80.dp) // Adjust the size of the image
                        .clip(CircleShape) // Make the image round
                        .border(2.dp, Color.Gray, CircleShape) // Optional border
                )

                Spacer(modifier = Modifier.height(8.dp)) // Space between image and Row

                Row(
                    verticalAlignment = Alignment.CenterVertically // Align the icon and text vertically centered
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Troca disponível",
                        tint = colorResource(id = R.color.secondary),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp)) // Space between icon and text
                    Text(text = "Livros para trocar", fontSize = 12.sp)
                }
            }
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ChatBubble, contentDescription = "chat")
        }
    }
}




@Preview (showSystemUi = true, name = "top bar row - profile")
@Composable
fun TopBarRowPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)

}