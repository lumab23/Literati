package com.aula.literatiapp.presentation.screens.settings.viewModels

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SettingsViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    var userEmail: String = ""
    var userName: String = ""
    var userProfilePictureUrl: String = ""

    init {
        fetchUserData()
    }

    fun fetchUserData() {
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            firestore.collection("users").document(user.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        userEmail = document.getString("email") ?: ""
                        userName = document.getString("username") ?: ""
                        userProfilePictureUrl = document.getString("profilePictureUrl") ?: ""

                    }
                }
                .addOnFailureListener {
                    // erros se o fetching dos dados do user dê errado
                }
        }
    }

    fun updateEmail(newEmail: String) {
        val currentUser = auth.currentUser
        currentUser?.let {
            it.verifyBeforeUpdateEmail(newEmail)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        firestore.collection("users").document(it.uid)
                            .update("email", newEmail)
                    }
                }
        }
    }

    fun updateUsername(newUsername: String) {
        val currentUser = auth.currentUser
        currentUser?.let {
            firestore.collection("users").document(it.uid)
                .update("username", newUsername)
                .addOnSuccessListener {
                    userName = newUsername
                }
                .addOnFailureListener {
                    // erros se o update do username dê errado

                }
        }
    }

    fun updatePassword(newPassword: String) {
        val currentUser = auth.currentUser
        currentUser?.let {
            it.updatePassword(newPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sucesso ao atualizar a senha
                    }

                }
        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun deleteAccount() {
        val currentUser = auth.currentUser
        currentUser?.let {
            it.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sucesso ao deletar a conta

                    }
                }
        }
    }

}