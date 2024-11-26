package com.aula.literatiapp.presentation.screens.chat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.aula.literatiapp.presentation.screens.chat.components.AddChannelDialog
import com.aula.literatiapp.presentation.screens.chat.components.ChannelItem
import com.aula.literatiapp.presentation.screens.chat.viewModels.ChatScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<ChatScreenViewModel>()
    val channels = viewModel.channels.collectAsState()
    val searchQuery = remember { mutableStateOf("") }
    val addChannel = remember{
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true // Adicionado para compatibilidade
    )

    Scaffold(
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Blue)
                    .clickable {
                        addChannel.value = true
                    }
            ){
                Text(
                    text = "Add Channel",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
            }
        },
        containerColor = Color.Black
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            LazyColumn {
                item{
                    Text(text = "Messages",
                        color = Color.Gray,
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(16.dp))

                }

                item {
                    OutlinedTextField(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        //placeholder = { Text(text = "Search") },
                        maxLines = 1, // Substitui singleLine
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(40.dp)),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        ),
                        trailingIcon = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                        }

                    )

                }


                items(channels.value){ channel ->
                    Column {
                        ChannelItem(channel.name,{
                            navController.navigate("chat/${channel.id}")
                        })
                    }
                }
            }
        }
    }
    if(addChannel.value){
        ModalBottomSheet(onDismissRequest ={
            addChannel.value = false
        }, sheetState = sheetState){
            AddChannelDialog {
                viewModel.addChannel(it)
                addChannel.value = false
            }
        }
    }
}


