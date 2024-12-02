package com.aula.literatiapp.domain.model

import java.util.Date

data class CommunityPost(
    val id: String = "",
    val userId: String = "",
    val userName: String = "",
    val content: String = "",
    val imageUrl: String? = null,
    val createdAt: Date = Date(),
    val likesCount: Int = 0,
    val commentsCount: Int = 0,
    val likedBy: Map<String, Boolean> = emptyMap()
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "userId" to userId,
            "userName" to userName,
            "content" to content,
            "imageUrl" to imageUrl,
            "createdAt" to createdAt,
            "likesCount" to likesCount,
            "commentsCount" to commentsCount,
            "likedBy" to likedBy
        )
    }
}