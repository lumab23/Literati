package com.aula.literatiapp.presentation.screens.registration

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aula.literatiapp.R
import com.aula.literatiapp.presentation.common.sharedComponents.AlternativeOptionAccount
import com.aula.literatiapp.presentation.common.sharedComponents.AlternativeOptionGoogle
import com.aula.literatiapp.presentation.common.sharedComponents.ButtonComponent
import com.aula.literatiapp.presentation.common.sharedComponents.HeadlineText
import com.aula.literatiapp.presentation.common.sharedComponents.TextField
import com.aula.literatiapp.presentation.screens.registration.components.ForgotPasswordLink
import com.aula.literatiapp.presentation.screens.registration.viewModels.LoginState
import com.aula.literatiapp.presentation.screens.registration.viewModels.LoginViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

private const val TAG = "MainActivity"

@Composable
fun LoginScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {


    //authentication
    val auth = Firebase.auth
    Log.i(TAG, "onCreate: ${auth.currentUser}")
    auth.currentUser

    val loginState = viewModel.loginState

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
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            HeadlineText(stringResource(R.string.bem_vindo_volta))

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

            ForgotPasswordLink()

            // handling the login State
            when (loginState) {

                is LoginState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is LoginState.Success -> {
                    navController.navigate("home_screen")
                }

                is LoginState.Error -> {
                    Snackbar(
                        modifier = Modifier.fillMaxWidth(),
                        content = { Text(loginState.message) }
                    )
                }

                else -> {
                    // idle ou outros estados
                }
            }

            ButtonComponent(
                value = stringResource(id = R.string.login),
                onButtonClick = {
                    viewModel.login()
                },
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(20.dp))


            AlternativeOptionGoogle(
                value =  stringResource(R.string.registration_with_google),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            AlternativeOptionAccount(
                value = stringResource(R.string.no_account),
                linkText = stringResource(R.string.cadastre_se),
                onLinkClick = {
                    navController.navigate("signUp")
                },
                modifier = Modifier.fillMaxWidth()
            )

        }

    }


}


@PreviewLightDark
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(modifier = Modifier, navController = navController)
}