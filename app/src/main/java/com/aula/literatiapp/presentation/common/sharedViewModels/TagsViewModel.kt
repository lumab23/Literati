package com.aula.literatiapp.presentation.common.sharedViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aula.literatiapp.BuildConfig
import com.aula.literatiapp.data.remote.RetrofitInstance
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.domain.model.BookItem
import com.aula.literatiapp.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TagsViewModel : ViewModel() {

    // iniciando a firestore database
    private val firestore = FirebaseFirestore.getInstance()
    // auth para pegar o id do usuário
    private val auth = FirebaseAuth.getInstance()
    // o id do usuário
    private val userId: String
        get() = auth.currentUser?.uid ?: ""

    /*
    booksByTag sendo definido como um MutableStateFlow que é usada para gerenciar estados mutáveis e observáveis
    O StateFlow é um tipo especial de fluxo projetado para representar e observar estados que mudam ao longo do tempo
     */
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
                fetchBooksByIds(allBookIds) // foi trocado
            }
            .addOnFailureListener { e ->
                println("Erro ao carregar tags: ${e.message}")
            }
    }

    private suspend fun fetchBookById(bookId: String): BookItem? {

        // Chama a API para obter o livro pelo ID
        Log.d("bookId", bookId)
        val response = RetrofitInstance.api.getBookById(bookId, BuildConfig.GOOGLE_API_KEY)
        val volumeInfo = response.volumeInfo

        val book = BookItem(
            id = bookId,
            volumeInfo = volumeInfo
        )

        return book
    }

    private fun fetchBooksByIds(bookIds: List<String>) {
        viewModelScope.launch {
            // Lista para armazenar os resultados
            val books = mutableListOf<Book>()

            // Chamada assíncrona para obter os livros
            val deferredBooks = bookIds.map { bookId ->
                async {
                    fetchBookById(bookId) // Agora retorna BookItem
                }
            }

            // Espera todas as tarefas assíncronas terminarem e coleta os resultados
            deferredBooks.awaitAll().forEach { bookItem ->
                bookItem?.let {
                    // Convertendo BookItem para Book antes de adicionar à lista
                    val book = Book(
                        id = it.id,
                        title = it.volumeInfo.title,
                        authors = it.volumeInfo.authors ?: emptyList(),
                        publisher = it.volumeInfo.publisher,
                        publishedDate = it.volumeInfo.publishedDate,
                        description = it.volumeInfo.description,
                        pageCount = it.volumeInfo.pageCount?.toString(),
                        categories = it.volumeInfo.categories ?: emptyList(),
                        averageRating = it.volumeInfo.averageRating,
                        ratingsCount = it.volumeInfo.ratingsCount,
                        language = it.volumeInfo.language,
                        imageLinks = it.volumeInfo.imageLinks,
                        previewLink = it.volumeInfo.previewLink
                    )
                    books.add(book)
                }
            }

            // Atualiza o estado da prateleira com os livros encontrados
            _bookshelf.value = books
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

    fun loadBooksByTags(selectedTags: List<String>) {
        viewModelScope.launch {
            val booksToLoad = _booksByTag.value.filterKeys { it in selectedTags }.values.flatten().distinct()
            fetchBooksByIds(booksToLoad) // foi trocado
        }
    }



}

