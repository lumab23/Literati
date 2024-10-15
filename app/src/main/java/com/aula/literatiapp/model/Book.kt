package com.aula.literatiapp.model

data class Book(
    val title: String,
    val authors: List<String>?,
    val publisher: String?,
    val description: String?,
    val imageLinks: ImageLinks?,
    val tags: String?,
    val genres: List<String>?
)
