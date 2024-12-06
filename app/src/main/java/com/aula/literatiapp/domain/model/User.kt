package com.aula.literatiapp.domain.model

data class User(
    val id: String = "",
    var name: String = "",
    val username: String = "",
    val email: String = "",
    val profilePictureUrl: String = "",
    val tags: Map<String, List<String>> = emptyMap(),
    val communities: List<String> = emptyList()
)
