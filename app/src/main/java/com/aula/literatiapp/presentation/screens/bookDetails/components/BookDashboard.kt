package com.aula.literatiapp.presentation.screens.bookDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.presentation.common.sharedComponents.CategorySection
import com.aula.literatiapp.presentation.common.sharedViewModels.TagsViewModel
import com.aula.literatiapp.presentation.screens.bookDetails.components.tags.SelectTagDialog
import com.aula.literatiapp.presentation.ui.theme.gradientBrushLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookDashboard(
    navController: NavController,
    bookId: Book
) {
    val selectedTag: TagsViewModel = viewModel()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showTagDialog by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    Column(
        Modifier
            .height(80.dp)
            .background(brush = gradientBrushLight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .height(70.dp)
        ) {
            val (backIcon, moreIcon) = createRefs()

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(50.dp)
                    .constrainAs(backIcon) {
                        start.linkTo(parent.start, margin = 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "back",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = { showBottomSheet = true },
                modifier = Modifier
                    .size(50.dp)
                    .constrainAs(moreIcon) {
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "back",
                    tint = Color.White
                )
            }

        }

        val categories = listOf("Review", "Adicione na biblioteca")

        // bottom sheet
        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {

                CategorySection(
                    title = "",
                    categories = categories,
                    onCategoryClick = { category ->
                        when (category) {
                            "Review" -> {
                                //navController.navigate("make_review_screen")
                            }
                            "Adicione na biblioteca" -> {
                                showBottomSheet = false
                                showTagDialog = true
                            }
                        }
                    }
                )

            }
        }

        if (showTagDialog) {

            val booksByTag = selectedTag.booksByTag.collectAsState().value

            val initialTags = booksByTag
                .filterValues { it.contains(bookId.id) }
                .keys
                .toList()

            SelectTagDialog(
                onDismissRequest = { showTagDialog = false },
                onTagSelected = { updatedTags ->
                    selectedTag.updateTagsForBook(updatedTags, bookId.id)
                    showTagDialog = false
                },
                bookId = bookId.id,
                initialTags =initialTags
            )
        }
    }
}