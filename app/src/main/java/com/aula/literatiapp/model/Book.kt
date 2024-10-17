package com.aula.literatiapp.model

data class Book(
    val title: String,
    val authors: List<String>?,
    val publisher: String?,
    val description: String?,
    val imageUrl: String,
    val tags: String?,
    val genres: List<String>?,
    val id: String,
    val volumeInfo: String?,
    val pages: String?,
    val review: String
)
