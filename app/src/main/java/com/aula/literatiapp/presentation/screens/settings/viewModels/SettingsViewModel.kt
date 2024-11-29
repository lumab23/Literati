package com.aula.literatiapp.presentation.screens.settings.viewModels

import android.net.Uri
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.aula.literatiapp.domain.model.User
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SettingsViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> get() = _userName

    private val _userEmail = MutableStateFlow("")
    val userEmail: StateFlow<String> get() = _userEmail

    private val _userProfilePictureUrl = MutableStateFlow("")
    val userProfilePictureUrl: StateFlow<String> get() = _userProfilePictureUrl

    init {
        fetchUserData()
    }

    fun fetchUserData() {
        val user = firebaseAuth.currentUser
        user?.let {
            _userName.value = it.displayName ?: ""
            _userEmail.value = it.email ?: ""
            _userProfilePictureUrl.value = it.photoUrl?.toString() ?: ""
        }
    }

    private fun updateUserNameInFirestore(newName: String, onComplete: (Boolean) -> Unit) {
        val currentUser = firebaseAuth.currentUser
        currentUser?.let {
            firestore.collection("users").document(it.uid)
                .update("username", newName)
                .addOnCompleteListener { task ->
                    onComplete(task.isSuccessful)
                }
        }
    }


    fun updateUserName(newName: String, onComplete: (Boolean) -> Unit) {
        val user = firebaseAuth.currentUser
        user?.let {
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(newName)
                .build()

            it.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _userName.value = newName
                        updateUserNameInFirestore(newName) {firestoreSuccess ->
                            onComplete(firestoreSuccess)
                        }
                    } else {
                        onComplete(false)
                    }
                }
        }
    }

    fun updateUserEmail(newEmail: String, onComplete: (Boolean) -> Unit) {
        val user = firebaseAuth.currentUser
        user?.verifyBeforeUpdateEmail(newEmail)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _userEmail.value = newEmail
                onComplete(true)
            } else {
                onComplete(false)
            }
        }
    }

    fun updateUserPassword(newPassword: String, onComplete: (Boolean) -> Unit) {
        val user = firebaseAuth.currentUser
        user?.updatePassword(newPassword)?.addOnCompleteListener { task ->
            onComplete(task.isSuccessful)
        }
    }

    private fun updateUserProfilePictureInFirestore(photoUrl: String) {
        val user = firebaseAuth.currentUser
        user?.let {
            firestore.collection("users").document(it.uid)
                .update("profilePictureUrl", photoUrl)
                .addOnFailureListener { e -> e.printStackTrace() }
        }
    }

    fun uploadProfilePicture(
        imageUri: Uri,
        onComplete: (Boolean, String) -> Unit
    ) {
        val user = firebaseAuth.currentUser

        if (user == null) {
            onComplete(false, "User is not authenticated")
            return
        }

        val storageRef = storage.reference.child("profile_pictures/${user?.uid}.jpg")

        viewModelScope.launch {
            try {
                // Upload file
                storageRef.putFile(imageUri).await()

                // Get the file's URL
                val downloadUri = storageRef.downloadUrl.await()

                // Update profile in Firebase Auth
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setPhotoUri(downloadUri)
                    .build()

                user.updateProfile(profileUpdates).await()

                // Update Firestore and local state
                _userProfilePictureUrl.value = downloadUri.toString()
                updateUserProfilePictureInFirestore(downloadUri.toString())

                // Notify the completion
                onComplete(true, downloadUri.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                onComplete(false, "Failed to upload profile picture: ${e.message}")
            }
        }
    }


    fun deleteAccount(
        password: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        val user = firebaseAuth.currentUser
        user?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onComplete(true, null)
            } else {
                onComplete(false, task.exception?.message)
            }
        }
    }


    fun signOut() {
        firebaseAuth.signOut()
    }


}