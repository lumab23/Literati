package com.aula.literatiapp.domain.model

data class Community(
    val id: String ="",
    val name: String="",
    val description: String="",
    val imageUrl: String? = null,
    val posts: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val parentCommunityId: String? = null
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "imageUrl" to imageUrl,
            "posts" to posts,
            "categories" to categories,
            "parentCommunityId" to parentCommunityId
        )
    }
}