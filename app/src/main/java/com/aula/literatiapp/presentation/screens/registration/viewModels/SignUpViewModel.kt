package com.aula.literatiapp.presentation.screens.registration.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aula.literatiapp.domain.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : ViewModel() {

    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)
    var confirmPasswordVisible by mutableStateOf(false)
    var signUpState by mutableStateOf<SignUpState>(SignUpState.Idle)

    fun onUsernameChange(newUsername: String) { username = newUsername }
    fun onEmailChange(newEmail: String) { email = newEmail }
    fun onPasswordChange(newPassword: String) { password = newPassword }
    fun onConfirmPasswordChange(newConfirmPassword: String) {
        confirmPassword = newConfirmPassword
        validatePasswordMatch()
    }

    fun togglePasswordVisibility() { passwordVisible = !passwordVisible }
    fun toggleConfirmPasswordVisibility() { confirmPasswordVisible = !confirmPasswordVisible }

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
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                        val user = User(id = userId, name = username, username = username, email = email, profilePictureUrl = "")
                        saveUserToFirestore(user)
                        signUpState = SignUpState.Success
                    } else {
                        signUpState = SignUpState.Error("Sign-up failed: ${task.exception?.message}")
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

    private fun saveUserToFirestore(user: User) {
        firestore.collection("users").document(user.id).set(user)
            .addOnSuccessListener {
                Log.d("SignUpViewModel", "User successfully written!")
            }
            .addOnFailureListener { e ->
                Log.w("SignUpViewModel", "Error writing user", e)
                signUpState = SignUpState.Error("Failed to save user to Firestore: ${e.message}")
            }
    }
}

sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    object Success : SignUpState()
    data class Error(val message: String) : SignUpState()
}