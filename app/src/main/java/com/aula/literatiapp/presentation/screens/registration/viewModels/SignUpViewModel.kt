package com.aula.literatiapp.presentation.screens.registration.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    var username by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    var passwordVisible by mutableStateOf(false)
        private set

    var confirmPasswordVisible by mutableStateOf(false)
        private set

    var signUpState by mutableStateOf<SignUpState>(SignUpState.Idle)
        private set

    fun onUsernameChange(newUsername: String) {
        username = newUsername
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        confirmPassword = newConfirmPassword
        validatePasswordMatch()
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    fun toggleConfirmPasswordVisibility() {
        confirmPasswordVisible = !confirmPasswordVisible
    }

    private fun validatePasswordMatch() {
        if (password != confirmPassword) {
            signUpState = SignUpState.Error("Passwords do not match")
        } else {
            signUpState = SignUpState.Idle
        }
    }

    fun signUp() {
        if (validateInput()) {
            signUpState = SignUpState.Loading
            viewModelScope.launch {
                try {
                    // simulando uma network call com delay
                    delay(2000)

                    // se o sign up for um sucesso
                    signUpState = SignUpState.Success
                } catch (e: Exception) {
                    signUpState = SignUpState.Error("Sign-up failed: ${e.message}")
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        return when {
            username.isEmpty() -> {
                signUpState = SignUpState.Error("Username is required")
                false
            }
            email.isEmpty() -> {
                signUpState = SignUpState.Error("Email is required")
                false
            }
            password.isEmpty() -> {
                signUpState = SignUpState.Error("Password is required")
                false
            }
            password != confirmPassword -> {
                signUpState = SignUpState.Error("Passwords do not match")
                false
            }
            else -> true
        }
    }

}

sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    object Success : SignUpState()
    data class Error(val message: String) : SignUpState()
}