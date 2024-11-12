package com.aula.literatiapp.presentation.common.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.ui.theme.onPrimaryLight

@Composable
fun SearchBarComponent(
    query: String,
    modifier: Modifier,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {

    var search by remember {
        mutableStateOf(TextFieldValue(query))
    }

    BasicTextField(
        value = search,
        onValueChange = {
            search = it
            onQueryChange(it.text)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp)
            .background(color = Color.Gray.copy(alpha = 0.05f), shape = MaterialTheme.shapes.small)
            .onKeyEvent { keyEvent ->
                if (keyEvent.nativeKeyEvent.keyCode == Key.Enter.nativeKeyCode) {
                    onSearch()
                    true
                } else {
                    false
                }
            },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = onPrimaryLight
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.weight(1f)) {
                    if (search.text.isEmpty()) {
                        Text(text = stringResource(R.string.pesquise_por), color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        }

    )
}