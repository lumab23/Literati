package com.aula.literatiapp.presentation.screens.registration.viewModels

import android.net.wifi.hotspot2.pps.Credential
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CustomCredential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aula.literatiapp.domain.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val auth = Firebase.auth

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var passwordVisible by mutableStateOf(false)
        private set

    var loginState by mutableStateOf<LoginState>(LoginState.Idle)
        private set

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    private fun fetchUserFromFirestore(uid: String, onSuccess: (User) -> Unit, onFailure: (Exception) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val user = document.toObject(User::class.java)
                    user?.let { onSuccess(it) }
                } else {
                    onFailure(Exception("User document does not exist"))
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun login() {
        loginState = LoginState.Loading
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val uid = auth.currentUser?.uid ?: return@addOnCompleteListener
                        fetchUserFromFirestore(
                            uid,
                            onSuccess = { user ->
                                loginState = LoginState.Success(user)
                            },
                            onFailure = { exception ->
                                loginState = LoginState.Error("Failed to fetch user data: ${exception.message}")
                            }
                        )
                    } else {
                        loginState = LoginState.Error("Login failed: ${task.exception?.message}")
                    }
                }
        }
    }



}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: User) : LoginState()
    data class Error(val message: String) : LoginState()
}