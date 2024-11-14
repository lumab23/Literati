package com.aula.literatiapp.presentation.screens.bookDetails.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aula.literatiapp.BuildConfig
import com.aula.literatiapp.data.remote.GoogleBooksApi
import com.aula.literatiapp.data.remote.RetrofitInstance
import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.domain.model.BookItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookScreenViewModel() : ViewModel() {

    private val apiKey =  BuildConfig.GOOGLE_API_KEY

    private val _bookList = MutableStateFlow<List<Book>>(emptyList())
    val bookList: StateFlow<List<Book>> = _bookList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun getBookById(id: String) {
        _isLoading.update { true }
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiId.searchBooks(query = id, apiKey = apiKey)
                Log.d("BookScreenViewModel", "Response: $response")
            } catch(e: Exception) {}

        }
    }

}