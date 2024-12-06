package com.aula.literatiapp.presentation.screens.profile.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aula.literatiapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getUserById(userId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val userDocument = firestore.collection("users").document(userId).get().await()
                val userData = userDocument.toObject(User::class.java)
                _user.value = userData
                _loading.value = false
            } catch (e: Exception) {
                _error.value = "Failed to fetch user: ${e.message}"
                _loading.value = false
            }
        }
    }




}