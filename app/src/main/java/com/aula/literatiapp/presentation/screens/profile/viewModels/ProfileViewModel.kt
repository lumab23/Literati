package com.aula.literatiapp.presentation.screens.profile.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aula.literatiapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _favorites = MutableStateFlow<List<String>>(emptyList())
    val favorites: StateFlow<List<String>> = _favorites

    private val _recentReads = MutableStateFlow<List<String>>(emptyList())
    val recentReads: StateFlow<List<String>> = _recentReads

    private val _swaps = MutableStateFlow<List<String>>(emptyList())
    val exchange: StateFlow<List<String>> = _swaps

    init {
        loadUserData()
        loadUserFavorites()
        loadRecentReads()
        loadSwaps()
    }

    private fun loadUserData() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            firestore.collection("users").document(currentUser.uid)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        return@addSnapshotListener
                    }
                    snapshot?.toObject(User::class.java)?.let { user ->
                        _user.value = user
                    }
                }
        }
    }

    private fun loadUserFavorites() {
        viewModelScope.launch {
            //_favorites.value = user.value?.favorites ?: emptyList()
            _favorites.value = listOf("Favorite Book 1", "Favorite Book 2")
        }
    }

    private fun loadRecentReads() {
        viewModelScope.launch {
            // Mock or actual logic to fetch recent reads
            _recentReads.value = listOf("Recently Read Book 1", "Recently Read Book 2")
        }
    }

    private fun loadSwaps() {
        viewModelScope.launch {
            // Mock or actual logic to fetch books for swapping
            _swaps.value = listOf("Swap Book 1", "Swap Book 2")
        }
    }

}