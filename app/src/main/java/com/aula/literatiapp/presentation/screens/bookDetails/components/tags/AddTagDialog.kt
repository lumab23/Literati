package com.aula.literatiapp.presentation.screens.bookDetails.components.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.aula.literatiapp.R

@Composable
fun AddTagDialog(
    onDismissRequest: () -> Unit,
    onTagAdded: (List<String>) -> Unit
) {
    var newTag by remember { mutableStateOf("") }
    var selectedTags by remember { mutableStateOf(listOf<String>()) }

    // Tags predefinidas
    val predefinedTags = listOf("Quero ler", "Abandonei", "Estou lendo", "Quero trocar")

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.add_tag),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Exibir tags predefinidas para seleção
                Text(text = "Selecione uma tag:")
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow {
                    items(predefinedTags) { tag ->
                        SelectableChip(
                            text = tag,
                            isSelected = selectedTags.contains(tag),
                            onSelected = { isSelected ->
                                selectedTags = if (isSelected) {
                                    selectedTags + tag
                                } else {
                                    selectedTags - tag
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo para adicionar nova tag
                OutlinedTextField(
                    value = newTag,
                    onValueChange = { newTag = it },
                    label = { Text("Nova Tag") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    if (newTag.isNotEmpty()) {
                        selectedTags = selectedTags + newTag
                        newTag = "" // Limpar campo de texto
                    }
                }) {
                    Text("Adicionar Tag")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Exibir as tags já selecionadas
                Text(text = "Tags selecionadas:")
                LazyRow {
                    items(selectedTags) { tag ->
                        Chip(tag)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botão para salvar e fechar o diálogo
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancelar")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    TextButton(onClick = {
                        onTagAdded(selectedTags)
                        onDismissRequest()
                    }) {
                        Text("Salvar")
                    }
                }
            }
        }
    }
}