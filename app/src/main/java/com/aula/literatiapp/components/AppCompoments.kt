package com.aula.literatiapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.navigation.Screen
import com.aula.literatiapp.ui.theme.primaryContainer
import com.aula.literatiapp.ui.theme.surfaceDim
import com.aula.literatiapp.ui.theme.textColor

@Composable
fun NormalTextComponent(
    value: String
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp, // mudar para 18
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = textColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SmallTextComponent(
    value: String
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = textColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(
    value: String
) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp, // mudar para 25
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = textColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun MyTextFieldComponent(labelValue: String, leadingIcon: ImageVector) {

    val value = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = labelValue, fontSize = 12.sp) },
        value = value.value,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.primary_container),
            focusedLabelColor = colorResource(id = R.color.primary_container),
            cursorColor = colorResource(id = R.color.primary_container),
        ),
        keyboardOptions = KeyboardOptions.Default,
        onValueChange = {
            value.value = it
        },
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = null)
        }
    )
}

@Composable
fun MyPasswordFieldComponent(labelValue: String, leadingIcon: ImageVector) {

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = labelValue, fontSize = 12.sp) },
        value = password.value,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.primary_container),
            focusedLabelColor = colorResource(id = R.color.primary_container),
            cursorColor = colorResource(id = R.color.primary_container),
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        onValueChange = {
            password.value = it
        },
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = null)
        },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisible.value = !passwordVisible.value
            }) {
                Icon(
                    imageVector = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (passwordVisible.value) "Hide password" else "Show password"
                )
            }
        }
    )
}

@Composable
fun CheckboxComponent() {

}


@Composable
fun ButtonComponenent(value: String, route: String, navController: NavController) {

    Button(
        onClick = { navController.navigate(route) },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.primary_container) // Use containerColor for background
        ),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .padding(16.dp)
    ) {
        Text(
            text = value,
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
    }

}

@Composable
fun AlternativeOptionGoogle(
    onIconClick: (index: Int) -> Unit,
    value: String,
    //route: String,
    //navController: NavController,
    painter: Painter,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SmallTextComponent(value = value)
        
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painter,
                contentDescription = "google logo",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
            )
        }


    } 

}

@Composable
fun AlternativeOptionAccount(
    //onLinkClick: () -> Unit,
    value: String,
    linkText: String,
    route: String,
    navController: NavController,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = value, fontSize = 14.sp)
        Text(
            text = linkText,
            color = colorResource(id = R.color.text_color),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .clickable { navController.navigate(route) }
                .padding(start = 4.dp)
        )
    }
}
