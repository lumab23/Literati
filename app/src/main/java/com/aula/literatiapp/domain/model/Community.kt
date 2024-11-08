package com.aula.literatiapp.domain.model

data class Community(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String? = null,
    val specificCommunityImageUrl: String? = null,
    val specificCommunityName: String,
    val categories: List<String> = emptyList()
)
