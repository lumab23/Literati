package com.aula.literatiapp.presentation.common.sharedViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aula.literatiapp.BuildConfig
import com.aula.literatiapp.data.remote.RetrofitInstance
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TagsViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val userId: String
        get() = auth.currentUser?.uid ?: ""

    private val _booksByTag = MutableStateFlow<Map<String, List<String>>>(emptyMap())
    val booksByTag: StateFlow<Map<String, List<String>>> = _booksByTag

    private val _bookshelf = MutableLiveData<List<Book>>()
    val bookshelf: LiveData<List<Book>> get() = _bookshelf

    init {
        loadTags()
    }

    // Carregar tags do Firestore
    fun loadTags() {
        if (userId.isEmpty()) return

        firestore.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                val tags = document.get("tags") as? Map<String, List<String>> ?: emptyMap()
                // Atualize o fluxo de estado com as tags
                _booksByTag.value = tags

                val allBookIds = tags.values.flatten().distinct()
                fetchBooksByIds(allBookIds)
            }
            .addOnFailureListener { e ->
                println("Erro ao carregar tags: ${e.message}")
            }
    }

    private fun fetchBooksByIds(bookIds: List<String>) {
        viewModelScope.launch {
            val books = bookIds.mapNotNull { bookId ->
                try {
                    val response = RetrofitInstance.api.searchBooks(
                        query = "id:$bookId",
                        apiKey = BuildConfig.GOOGLE_API_KEY
                    )
                    response.items.firstOrNull()?.volumeInfo
                } catch (e: Exception) {
                    Log.e("TagsViewModel", "Erro ao buscar livro $bookId: ${e.message}")
                    null
                }
            }
            _bookshelf.value = books
        }
    }


    // Adicionar livro a uma tag específica
    fun addBookToTag(tag: String, bookId: String) {
        viewModelScope.launch {
            val currentTags = _booksByTag.value.toMutableMap()
            val updatedList = currentTags[tag]?.toMutableList() ?: mutableListOf()

            if (!updatedList.contains(bookId)) {
                updatedList.add(bookId)
                currentTags[tag] = updatedList
                _booksByTag.value = currentTags

                Log.d("TagsViewModel", "Books in tag '$tag': ${updatedList.joinToString(", ")}")

                updateFirestoreTags(currentTags)
            }
        }
    }

    // Atualizar as tags no Firestore
    private fun updateFirestoreTags(updatedTags: Map<String, List<String>>) {
        firestore.collection("users").document(userId) // Substituir pelo ID do usuário autenticado
            .update("tags", updatedTags)
            .addOnSuccessListener {
                println("Tags atualizadas no Firestore com sucesso!")
            }
            .addOnFailureListener { e ->
                println("Erro ao atualizar tags no Firestore: ${e.message}")
            }
    }

    fun updateTagsForBook(updatedTags: List<String>, bookId: String) {
        viewModelScope.launch {
            val currentTags = _booksByTag.value.toMutableMap()

            // Remove o livro das tags antigas
            currentTags.forEach { (tag, books) ->
                if (books.contains(bookId)) {
                    currentTags[tag] = books.filter { it != bookId }
                }
            }

            // Adiciona o livro às novas tags
            updatedTags.forEach { tag ->
                val booksInTag = currentTags[tag]?.toMutableList() ?: mutableListOf()
                if (!booksInTag.contains(bookId)) {
                    booksInTag.add(bookId)
                }
                currentTags[tag] = booksInTag
            }

            _booksByTag.value = currentTags
            updateFirestoreTags(currentTags)
        }
    }

    fun getBooksByTags(userId: User) {
        viewModelScope.launch {
            // pegar as tags e os ids dos livros do firestore
            val userDoc = firestore.collection("users").document(userId.id).get().await()
            val tagsMap = userDoc.get("tags") as? Map<String, List<String>>

            val booksIds = tagsMap?.values?.flatten() ?: emptyList()

        }
    }



}

