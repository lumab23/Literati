package com.aula.literatiapp.presentation.common.sharedComponents

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.aula.literatiapp.presentation.ui.theme.primaryContainerLight
import com.aula.literatiapp.presentation.ui.theme.primaryLight
import com.aula.literatiapp.presentation.ui.theme.secondaryContainerLight
import com.aula.literatiapp.presentation.ui.theme.secondaryLight


@Composable
fun CustomSwitch() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        modifier = Modifier,
        checked = checked,
        onCheckedChange = { checked = it },
        colors = SwitchDefaults.colors(
            checkedThumbColor = primaryLight,
            checkedTrackColor = primaryContainerLight,
            uncheckedThumbColor = secondaryLight,
            uncheckedTrackColor = secondaryContainerLight
        )
    )

}