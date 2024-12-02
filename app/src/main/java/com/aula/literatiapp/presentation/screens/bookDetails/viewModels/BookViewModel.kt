package com.aula.literatiapp.presentation.screens.bookDetails.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aula.literatiapp.domain.model.Book
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class BookViewModel(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _book = MutableLiveData<Book?>()
    val book: MutableLiveData<Book?> = _book

    fun loadBook(bookId: String) {
        firestore.collection("books").document(bookId)
            .get()
            .addOnSuccessListener { document ->
                val book = document.toObject(Book::class.java)
                _book.value = book
            }
    }

    fun addTagsToBook(bookId: String, tags: List<String>) {
        val bookRef = firestore.collection("books").document(bookId)
        bookRef.update("tags", FieldValue.arrayUnion(*tags.toTypedArray()))

        tags.forEach { tag ->
            val tagRef = firestore.collection("tags").document(tag)
            tagRef.update("bookIds", FieldValue.arrayUnion(bookId))
                .addOnFailureListener {
                    tagRef.set(mapOf("bookIds" to listOf(bookId)))
                }
        }
    }

}