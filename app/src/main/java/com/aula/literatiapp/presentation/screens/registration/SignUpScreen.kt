package com.aula.literatiapp.presentation.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.AlternativeOptionAccount
import com.aula.literatiapp.presentation.common.sharedComponents.AlternativeOptionGoogle
import com.aula.literatiapp.presentation.common.sharedComponents.ButtonComponent
import com.aula.literatiapp.presentation.common.sharedComponents.HeadlineText
import com.aula.literatiapp.presentation.common.sharedComponents.TextField
import com.aula.literatiapp.presentation.screens.registration.viewModels.SignUpState
import com.aula.literatiapp.presentation.screens.registration.viewModels.SignUpViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier,
    navController: NavController
) {
    var viewModel: SignUpViewModel = viewModel()
    val signUpState = viewModel.signUpState

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            HeadlineText(stringResource(R.string.crie_conta))

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = viewModel.username,
                onValueChange = { viewModel.onUsernameChange(it) },
                placeholder = stringResource(R.string.username),
                leadingIcon = Icons.Default.Person,
                isPassword = false,
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = viewModel.email,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = stringResource(R.string.email),
                leadingIcon = Icons.Default.Email,
                isPassword = false,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = viewModel.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeholder = stringResource(R.string.password),
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                modifier = Modifier.fillMaxWidth(),
                passwordVisible = viewModel.passwordVisible,
                onPasswordToggle = { viewModel.togglePasswordVisibility() }
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = viewModel.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                placeholder = stringResource(R.string.repita_senha),
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                modifier = Modifier.fillMaxWidth(),
                passwordVisible = viewModel.passwordVisible,
                onPasswordToggle = { viewModel.togglePasswordVisibility() }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Handle sign-up state
            when (signUpState) {
                is SignUpState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is SignUpState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate("login")
                    }
                }
                is SignUpState.Error -> {
                    Snackbar(
                        modifier = Modifier.fillMaxWidth(),
                        content = { Text(signUpState.message) }
                    )
                }
                else -> { /* Idle state - do nothing */ }
            }

            ButtonComponent(
                value = stringResource(id = R.string.login),
                onButtonClick = {
                    viewModel.signUp()
                },
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(20.dp))

            AlternativeOptionGoogle(
                onIconClick = {},
                value =  stringResource(R.string.registration_with_google_cadastro)
            )

            Spacer(modifier = Modifier.height(20.dp))

            AlternativeOptionAccount(
                value = stringResource(R.string.has_an_account),
                linkText = stringResource(R.string.do_the_login),
                onLinkClick = {
                    navController.navigate("login")
                },
                modifier = Modifier.fillMaxWidth()
            )

        }

    }
}
