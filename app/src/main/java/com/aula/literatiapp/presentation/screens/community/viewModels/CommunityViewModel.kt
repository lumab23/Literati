package com.aula.literatiapp.presentation.screens.community.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.domain.model.CommunityPost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date
import java.util.UUID

class CommunityViewModel : ViewModel() {

    // UI State
    private val _posts = MutableStateFlow<List<CommunityPost>>(emptyList())
    val posts: StateFlow<List<CommunityPost>> = _posts

    private val _isMember = MutableStateFlow(false)
    val isMember: StateFlow<Boolean> = _isMember

    private val _isAdmin = MutableStateFlow(false)
    val isAdmin: StateFlow<Boolean> = _isAdmin

    // Firebase instance, for example
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun loadPosts(communityId: String) {
        firestore.collection("communities")
            .document(communityId)
            .collection("posts")
            .get()
            .addOnSuccessListener { snapshot ->
                val postsList = snapshot.toObjects(CommunityPost::class.java)
                _posts.value = postsList
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    fun joinCommunity(communityId: String) {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("communities")
            .document(communityId)
            .collection("members")
            .document(userId)
            .set(hashMapOf("joined" to true))
            .addOnSuccessListener {
                _isMember.value = true
                loadPosts(communityId)
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    fun checkMembershipStatus(communityId: String) {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("communities")
            .document(communityId)
            .collection("members")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                _isMember.value = document.exists()
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    fun checkAdminStatus(communityId: String) {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("communities")
            .document(communityId)
            .get()
            .addOnSuccessListener { document ->
                _isAdmin.value = document.getString("adminId") == userId
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    fun createCommunity(community: Community, categories: List<String>) {
        val communityData = community.copy(categories = categories).toMap() // Assuming Community has a toMap() function
        firestore.collection("communities")
            .add(communityData)
            .addOnSuccessListener {
                // Community created successfully
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    fun createPost(communityId: String, userName: String, postText: String) {
        val userId = auth.currentUser?.uid ?: return
        val newPost = CommunityPost(
            id = UUID.randomUUID().toString(),  // Generate a unique ID for the post
            communityId = communityId,
            userId = userId,
            userName = userName,  // Use the passed userName
            content = postText,
            createdAt = Date(),
            likesCount = 0,  // Initialize likes count as 0
            commentsCount = 0,  // Initialize comments count as 0
            comments = listOf()  // Initialize comments as an empty list
        )

        // Save the post to Firestore
        firestore.collection("communities")
            .document(communityId)
            .collection("posts")
            .document(newPost.id)
            .set(newPost)  // Save the new post in Firestore
            .addOnSuccessListener {
                // Optionally, you could immediately update the posts list by fetching it again
                loadPosts(communityId)
            }
            .addOnFailureListener {
                // Handle failure, show an error message
            }
    }

}