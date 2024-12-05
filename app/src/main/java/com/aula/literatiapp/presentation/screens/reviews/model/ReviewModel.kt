package com.aula.literatiapp.presentation.screens.reviews.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

data class Review(
    val bookId: String,
    val userId: String,
    val rating: Double,
    val comment: String,
    val timestamp: Timestamp
)

class ReviewViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()


    // StateFlow para monitorar as reviews associadas a um livro
    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> get() = _reviews

    // StateFlow para monitorar o estado de envio
    private val _isSubmitting = MutableStateFlow(false)
    val isSubmitting: StateFlow<Boolean> get() = _isSubmitting

    // Função para buscar reviews de um livro específico
    fun fetchReviews(bookId: String) {
        viewModelScope.launch {
            firestore.collection("reviews")
                .whereEqualTo("bookId", bookId)
                .orderBy("timestamp")
                .get()
                .addOnSuccessListener { result ->
                    val reviewList = result.documents.mapNotNull { document ->
                        document.toObject(Review::class.java)
                    }
                    _reviews.value = reviewList
                }
                .addOnFailureListener {
                    _reviews.value = emptyList() // Pode ser usado para exibir erro
                }
        }
    }

    // Função para enviar uma nova review
    fun postReview(bookId: String, userId: String, rating: Double, comment: String) {
        _isSubmitting.value = true
        val newReview = mapOf(
            "bookId" to bookId,
            "userId" to userId,
            "rating" to rating,
            "comment" to comment,
            "timestamp" to Timestamp(Date())
        )

        firestore.collection("reviews")
            .add(newReview)
            .addOnSuccessListener {
                _isSubmitting.value = false
                fetchReviews(bookId) // Atualiza as reviews após enviar
            }
            .addOnFailureListener {
                _isSubmitting.value = false
                // Pode ser usado para exibir uma mensagem de erro
            }
    }
}
