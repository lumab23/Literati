package com.aula.literatiapp.presentation.screens.search.viewModels

import android.util.Log
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
import com.aula.literatiapp.domain.model.BookItem
import kotlinx.coroutines.delay


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
                    delay(200)
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
                val filteredBooks = response.items.filter { item ->
                    item.volumeInfo.title.contains(query, ignoreCase = true)
                }.map { item ->
                    Book(
                        id = item.id,
                        title = item.volumeInfo.title ?: "No Title",
                        authors = item.volumeInfo.authors,
                        publisher = item.volumeInfo.publisher,
                        publishedDate = item.volumeInfo.publishedDate,
                        description = item.volumeInfo.description,
                        pageCount = item.volumeInfo.pageCount?.toString(),
                        categories = item.volumeInfo.categories ?: emptyList(),
                        averageRating = item.volumeInfo.averageRating,
                        ratingsCount = item.volumeInfo.ratingsCount,
                        language = item.volumeInfo.language,
                        imageLinks = item.volumeInfo.imageLinks,
                        previewLink = item.volumeInfo.previewLink
                    )
                }

                _bookList.value = filteredBooks
                _errorMessage.update { null }
            } catch (e: Exception) {
                _errorMessage.update { "Error fetching books: ${e.message}" }
            } finally {
                _isLoading.update { false }
            }
        }
    }

    fun getBooksByDateRange(startYear: Int, endYear: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val query = "publishedDate:$startYear-$endYear"
                val response = RetrofitInstance.api.searchBooks(query = query, apiKey = apiKey)

                val validBooks = response.items.filter { item ->
                    val year = item.volumeInfo.publishedDate?.take(4)?.toIntOrNull()
                    year != null && year in startYear..endYear
                }

                _bookList.value = validBooks.map { item ->

                    Book(
                        id = item.id,
                        title = item.volumeInfo.title ?: "No Title",
                        authors = item.volumeInfo.authors,
                        publisher = item.volumeInfo.publisher,
                        publishedDate = item.volumeInfo.publishedDate,
                        description = item.volumeInfo.description,
                        pageCount = item.volumeInfo.pageCount?.toString(),
                        categories = item.volumeInfo.categories ?: emptyList(),
                        averageRating = item.volumeInfo.averageRating,
                        ratingsCount = item.volumeInfo.ratingsCount,
                        language = item.volumeInfo.language,
                        imageLinks = item.volumeInfo.imageLinks,
                        previewLink = item.volumeInfo.previewLink
                    )
                }
            } catch (e: Exception) {
                _bookList.value = emptyList()
                _errorMessage.update { "Error fetching books: ${e.message}" }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getPopularBooks(maxResults: Int = 40) {
        _isLoading.update { true }
        //_bookList.update { emptyList() }
        viewModelScope.launch {
            try {
                val query = "bestseller"
                val response = RetrofitInstance.api.searchBooks(
                    query = query,
                    maxResults = maxResults,
                    apiKey = apiKey
                )

                val popularBooks = response.items.map { item ->
                    Book(
                        id = item.id,
                        title = item.volumeInfo.title ?: "No Title",
                        authors = item.volumeInfo.authors,
                        publisher = item.volumeInfo.publisher,
                        publishedDate = item.volumeInfo.publishedDate,
                        description = item.volumeInfo.description,
                        pageCount = item.volumeInfo.pageCount?.toString(),
                        categories = item.volumeInfo.categories ?: emptyList(),
                        averageRating = item.volumeInfo.averageRating,
                        ratingsCount = item.volumeInfo.ratingsCount,
                        language = item.volumeInfo.language,
                        imageLinks = item.volumeInfo.imageLinks,
                        previewLink = item.volumeInfo.previewLink
                    )
                }

                val filteredPopularBooks = popularBooks.filter { book ->
                    (book.ratingsCount ?: 0) > 20 || (book.averageRating ?: 0.0) >= 3.0
                }.sortedWith(
                    compareByDescending<Book> { it.ratingsCount ?: 0 }
                        .thenByDescending { it.averageRating ?: 0.0 }
                )

                _bookList.value = filteredPopularBooks
                _errorMessage.update { null }
            } catch (e: Exception) {
                _errorMessage.update { "Error fetching popular books: ${e.message}" }
            } finally {
                _isLoading.update { false }
            }
        }
    }


    fun getBooksByGenre(genre: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val query = "subject:$genre"
                val response = RetrofitInstance.api.searchBooks(query = query, apiKey = apiKey)
                val filteredBooks = response.items.map { item ->
                    Book(
                        id = item.id,
                        title = item.volumeInfo.title ?: "No Title",
                        authors = item.volumeInfo.authors,
                        publisher = item.volumeInfo.publisher,
                        publishedDate = item.volumeInfo.publishedDate,
                        description = item.volumeInfo.description,
                        pageCount = item.volumeInfo.pageCount?.toString(),
                        categories = item.volumeInfo.categories ?: emptyList(),
                        averageRating = item.volumeInfo.averageRating,
                        ratingsCount = item.volumeInfo.ratingsCount,
                        language = item.volumeInfo.language,
                        imageLinks = item.volumeInfo.imageLinks,
                        previewLink = item.volumeInfo.previewLink
                    )
                }
                _bookList.value = filteredBooks
                _errorMessage.update { null }
            } catch (e: Exception) {
                _bookList.value = emptyList()
                _errorMessage.update { "Error fetching books by genre: ${e.message}" }
            } finally {
                _isLoading.value = false
            }
        }
    }





}
