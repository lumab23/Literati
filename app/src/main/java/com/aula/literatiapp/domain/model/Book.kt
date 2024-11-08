package com.aula.literatiapp.domain.model

data class Book(
    val id: String,
    val title: String,
    val authors: List<String> = emptyList(),
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String> = emptyList(),
    val averageRating: Double? = null,
    val ratingsCount: Int? = null,
    val language: String? = null,
    val thumbnail: String? = null,
    val previewLink: String? = null,
)
