package com.aula.literatiapp.presentation.screens.community.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.aula.literatiapp.domain.model.Community
import com.aula.literatiapp.domain.model.CommunityPost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.aula.literatiapp.domain.model.User
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Date
import java.util.UUID

class CommunityViewModel : ViewModel() {

    // UI State
    private val _posts = MutableStateFlow<List<CommunityPost>>(emptyList())
    val posts: StateFlow<List<CommunityPost>> = _posts

    private val _comments = MutableStateFlow<List<CommunityPost>>(emptyList())
    val comments: StateFlow<List<CommunityPost>> = _comments

    private val _isMember = MutableStateFlow(false)
    val isMember: StateFlow<Boolean> = _isMember

    private val _isAdmin = MutableStateFlow(false)
    val isAdmin: StateFlow<Boolean> = _isAdmin

    private val _parentCommunityName = MutableStateFlow<String?>(null)
    val parentCommunityName: StateFlow<String?> get() = _parentCommunityName

    private val _communities = MutableStateFlow<List<Community>>(emptyList())
    val communities: StateFlow<List<Community>> = _communities

    private val _community = MutableStateFlow<Community?>(null)
    val community: StateFlow<Community?> = _community

    private val _members = MutableStateFlow<List<User>>(emptyList())
    val members: StateFlow<List<User>> = _members

    private val _admins = MutableStateFlow<List<User>>(emptyList())
    val admins: StateFlow<List<User>> = _admins

    private val _isLiked = MutableStateFlow<Boolean>(false)
    val isLiked: StateFlow<Boolean> = _isLiked

    private val _likeCounts = MutableStateFlow(0)
    val likeCounts: StateFlow<Int> get() = _likeCounts

    private val _myCommunities = MutableStateFlow<List<Community>>(emptyList())
    val myCommunities: StateFlow<List<Community>> = _myCommunities

    // Firebase instance, for example
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()


    // TODO: fetch the communities
    fun fetchUserCommunities() {
        val userId = auth.currentUser?.uid ?: return

        firestore.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val communityIds = document.get("communities") as? List<String> ?: emptyList()

                    val fetchedCommunities = mutableListOf<Community>()
                    communityIds.forEach { communityId ->
                        firestore.collectionGroup("specificCommunities")
                            .whereEqualTo("id", communityId)
                            .get()
                            .addOnSuccessListener { querySnapshot ->
                                for (doc in querySnapshot.documents) {
                                    val community = doc.toObject(Community::class.java)
                                    if (community != null) {
                                        fetchedCommunities.add(community)
                                    }
                                }

                                _myCommunities.value = fetchedCommunities
                            }
                            .addOnFailureListener { e ->
                                Log.e("fetchUserCommunities", "Failed to fetch community: $e")
                            }
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("fetchUserCommunities", "Failed to fetch user communities: $e")
            }
    }



    fun joinCommunity(parentCommunityId: String, communityId: String) {
        val userId = auth.currentUser?.uid ?: return

        val memberData = hashMapOf(
            "role" to "member",
            "joinedAt" to FieldValue.serverTimestamp()
        )

        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("members")
            .document(userId)
            .set(memberData)
            .addOnSuccessListener {
                Log.d("Join Community", "User $userId joined the community")

                val userRef = firestore.collection("users").document(userId)

                userRef.update("communities", FieldValue.arrayUnion(communityId))
                    .addOnSuccessListener {
                        Log.d("Join Community", "User's communities list updated successfully")
                    }
                    .addOnFailureListener { e ->
                        Log.e("Join Community", "Failed to update user's communities list: $e")
                    }
            }
            .addOnFailureListener { e ->
                Log.e("Join Community", "Error adding user: $e")
            }
    }

    fun promoteToAdmin(parentCommunityId: String, communityId: String, userId: String) {
        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("members")
            .document(userId)
            .update("role", "admin")
            .addOnSuccessListener {
                Log.d("Promote Member", "User $userId promoted to admin")
            }
            .addOnFailureListener { e ->
                Log.e("Promote Member", "Error promoting user: $e")
            }
    }

