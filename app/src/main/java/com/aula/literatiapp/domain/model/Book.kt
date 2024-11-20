package com.aula.literatiapp.domain.model

import android.util.Log

data class Book(
    val id: String,
    val title: String,
    val authors: List<String> = emptyList(),
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val pageCount: String? = null,
    val categories: List<String> = emptyList(),
    val averageRating: Double? = null,
    val ratingsCount: Int? = null,
    val language: String? = null,
    val imageLinks: ImageLinks?,
    val previewLink: String? = null,
    val popularity: Int = 0,
    val tags: List<String> = emptyList()
)
{
    override fun toString(): String {
        //Log.d("Book", "toString: $categories")
        return "$id $title $authors $publisher, $publishedDate, $description, $pageCount, $categories, $averageRating, $ratingsCount, $language, $imageLinks, $previewLink}"
    }
}