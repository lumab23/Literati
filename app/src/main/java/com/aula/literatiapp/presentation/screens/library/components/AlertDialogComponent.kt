package com.aula.literatiapp.presentation.screens.library.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.ui.theme.secondaryContainerLight


@Composable
fun AlertDialogComponent(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogInput: String,
    onValueChange: (String) -> Unit
) {

    var inputValue by remember {
        mutableStateOf(dialogInput)
    }

    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Default.Book, contentDescription = "book icon")
        },
        title = {
            Text(
                // TODO: Adiconar material theme typography
                text = stringResource(id = R.string.dialogTitle),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column {
                Spacer(modifier = Modifier.height(5.dp))
                TextField(
                    value = inputValue,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }) {
                            inputValue = newValue
                            onValueChange(newValue)
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.digitemeta)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTrailingIconColor = secondaryContainerLight
                    )
                )
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.confirm),
                    color = secondaryContainerLight
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissRequest()
            }) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    color = secondaryContainerLight
                )
            }
        },
    )

}