    fun removeAdmin(parentCommunityId: String, communityId: String, userId: String) {
        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("members")
            .document(userId)
            .update("role", "member")
            .addOnSuccessListener {
                Log.d("Promote Member", "User $userId promoted to admin")
            }
            .addOnFailureListener { e ->
                Log.e("Promote Member", "Error promoting user: $e")
            }
    }

    fun deleteCommunity(parentCommunityId: String, communityId: String, onComplete: (Boolean) -> Unit) {
        val communityRef = firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)

        communityRef.collection("subcollectionName")
            .get()
            .addOnSuccessListener { snapshot ->
                for (document in snapshot.documents) {
                    document.reference.delete()
                }
            }

        communityRef.delete()
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    fun leaveCommunity(parentCommunityId: String, communityId: String) {
        val userId = auth.currentUser?.uid ?: return

        val userDocRef = firestore.collection("users").document(userId)

        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("members")
            .document(userId)
            .delete()
            .addOnSuccessListener {
                Log.d("Leave Community", "User $userId left the community")

                userDocRef.get().addOnSuccessListener { document ->
                    if (document.exists()) {
                        val user = document.toObject(User::class.java)

                        val updatedCommunities = user?.communities?.toMutableList()?.apply {
                            remove(communityId)
                        } ?: mutableListOf()

                        userDocRef.update("communities", updatedCommunities)
                            .addOnSuccessListener {
                                Log.d("Leave Community", "User's communities list updated successfully.")
                            }
                            .addOnFailureListener { e->
                                Log.e("Leave Community", "Failed to update user's communities list: $e")
                            }
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("Leave Community", "Error removing user: $e")
            }
    }

    fun checkUserStatus(parentCommunityId: String, communityId: String) {
        val userId = auth.currentUser?.uid ?: return
        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("members")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val role = document.getString("role")
                    _isMember.value = role == "member" || role == "admin"
                    _isAdmin.value = role == "admin"
                } else {
                    _isMember.value = false
                    _isAdmin.value = false
                }
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    fun checkUserStatusById(parentCommunityId: String, communityId: String, userId: String) {
        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("members")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val role = document.getString("role")
                    _isMember.value = role == "member" || role == "admin"
                    _isAdmin.value = role == "admin"
                } else {
                    _isMember.value = false
                    _isAdmin.value = false
                }
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    fun getAllMembers(parentCommunityId: String, communityId: String) {
        val db = FirebaseFirestore.getInstance()

        db.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("members")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.isEmpty) {
                    Log.d("Community Debug banco", "No members found for communityId: $communityId")
                    _members.value = emptyList()
                    return@addOnSuccessListener
                }

                // Retrieve the member IDs
                val memberIds = snapshot.documents.map { it.id }

                // Fetch user data for each member
                val userRequests = memberIds.map { userId ->
                    db.collection("users").document(userId).get()
                }

                Tasks.whenAllSuccess<DocumentSnapshot>(userRequests)
                    .addOnSuccessListener { userDocuments ->
                        val membersList = userDocuments.mapNotNull { userDocument ->
                            userDocument.toObject(User::class.java)?.copy(id = userDocument.id)
                        }

                        Log.d("Community Debug banco", "Members fetched: $membersList")
                        _members.value = membersList
                    }
                    .addOnFailureListener { exception ->
                        Log.e("Community Debug banco", "Error fetching user data: $exception")
                        _members.value = emptyList()
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("Community Debug banco", "Error fetching members: $exception")
                _members.value = emptyList()
            }
    }

    fun getAdmins(parentCommunityId: String, communityId: String){
        val db = FirebaseFirestore.getInstance()

        db.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("members")
            .whereEqualTo("role", "admin")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.isEmpty) {
                    Log.d("Community Debug banco", "No members found for communityId: $communityId")
                    _admins.value = emptyList()
                    return@addOnSuccessListener
                }

                val adminsIds = snapshot.documents.map { it.id }

                // Fetch user data for each member
                val userRequests = adminsIds.map { userId ->
                    db.collection("users").document(userId).get()
                }

                Tasks.whenAllSuccess<DocumentSnapshot>(userRequests)
                    .addOnSuccessListener { userDocuments ->
                        val adminsList = userDocuments.mapNotNull { userDocument ->
                            userDocument.toObject(User::class.java)?.copy(id = userDocument.id)
                        }

                        Log.d("Community Debug banco", "Members fetched: $adminsList")
                        _admins.value = adminsList
                    }
                    .addOnFailureListener { exception ->
                        Log.e("Community Debug banco", "Error fetching user data: $exception")
                        _admins.value = emptyList()
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("Community Debug banco", "Error fetching members: $exception")
                _admins.value = emptyList()
            }
    }

