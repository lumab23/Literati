package com.aula.literatiapp.domain.model

data class Notification(
    val name: String,
    val action: String,
    val time: String,
    val userImageUrl: String,
    val nomeLivroOuComunidade: String
)