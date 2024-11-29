package com.aula.literatiapp.presentation.screens.bookDetails.components.tags

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aula.literatiapp.R
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.common.sharedViewModels.TagsViewModel

// TODO: Adicionar tag personalizada
@Composable
fun SelectTagDialog(
    onDismissRequest: () -> Unit,
    onTagSelected: (List<String>) -> Unit,
    bookId: String,
    initialTags: List<String>
) {
    val selectedTag: TagsViewModel = viewModel()
    // Lista de tags predefinidas
    val predefinedTags = listOf("Quero ler", "Abandonei", "Estou lendo", "Quero trocar", "Completo", "Favoritos")
    val selectedTags = remember { mutableStateListOf(*initialTags.toTypedArray()) }

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
                    text = stringResource(id = R.string.select_tag),
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Exibir as tags predefinidas
                LazyColumn {
                    items(predefinedTags) { tag ->
                        SelectableChip(
                            text = tag,
                            isSelected = tag in selectedTags,
                            onSelected = { isSelected ->
                                if (isSelected) {
                                    selectedTags.add(tag)
                                } else {
                                    selectedTags.remove(tag)
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botões de ação
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(
                            text = stringResource(id = R.string.cancel)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    TextButton(onClick = {
                        onTagSelected(selectedTags.toList())
                        onDismissRequest()
                    }) {
                        Text(
                            text = stringResource(id = R.string.ok)
                        )
                    }
                }
            }
        }
    }
}