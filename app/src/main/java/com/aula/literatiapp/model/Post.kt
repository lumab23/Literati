package com.aula.literatiapp.model

data class Post(
    val id: String,
    val author: String,
    val avatarUrl: String,
    val content: String,
    var likes: Int = 0,
    var isLiked: Boolean = false,
    val comments: MutableList<String> = mutableListOf()
)