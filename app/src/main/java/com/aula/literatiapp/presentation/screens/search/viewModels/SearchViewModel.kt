package com.aula.literatiapp.presentation.screens.search.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aula.literatiapp.data.remote.RetrofitInstance
import com.aula.literatiapp.domain.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update
import com.aula.literatiapp.BuildConfig


class SearchViewModel : ViewModel() {

    private val apiKey =  BuildConfig.GOOGLE_API_KEY

    private val _bookList = MutableStateFlow<List<Book>>(emptyList())
    val bookList: StateFlow<List<Book>> = _bookList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        viewModelScope.launch {
            _searchQuery.collectLatest { query ->
                if (query.isNotEmpty()) {
                    searchBooks(query)
                } else {
                    _bookList.update { emptyList() }
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchBooks(query: String) {
        _isLoading.update { true }
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchBooks(query = query, apiKey = apiKey)
                _bookList.value = response.items.map { item ->
                    Book(
                        id = item.id,
                        title = item.volumeInfo.title ?: "No Title",
                        authors = item.volumeInfo.authors,
                        publisher = item.volumeInfo.publisher,
                        publishedDate = item.volumeInfo.publishedDate,
                        description = item.volumeInfo.description,
                        pageCount = item.volumeInfo.pageCount,
                        categories = item.volumeInfo.categories,
                        averageRating = item.volumeInfo.averageRating,
                        ratingsCount = item.volumeInfo.ratingsCount,
                        language = item.volumeInfo.language,
                        imageLinks = item.volumeInfo.imageLinks,
                        previewLink = item.volumeInfo.previewLink
                    )
                }
                _errorMessage.update { null }
            } catch (e: Exception) {
                _errorMessage.update { "Error fetching books: ${e.message}" }
            } finally {
                _isLoading.update { false }
            }
        }
    }
}
