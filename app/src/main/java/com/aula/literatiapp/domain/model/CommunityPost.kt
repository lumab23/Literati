package com.aula.literatiapp.domain.model

import java.util.Date

data class CommunityPost(
    val id: String,
    val communityId: String,
    val userId: String,
    val userName: String,
    val content: String,
    val imageUrl: String? = null,
    val createdAt: Date,
    val likesCount: Int = 0,
    val commentsCount: Int = 0,
    val comments: List<String> = listOf()
)