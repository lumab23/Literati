package com.aula.literatiapp.presentation.screens.library.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aula.literatiapp.domain.model.Book
import com.google.firebase.firestore.FirebaseFirestore

class LibraryViewModel(
    private val firestore: FirebaseFirestore,
) : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    fun loadLibrary() {
        firestore.collection("books").get()
            .addOnSuccessListener { querySnapshot ->
                val bookList = querySnapshot.toObjects(Book::class.java)
                _books.value = bookList
            }
    }

}