    fun getNonAdminMembers(parentCommunityId: String, communityId: String) {
        val db = FirebaseFirestore.getInstance()

        db.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("members")
            .whereEqualTo("role", "member")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.isEmpty) {
                    Log.d("Community Debug banco", "No non-admin members found for communityId: $communityId")
                    _members.value = emptyList()
                    return@addOnSuccessListener
                }

                val memberIds = snapshot.documents.map { it.id }

                val userRequests = memberIds.map { userId ->
                    db.collection("users").document(userId).get()
                }

                Tasks.whenAllSuccess<DocumentSnapshot>(userRequests)
                    .addOnSuccessListener { userDocuments ->
                        val nonAdminMembersList = userDocuments.mapNotNull { userDocument ->
                            userDocument.toObject(User::class.java)?.copy(id = userDocument.id)
                        }

                        Log.d("Community Debug banco", "Non-admin members fetched: $nonAdminMembersList")
                        _members.value = nonAdminMembersList
                    }
                    .addOnFailureListener { exception ->
                        Log.e("Community Debug banco", "Error fetching user data: $exception")
                        _members.value = emptyList()
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("Community Debug banco", "Error fetching non-admin members: $exception")
                _members.value = emptyList()
            }
    }

    fun createCommunity(community: Community, parentCommunityId: String) {
        Log.d("banco", "Community creation started")

        val userId = auth.currentUser?.uid ?: return

        val newCommunity = community.copy(
            parentCommunityId = parentCommunityId,
            name = community.name,
            description = community.description,
            imageUrl = community.imageUrl,
            posts = community.posts,
            categories = community.categories
        )

        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .add(newCommunity.toMap())
            .addOnSuccessListener { documentReference ->
                val newCommunityId = documentReference.id
                Log.d("banco", "Community created successfully with ID: $newCommunityId")

                // Update the community document with its generated ID
                documentReference.update("id", newCommunityId)
                    .addOnSuccessListener {
                        Log.d("banco", "Community ID updated successfully in Firestore.")

                        // Add the creator to the members subcollection as admin
                        val memberData = mapOf(
                            "role" to "admin",
                            "joinedAt" to System.currentTimeMillis()
                        )
                        documentReference.collection("members")
                            .document(userId)
                            .set(memberData)
                            .addOnSuccessListener {
                                Log.d("banco", "Creator added as admin to the community members.")

                                val userRef = firestore.collection("users").document(userId)

                                userRef.update("communities", FieldValue.arrayUnion(newCommunityId))
                                    .addOnSuccessListener {
                                        Log.d("banco", "User's communities list updated successfully.")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.e("banco", "Failed to update user's communities list: $e")
                                    }
                            }
                            .addOnFailureListener { exception ->
                                Log.e("banco", "Failed to add creator as admin: $exception")
                            }
                    }
                    .addOnFailureListener { exception ->
                        Log.e("banco", "Failed to update community ID: $exception")
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("banco", "Community creation failed: $exception")
            }
    }

    fun createPost(post: CommunityPost, parentCommunityId: String, communityId: String) {
        Log.d("banco", "Post creation started")

        val userId = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { userDocument ->
                if (userDocument.exists()) {

                    val userName = userDocument.getString("username") ?: "Usuário Anônimo"
                    val userImageUrl = userDocument.getString("profilePictureUrl")

                    val newPost = post.copy(
                        id = post.id,
                        userId = userId,
                        userName = userName,
                        userImageUrl = userImageUrl,
                        content = post.content,
                        imageUrl = post.imageUrl,
                        createdAt = post.createdAt,
                        likesCount = post.likesCount,
                        commentsCount = post.commentsCount,
                        likedBy = post.likedBy
                    )

                    firestore.collection("communities")
                        .document(parentCommunityId)
                        .collection("specificCommunities")
                        .document(communityId)
                        .collection("posts")
                        .add(newPost.toMap())
                        .addOnSuccessListener { documentReference ->
                            val newPostId = documentReference.id
                            Log.d("banco", "Post created successfully with ID: $newPostId")

                            // Atualiza o post com o ID
                            documentReference.update("id", newPostId)
                                .addOnSuccessListener {
                                    Log.d("banco", "Post ID updated successfully in Firestore.")
                                }
                                .addOnFailureListener { exception ->
                                    Log.e("banco", "Failed to update post ID: $exception")
                                }
                        }
                        .addOnFailureListener { exception ->
                            Log.e("banco", "Post creation failed: $exception")
                        }
                } else {
                    Log.e("banco", "User document not found!")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("banco", "Failed to retrieve user data: $exception")
            }
    }


    fun deletePost(parentCommunityId: String, communityId: String, postId: String) {
        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("posts")
            .document(postId)
            .delete()
            .addOnSuccessListener {
                println("Post deleted successfully")
            }
            .addOnFailureListener { exception ->
                println("Error deleting post: $exception")
            }
    }

    fun likePost(
        parentCommunityId: String,
        communityId: String,
        postId: String,
        callback: ((Boolean, Int) -> Unit)? = null
    ) {
        val userId = auth.currentUser?.uid ?: return

        // Código do método
        firestore.runTransaction { transaction ->
            val likeRef = firestore.collection("communities")
                .document(parentCommunityId)
                .collection("specificCommunities")
                .document(communityId)
                .collection("posts")
                .document(postId)
                .collection("likes")
                .document(userId)

            val postRef = firestore.collection("communities")
                .document(parentCommunityId)
                .collection("specificCommunities")
                .document(communityId)
                .collection("posts")
                .document(postId)

            val likeSnapshot = transaction.get(likeRef)
            val currentLiked = likeSnapshot.getBoolean("liked") ?: false

            val postSnapshot = transaction.get(postRef)
            val post = postSnapshot.toObject(CommunityPost::class.java)
                ?: throw Exception("Post not found!")

            val newLikedState = !currentLiked
            val updatedLikesCount = if (newLikedState) {
                post.likesCount + 1
            } else {
                (post.likesCount - 1).coerceAtLeast(0)
            }

            transaction.set(likeRef, mapOf("liked" to newLikedState))
            transaction.set(postRef, post.copy(likesCount = updatedLikesCount))

            // Callback com os valores atualizados
            callback?.invoke(newLikedState, updatedLikesCount)
        }
    }

    fun loadLikeCounts(
        parentCommunityId: String,
        communityId: String,
        postId: String,
        onResult: (Int) -> Unit
    ) {
        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("posts")
            .document(postId)
            .get()
            .addOnSuccessListener { document ->
                val likeCount = document.getLong("likesCount")?.toInt() ?: 0
                onResult(likeCount) // Retorna o valor para o callback
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Failed to load like counts: $exception")
            }
    }

    fun loadIsLiked(
        parentCommunityId: String,
        communityId: String,
        postId: String,
        onResult: (Boolean) -> Unit
    ) {
        val userId = auth.currentUser?.uid ?: return

        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("posts")
            .document(postId)
            .collection("likes")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                val isLiked = document.getBoolean("liked") ?: false
                onResult(isLiked) // Retorna o valor para o callback
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Failed to check like status: $exception")
            }
    }

    fun addComment(post: CommunityPost, parentCommunityId: String, communityId: String, postId: String) {
        val userId = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { userDocument ->
                if (userDocument.exists()) {

                    val userName = userDocument.getString("userName") ?: "Usuário Anônimo"
                    val userImageUrl = userDocument.getString("userImageUrl") // Aqui pegamos a URL da imagem do usuário

                    val newComment = CommunityPost(
                        id = post.id,
                        userId = userId,
                        userName = userName,
                        userImageUrl = userImageUrl,
                        content = post.content,
                        imageUrl = post.imageUrl,
                        createdAt = post.createdAt,
                        likesCount = post.likesCount,
                        commentsCount = post.commentsCount,
                        likedBy = post.likedBy
                    )

                    firestore.collection("communities")
                        .document(parentCommunityId)
                        .collection("specificCommunities")
                        .document(communityId)
                        .collection("posts")
                        .document(postId)
                        .collection("comments")
                        .add(newComment.toMap())
                        .addOnSuccessListener { documentReference ->
                            val commentId = documentReference.id
                            Log.d("banco", "Comment added successfully with ID: $commentId")

                            // Atualiza o comentário com o ID gerado
                            documentReference.update("id", commentId)
                                .addOnSuccessListener {
                                    Log.d("banco", "Comment ID updated successfully")
                                }
                                .addOnFailureListener { exception ->
                                    Log.e("banco", "Error updating comment ID: $exception")
                                }
                        }
                        .addOnFailureListener { exception ->
                            Log.e("banco", "Error adding comment: $exception")
                        }

                    val postRef = firestore.collection("communities")
                        .document(parentCommunityId)
                        .collection("specificCommunities")
                        .document(communityId)
                        .collection("posts")
                        .document(postId)

                    firestore.runTransaction { transaction ->
                        val postSnapshot = transaction.get(postRef)
                        val post = postSnapshot.toObject(CommunityPost::class.java)

                        val updatedCommentsCount = post?.commentsCount?.let {
                            it + 1
                        } ?: 1

                        val updatedPost = post?.copy(commentsCount = updatedCommentsCount)
                        transaction.set(postRef, updatedPost!!)
                    }.addOnSuccessListener {
                        Log.d("banco", "Post updated successfully with new comments count")
                    }.addOnFailureListener { exception ->
                        Log.e("banco", "Error updating post: $exception")
                    }
                } else {
                    Log.e("banco", "User document not found!")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("banco", "Failed to retrieve user data: $exception")
            }
    }


    fun updateCommunity(
        parentCommunityId: String,
        communityId: String,
        name: String? = null,
        description: String? = null,
        imageUrl: String? = null,
    ) {
        val updates = mutableMapOf<String, Any>()
        name?.let { updates["name"] = it }
        description?.let { updates["description"] = it }
        imageUrl?.let { updates["imageUrl"] = it }

        if (updates.isNotEmpty()) {
            firestore.collection("communities")
                .document(parentCommunityId)
                .collection("specificCommunities")
                .document(communityId)
                .update(updates)
                .addOnSuccessListener {
                    Log.d("CommunityViewModel", "Community updated successfully")
                }
                .addOnFailureListener { e ->
                    Log.e("CommunityViewModel", "Failed to update community", e)
                }
        } else {
            Log.w("CommunityViewModel", "No updates provided for community")
        }
    }

    fun isGeneralCommunity(community: Community): Boolean {
        return community.parentCommunityId == null
    }

    fun loadParentCommunityName(parentCommunityId: String) {
        val db = FirebaseFirestore.getInstance()

        db.collection("communities")
            .document(parentCommunityId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val parentCommunity = document.toObject(Community::class.java)
                    _parentCommunityName.value = parentCommunity?.name // Update the StateFlow
                } else {
                    _parentCommunityName.value = "Unknown Parent Community"
                }
            }
            .addOnFailureListener { exception ->
                println("Error fetching parent community: $exception")
                _parentCommunityName.value = "Error Loading Name"
            }
    }

    fun loadUserProfilePicture(onComplete: (String?) -> Unit) {
        val userId = auth.currentUser?.uid ?: return

        firestore.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val profileImageUrl = document.getString("profileImageUrl")
                    onComplete(profileImageUrl)
                } else {
                    onComplete(null)
                }
            }
            .addOnFailureListener { exception ->
                println("Error fetching user profile picture: $exception")
                onComplete(null)
            }
    }

    fun loadSpecificCommunities(parentCommunityId: String) {
        val db = FirebaseFirestore.getInstance()

        db.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.isEmpty) {
                    Log.d("Community Debug banco", "No specific communities found for parentCommunityId: $parentCommunityId")
                    _communities.value = emptyList()
                    return@addOnSuccessListener
                }

                val specificCommunities = snapshot.documents.mapNotNull { specificDoc ->
                    specificDoc.toObject(Community::class.java)?.copy(id = specificDoc.id)
                }

                Log.d("Community Debug banco", "Specific communities fetched: $specificCommunities")
                _communities.value = specificCommunities
            }
            .addOnFailureListener { exception ->
                Log.e("Community Debug banco", "Error fetching specific communities: $exception")
                _communities.value = emptyList()
            }
    }

    fun loadAllPosts(parentCommunityId: String, communityId: String) {
        firestore.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("posts")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.isEmpty) {
                    Log.d("Community Debug banco", "No posts found for communityId: $communityId")
                    _posts.value = emptyList()
                    return@addOnSuccessListener
                }

                val posts = snapshot.documents.mapNotNull { postDoc ->
                    postDoc.toObject(CommunityPost::class.java)?.copy(id = postDoc.id)
                }

                Log.d("Community Debug banco", "Posts fetched: $posts")
                _posts.value = posts
            }
            .addOnFailureListener { exception ->
                Log.e("Community Debug banco", "Error fetching posts: $exception")
                _posts.value = emptyList()
            }
    }

    fun loadAllComments(parentCommunityId: String, communityId: String, postId: String) {
        val db = FirebaseFirestore.getInstance()

        db.collection("communities")
            .document(parentCommunityId)
            .collection("specificCommunities")
            .document(communityId)
            .collection("posts")
            .document(postId)
            .collection("comments")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.isEmpty) {
                    Log.d("Community Debug banco", "No comments found for postId: $postId")
                    _comments.value = emptyList()
                    return@addOnSuccessListener
                }

                val comments = snapshot.documents.mapNotNull { commentDoc ->
                    commentDoc.toObject(CommunityPost::class.java)?.copy(id = commentDoc.id)
                }

                Log.d("Community Debug banco", "Comments fetched: $comments")
                _comments.value = comments
            }
            .addOnFailureListener { exception ->
                Log.e("Community Debug banco", "Error fetching comments: $exception")
                _comments.value = emptyList()
            }
    }

    fun getCommunityById(parentCommunityId: String, communityId: String) {
        viewModelScope.launch {
            val db = FirebaseFirestore.getInstance()
            Log.d("SpecificCommunity Debug banco", communityId)
            Log.d("SpecificCommunity Debug banco", parentCommunityId)

            db.collection("communities")
                .document(parentCommunityId)
                .collection("specificCommunities")
                .document(communityId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val community = document.toObject(Community::class.java)
                        _community.value = community
                        Log.d("SpecificCommunity Debug banco", community.toString())
                    } else {
                        _community.value = null
                    }
                }
                .addOnFailureListener {
                    // adicionar exceção dps
                }
        }
    }
}